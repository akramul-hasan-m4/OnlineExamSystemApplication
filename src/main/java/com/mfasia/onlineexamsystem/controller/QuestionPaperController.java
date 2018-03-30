package com.mfasia.onlineexamsystem.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.Course;
import com.mfasia.onlineexamsystem.entities.ExamBoard;
import com.mfasia.onlineexamsystem.entities.QuestionPaper;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.models.ResultCounter;
import com.mfasia.onlineexamsystem.service.CourseService;
import com.mfasia.onlineexamsystem.service.ExamBoardService;
import com.mfasia.onlineexamsystem.service.QuestionBankService;
import com.mfasia.onlineexamsystem.service.QuestionPaperService;
import com.mfasia.onlineexamsystem.service.StudentsService;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

	@Autowired private QuestionPaperService questionPaperService;
	@Autowired private QuestionBankService quesBankservice;
	@Autowired private MessageSource msgSource ;
	@Autowired private StudentsService studentsService ;
	@Autowired private ExamBoardService examBoardService;
	@Autowired private CourseService courseService;

	@GetMapping("/showCreatedQuestion")
	public ResponseEntity<List<QuestionsBank>> createQuestionPaper( Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Long userId = user.getUserId();
		Student studentInfo = studentsService.findStudentByUserId(userId);
		Long studentId = studentInfo.getStudentId();
		Course courseinfo = courseService.findByCourseName(studentInfo.getSelectedCourse());
		ExamBoard examBoard = examBoardService.findActiveExamBycourseId(courseinfo.getCourseId());
		Long examid = examBoard.getExamId();
		Long studentIdfromQpaper = questionPaperService.findStudentIdFromQusPaper(studentId);
		
		List<QuestionsBank> qusListForExam = questionPaperService.showgeneratedQusList(examid, studentId, studentIdfromQpaper);
		if (qusListForExam.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("noQuestions.found", null, null));
			return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(qusListForExam, HttpStatus.OK);
	}

	@PutMapping
	private Map<String, Integer> collectAns (@RequestBody List<QuestionPaper> paper,  Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Long userId = user.getUserId();
		Student studentInfo = studentsService.findStudentByUserId(userId);
		Long studentId = studentInfo.getStudentId();
		Map<String, Integer> resultStatus = new HashMap<>();
		ResultCounter resultCounter = new ResultCounter();
		if (!paper.isEmpty()) {
			paper.forEach(question -> questionPaperService.collectAns( question.getCollectedAns().intValue(), studentId.intValue(), question.getQuestionBank().getQusBankId().intValue()));
			paper.forEach(bank ->{
				QuestionsBank result = quesBankservice.countResult(bank.getQuestionBank().getQusBankId(), bank.getCollectedAns().intValue());
				if (result != null) {
					resultCounter.setCorrectResult(1);
				} else {
					resultCounter.setFalseResult(1);
				}
			});
		}
		resultStatus.put("CorrectAns", resultCounter.getCorrectResult());
		resultStatus.put("wrongAns", resultCounter.getFalseResult());
		
		return resultStatus;
	}
	
	@GetMapping
	public List<QuestionPaper> getall (){
		return questionPaperService.getAllQustions();
	}

}