package com.MFAsia.onlineExamSystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.QuestionsBank;
import com.MFAsia.onlineExamSystem.service.QuestionBankService;

@RestController
@RequestMapping("/quesionsBank")
public class QuestionBankController {
	
	@Autowired
	private QuestionBankService quesService;
	
	@PostMapping
	public String saveQuesion(@RequestBody QuestionsBank quesBank) {
		quesBank.setQuestionCreatedDate(new Date().toString());
		quesService.saveQuestion(quesBank);
		return "";
	}
	@GetMapping
	public List<QuestionsBank> getAllQuesions () {
		return quesService.getAllQuestion();
	}
	@PutMapping
	public String updateQuestin () {
		return "";
	}
	@DeleteMapping
	public String deleteQuestion () {
		return "";
	}
}
