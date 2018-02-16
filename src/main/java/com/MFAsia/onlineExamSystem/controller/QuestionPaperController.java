package com.MFAsia.onlineExamSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.QuestionerDefination;
import com.MFAsia.onlineExamSystem.service.QuestionPaperService;
import com.MFAsia.onlineExamSystem.service.QuestionerDefinationService;

@RestController("/questionPaper")
public class QuestionPaperController {

	@Autowired private QuestionPaperService questionPaperService;
	@Autowired private QuestionerDefinationService qusDefinationService;
	
	@GetMapping("/{examId}")
	public ResponseEntity<Object> createQuestionPaper(@PathVariable("examId") Long examId){
		List<QuestionerDefination> quesDefinationList = new ArrayList<>();
		qusDefinationService.findByExamId(examId).forEach(quesDefinationList::add);
		//quesDefinationList.stream().map(m-> m.getBooks()).Coll(collector);
		return new ResponseEntity<>(null);
	}
}
