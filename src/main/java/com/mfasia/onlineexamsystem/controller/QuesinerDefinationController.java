package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
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
import com.mfasia.onlineexamsystem.entities.Book;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.entities.Teacher;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.service.QuestionerDefinationService;
import com.mfasia.onlineexamsystem.service.TeacherService;

/**
 * @author Akramul
 */
@RestController
@RequestMapping(QuesinerDefinationController.QUESTIONER_DEFINITION_MAPPING)
public class QuesinerDefinationController {
	
	public static final String QUESTIONER_DEFINITION_MAPPING= "/questionerDefination";
	
	@Autowired private QuestionerDefinationService quseDefinationService;
	@Autowired private MessageSource msgSource ;
	@Autowired private TeacherService teacherService ;
	
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
	public ResponseEntity<QuestionerDefination> saveQuestionerDefination(@RequestBody QuestionerDefination quesDefination, BindingResult result, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Long userId = user.getUserId();
		Teacher findTeacherId = teacherService.findByuserId(userId);
		quesDefination.setTeachers(findTeacherId);
		quseDefinationService.saveQuestionDefination(quesDefination);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(quesDefination.getDefinationId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@PutMapping("/{definationId}")
	public ResponseEntity<Book> updateDefinationInfo (@RequestBody QuestionerDefination quesDefination, @PathVariable Long definationId, Authentication authentication) {
		Optional<QuestionerDefination> findDefination = quseDefinationService.findBydefinationId(definationId);
		HttpHeaders headers = new HttpHeaders();
		User user = (User) authentication.getPrincipal();
		Long userId = user.getUserId();
		Teacher findTeacherId = teacherService.findByuserId(userId);
		quesDefination.setTeachers(findTeacherId);
		if (!findDefination.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+definationId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		quseDefinationService.saveQuestionDefination(quesDefination);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{definationId}")
	public ResponseEntity<Void> deleteDefination (@PathVariable Long definationId) {
		Optional<QuestionerDefination> findDefination = quseDefinationService.findBydefinationId(definationId);
		HttpHeaders headers = new HttpHeaders();
		if(findDefination.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			quseDefinationService.deleteDefination(definationId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
	
}
