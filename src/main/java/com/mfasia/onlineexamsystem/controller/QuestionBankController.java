package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.service.QuestionBankService;

@RestController
@RequestMapping("/quesionsBank")
public class QuestionBankController {
	
	@Autowired
	private QuestionBankService quesService;
	
	@PostMapping()
	public ResponseEntity<QuestionsBank> saveQuesion(@RequestBody QuestionsBank quesBank) {
		quesBank.setQuestionCreatedDate(new Date().toString());
		quesService.saveQuestion(quesBank);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(quesBank.getQusBankId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, "Question"+Messages.SAVE_SUCCESS_MSG+"Questions Bank");
		return ResponseEntity.created(location).headers(headers).build();
	}
	
	@GetMapping
	public ResponseEntity<List<QuestionsBank>> getAllQuesions () {
		List<QuestionsBank> list = quesService.getAllQuestion();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, Messages.GET_ALL_ERROR_MSG);
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateQuestion (@RequestBody QuestionsBank questionbank, @PathVariable("id") Long qbid) {
		Optional<QuestionsBank> findQus = quesService.findById(qbid);
		HttpHeaders headers = new HttpHeaders();
		if (!findQus.isPresent()) {
			headers.add(Messages.ERROR_MSG, Messages.FIND_BY_ERROR_MSG+qbid);
			return ResponseEntity.notFound().headers(headers).build();
		}
		
		headers.add(Messages.SUCCESS_MSG, "Question"+Messages.UPDATE_SUCCESS_MSG+qbid);
		questionbank.setQusBankId(qbid);
		quesService.saveQuestion(questionbank);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{id}")
	public Boolean deleteQuestion (@PathVariable("id") Long qbid) {
		return Boolean.TRUE;
	}
}
