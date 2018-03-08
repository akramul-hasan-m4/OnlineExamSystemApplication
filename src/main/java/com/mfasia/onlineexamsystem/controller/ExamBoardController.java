package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.ExamBoard;
import com.mfasia.onlineexamsystem.service.ExamBoardService;

@RestController
@RequestMapping("/examboard")
public class ExamBoardController {

	@Autowired private ExamBoardService examBoardService;
	
	@GetMapping
	public List<ExamBoard> getAllExamBoardInfo () {
		return examBoardService.getAllExamBoardInfo();
	}
	
	@GetMapping("/active")
	public List<ExamBoard> showAllActiveExam () {
		return examBoardService.showAllActiveExam();
	}
	
	@PostMapping()
	public ResponseEntity<ExamBoard> saveQuesion(@RequestBody ExamBoard board) {
		examBoardService.saveExamdeclaration(board);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(board.getExamId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, "Exam declation"+Messages.SAVE_SUCCESS_MSG+"Exam board");
		return ResponseEntity.created(location).headers(headers).build();
	} 
}
