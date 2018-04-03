package com.mfasia.onlineexamsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.ExamInfo;
import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.service.ExamInfoService;
import com.mfasia.onlineexamsystem.service.StudentsService;

@RestController
@RequestMapping("/examInfo")
public class ExamInfoController {

	@Autowired private ExamInfoService examInfoService;
	@Autowired private MessageSource msgSource ;
	@Autowired private StudentsService studentsService ;
	
	@GetMapping
	public ResponseEntity<List<ExamInfo>> getAllExamInfo () {
		List<ExamInfo> list = examInfoService.getAllExamInfo();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/singleResult")
	public ResponseEntity<ExamInfo> getSingleResult (Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Long userId = user.getUserId();
		Student studentInfo = studentsService.findStudentByUserId(userId);
		Long studentId = studentInfo.getStudentId();
		ExamInfo result = examInfoService.findExamInfoByStudentId(studentId);
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (result == null) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
