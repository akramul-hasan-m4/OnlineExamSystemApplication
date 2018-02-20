package com.mfasia.onlineexamsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.repositories.QuestionBankRepository;
import com.mfasia.onlineexamsystem.service.QuestionPaperService;
import com.mfasia.onlineexamsystem.service.QuestionerDefinationService;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

	@Autowired private QuestionPaperService questionPaperService;
	@Autowired private QuestionerDefinationService qusDefinationService;
	@Autowired private QuestionBankRepository quesBankRepo;
	
	@GetMapping("/{examId}")
	public ResponseEntity<List<QuestionsBank>> createQuestionPaper(@PathVariable("examId") Long examId){
		List<QuestionerDefination> quesDefinationList = qusDefinationService.findByexamExamId(examId);
		List<QuestionsBank> quesBank = null;
		for (QuestionerDefination iterator : quesDefinationList) {
			quesBank = quesBankRepo.getInfoForQuestionPaper(iterator.getCourses().getCourseId(), iterator.getBooks().getBookId().toString(), iterator.getChapters().getChId().toString(), iterator.getRef().getRefId().toString(), new PageRequest(0, iterator.getQusLimitation().intValue()));
			
		}
		return new ResponseEntity<>(quesBank, HttpStatus.OK);
	}
	
	
}
