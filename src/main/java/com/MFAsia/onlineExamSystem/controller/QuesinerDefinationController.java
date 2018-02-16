package com.MFAsia.onlineExamSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.ExamBoard;
import com.MFAsia.onlineExamSystem.entities.QuestionerDefination;
import com.MFAsia.onlineExamSystem.service.QuestionerDefinationService;

@RestController
@RequestMapping("/questionerDefination")
public class QuesinerDefinationController {

	@Autowired
	private QuestionerDefinationService quseDefinationService;
	
	@GetMapping
	public List<QuestionerDefination> getAllQuesionDefination () {
		return quseDefinationService.getAllQuestionerDefination();
	}
	@GetMapping("/{examId}")
	public List<QuestionerDefination> findByExamId (@PathVariable("examId") Long examId) {
		return quseDefinationService.findByExamId(examId);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void saveQuesionDefination(@RequestBody QuestionerDefination quesDefination , @RequestHeader HttpHeaders headers) {
		quseDefinationService.saveQuestionDefination(quesDefination);
	}
	
	//@PostMapping
	public String testSave() {
		QuestionerDefination q= new QuestionerDefination();
		return "success";
	}
}
