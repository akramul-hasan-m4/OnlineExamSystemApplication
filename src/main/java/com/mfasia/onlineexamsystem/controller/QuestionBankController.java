package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	
	
	@Autowired private QuestionBankService quesService;
	@Autowired private MessageSource msgSource ;
	
	@PostMapping()
	public ResponseEntity<QuestionsBank> saveQuesion(@RequestBody QuestionsBank quesBank) {
		quesBank.setQuestionCreatedDate(new Date().toString());
		quesService.saveQuestion(quesBank);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(quesBank.getQusBankId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	}
	
	@GetMapping
	public ResponseEntity<List<QuestionsBank>> getAllQuesions () {
		List<QuestionsBank> list = quesService.getAllQuestion();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
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
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+qbid);
			return ResponseEntity.notFound().headers(headers).build();
		}
		
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		questionbank.setQusBankId(qbid);
		quesService.saveQuestion(questionbank);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{bankId}")
	public ResponseEntity<Void> deleteBook (@PathVariable Long bankId) {
		Optional<QuestionsBank> findQusFromBank = quesService.findById(bankId);
		HttpHeaders headers = new HttpHeaders();
		if(bankId != null && findQusFromBank.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			quesService.deleteQusFromBank(bankId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
}
