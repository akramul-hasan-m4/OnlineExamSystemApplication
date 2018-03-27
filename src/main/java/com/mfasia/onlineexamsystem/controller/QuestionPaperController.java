package com.mfasia.onlineexamsystem.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.Course;
import com.mfasia.onlineexamsystem.entities.ExamBoard;
import com.mfasia.onlineexamsystem.entities.QuestionPaper;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.exception.OnlineExamSystemException;
import com.mfasia.onlineexamsystem.models.ResultCounter;
import com.mfasia.onlineexamsystem.service.CourseService;
import com.mfasia.onlineexamsystem.service.ExamBoardService;
import com.mfasia.onlineexamsystem.service.QuestionBankService;
import com.mfasia.onlineexamsystem.service.QuestionPaperService;
import com.mfasia.onlineexamsystem.service.QuestionerDefinationService;
import com.mfasia.onlineexamsystem.service.StudentsService;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {
	
	private Logger logger = LoggerFactory.getLogger(QuestionPaperController.class);

	@Autowired private QuestionPaperService questionPaperService;
	@Autowired private QuestionerDefinationService qusDefinationService;
	@Autowired private QuestionBankService quesBankservice;
	@Autowired private MessageSource msgSource ;
	@Autowired private StudentsService studentsService ;
	@Autowired private ExamBoardService examBoardService;
	@Autowired private CourseService courseService;

	List<QuestionsBank> quesBankList = new ArrayList<>();
	List<QuestionsBank> qusListForExam = new ArrayList<>();

	@GetMapping("/showCreatedQuestion")
	public ResponseEntity<List<QuestionsBank>> createQuestionPaper( Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Long userId = user.getUserId();
		System.out.println("userId == " + userId);
		Student studentInfo = studentsService.findStudentByUserId(userId);
		Course courseinfo = courseService.findByCourseName(studentInfo.getSelectedCourse());
		ExamBoard findExamId = examBoardService.findActiveExamBycourseId(courseinfo.getCourseId());
		List<QuestionerDefination> quesDefinationList = qusDefinationService.findByexamExamId(findExamId.getExamId());
		Long studentIdfromQpaper = questionPaperService.findStudentIdFromQusPaper(studentInfo.getStudentId());
		if (!quesBankList.isEmpty() && studentIdfromQpaper == null) {
			quesBankList.clear();
		}
		if (quesBankList.isEmpty()) {
			quesDefinationList.forEach(d -> {
				if (d.getRefId() == null) {
					quesBankList.addAll(quesBankservice.getQuesBankIdForQuesPaper(d.getCourses().getCourseId(),
							d.getBookId().toString(), d.getChId().toString(), null,
							new PageRequest(0, d.getQusLimitation().intValue())));
				} else {
					quesBankList.addAll(quesBankservice.getQuesBankIdForQuesPaper(d.getCourses().getCourseId(), null,
							null, d.getRefId().toString(),
							new PageRequest(0, d.getQusLimitation().intValue())));
				}
			});
		}
		if (studentIdfromQpaper == null) {
			insertIntoQusPaper(findExamId.getExamId(), studentInfo.getStudentId());
		} else {
			if (!qusListForExam.isEmpty()) {
				qusListForExam.clear();
			}
		}
		if (qusListForExam.isEmpty()) {
			try {
				getQuestionForExam(findExamId.getExamId(), studentInfo.getStudentId());
			} catch (OnlineExamSystemException e) {
				logger.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(qusListForExam, HttpStatus.OK);
	}

	private void insertIntoQusPaper(Long examId, Long studentId) {
		if (!quesBankList.isEmpty()) {
			quesBankList.forEach(l -> {
				QuestionPaper qusPaper = new QuestionPaper();
				QuestionsBank questionsBankId = new QuestionsBank();
				questionsBankId.setQusBankId(l.getQusBankId());
				qusPaper.setExamId(examId);
				qusPaper.setStudentId(studentId);
				qusPaper.setQuestionBank(questionsBankId);
				questionPaperService.createQuestion(qusPaper);
			});
		}
	}

	private void getQuestionForExam(Long examId, Long studentId) throws OnlineExamSystemException {
		List<QuestionPaper> qusBankIdList = questionPaperService.findByQuesBankId(examId, studentId);
		if (qusBankIdList.isEmpty()) {
			throw new OnlineExamSystemException(msgSource.getMessage("commons.findByidErrorMsg", null, null)+studentId);
		}
		qusBankIdList.forEach(l -> 
			qusListForExam.add(quesBankservice.findByBankId(l.getQuestionBank().getQusBankId()))
		);

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
