package com.mfasia.onlineexamsystem.controller;


import java.security.Principal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.Course;
import com.mfasia.onlineexamsystem.entities.ExamBoard;
import com.mfasia.onlineexamsystem.entities.ExamInfo;
import com.mfasia.onlineexamsystem.entities.QuestionPaper;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.models.ResultCounter;
import com.mfasia.onlineexamsystem.service.CourseService;
import com.mfasia.onlineexamsystem.service.ExamBoardService;
import com.mfasia.onlineexamsystem.service.ExamInfoService;
import com.mfasia.onlineexamsystem.service.QuestionBankService;
import com.mfasia.onlineexamsystem.service.QuestionPaperService;
import com.mfasia.onlineexamsystem.service.StudentsService;
import com.mfasia.onlineexamsystem.service.UserService;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

	@Autowired private QuestionPaperService questionPaperService;
	@Autowired private QuestionBankService quesBankservice;
	@Autowired private MessageSource msgSource ;
	@Autowired private StudentsService studentsService ;
	@Autowired private ExamBoardService examBoardService;
	@Autowired private CourseService courseService;
	@Autowired private ExamInfoService examInfoService;
	@Autowired private UserService userService;

	@SuppressWarnings("unchecked")
	@GetMapping("/showCreatedQuestion")
	public ResponseEntity<List<QuestionsBank>> createQuestionPaper(Principal principal, Authentication authentication) {
		Long userId = null;
		try {
			User user = (User) authentication.getPrincipal();
			userId = user.getUserId();
		}catch (Exception e) {
			OAuth2Authentication oauthentication = (OAuth2Authentication) principal;
			LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) oauthentication.getUserAuthentication().getDetails();
			String email = (String) properties.get("email");
			User usr = userService.findByEmailAddress(email);
			userId = usr.getUserId();
		}
		Student studentInfo = studentsService.findStudentByUserId(userId);
		Long studentId = studentInfo.getStudentId();
		Course courseinfo = courseService.findByCourseName(studentInfo.getSelectedCourse());
		ExamBoard examBoard = examBoardService.findActiveExamBycourseId(courseinfo.getCourseId());
		Long examid = examBoard.getExamId();
		Long studentIdfromQpaper = questionPaperService.findStudentIdFromQusPaper(studentId);
		ExamInfo examinfo = examInfoService.findExamInfoByStudentId(studentId);
		if (examinfo == null && studentId > 0) {
			saveExamInfo(studentId);
		}
		List<QuestionsBank> qusListForExam = questionPaperService.showgeneratedQusList(examid, studentId, studentIdfromQpaper);
		if (qusListForExam.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("noQuestions.found", null, null));
			return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(qusListForExam, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@PutMapping
	private Map<String, Integer> collectAns (@RequestBody List<QuestionPaper> paper, Authentication authentication, Principal principal) {
		Long userId = null;
		try {
			User user = (User) authentication.getPrincipal();
			userId = user.getUserId();
		}catch (Exception e) {
			OAuth2Authentication oauthentication = (OAuth2Authentication) principal;
			LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) oauthentication.getUserAuthentication().getDetails();
			String email = (String) properties.get("email");
			User usr = userService.findByEmailAddress(email);
			userId = usr.getUserId();
		}
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
		int correctAns = resultCounter.getCorrectResult();
		int falseAns = resultCounter.getFalseResult();
		updateExaminfo(studentId, correctAns);
		resultStatus.put("CorrectAns", correctAns);
		resultStatus.put("wrongAns", falseAns);
		
		return resultStatus;
	}
	
	private void saveExamInfo (Long studentId) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		Time startTime = Time.valueOf(time) ;
		Student studentinfo = studentsService.findByStudentId(studentId);
		Student student = new Student();
		student.setStudentId(studentinfo.getStudentId());
		
		ExamInfo examInfo = new ExamInfo();
		examInfo.setStudents(student);
		examInfo.setGeneratedStId(studentinfo.getGeneratedStId());
		examInfo.setStartTime(startTime);
		examInfo.setDate(new Date());
		examInfoService.saveExamInfo(examInfo);
	}
	
	private void updateExaminfo (Long studentId, int correctAns) {
		ExamInfo examinfo = examInfoService.findExamInfoByStudentId(studentId);
		int totalQues = questionPaperService.countQuesforEachStudent(studentId);
		Student student = new Student();
		student.setStudentId(studentId);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		Time endTime = Time.valueOf(time) ;
		float mark = (correctAns * 100.0f) / totalQues;
		String score = String.valueOf(mark)+"%" ;
		String grade = null ;
		if (mark >= 80) {
			grade = "A+";
		}else if (mark >= 70) {
			grade = "A";
		}else if (mark >= 60) {
			grade = "A-";
		}else if (mark >= 50) {
			grade = "B";
		}else if (mark >= 33) {
			grade = "C";
		}else if (mark < 33) {
			grade = "F";
		}
		if (examinfo != null) {
			ExamInfo info = new ExamInfo();
			info.setInfoId(examinfo.getInfoId());
			info.setStudents(student);
			info.setGeneratedStId(examinfo.getGeneratedStId());
			info.setStartTime(examinfo.getStartTime());
			info.setEndTime(endTime);
			info.setDate(examinfo.getDate());
			info.setScore(score);
			info.setGrade(grade);
			examInfoService.saveExamInfo(info);
		}
	}
	
	@GetMapping
	public List<QuestionPaper> getall (){
		return questionPaperService.getAllQustions();
	}

}