package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.service.QuestionerDefinationService;

@RestController
@RequestMapping("/questionerDefination")
public class QuesinerDefinationController {
	
	@Autowired private QuestionerDefinationService quseDefinationService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping("/{examId}")
	public List<QuestionerDefination> findByExamId (@PathVariable("examId") Long examId) {
		return quseDefinationService.findByexamExamId(examId);
	}
	
	@GetMapping
	public ResponseEntity<List<QuestionerDefination>> getAllQuesionDefination () {
		List<QuestionerDefination> list = quseDefinationService.getAllQuestionerDefination();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<QuestionerDefination> saveQuesion(@RequestBody QuestionerDefination quesDefination) {
		QuestionerDefination questionerDefination = new QuestionerDefination();
		if (quesDefination.getRef() == null) {
			questionerDefination.setBooks(quesDefination.getBooks());
			questionerDefination.setChapters(quesDefination.getChapters());
		}else {
			questionerDefination.setRef(quesDefination.getRef());
		}
		questionerDefination.setExam(quesDefination.getExam());
		questionerDefination.setCourses(quesDefination.getCourses());
		questionerDefination.setQusLimitation(quesDefination.getQusLimitation());
		questionerDefination.setTeachers(quesDefination.getTeachers());
		quseDefinationService.saveQuestionDefination(questionerDefination);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(quesDefination.getDefinationId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG,  msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	}
	
}
