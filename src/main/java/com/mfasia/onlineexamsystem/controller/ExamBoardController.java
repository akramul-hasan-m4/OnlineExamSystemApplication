package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
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
import com.mfasia.onlineexamsystem.entities.ExamBoard;
import com.mfasia.onlineexamsystem.service.ExamBoardService;

/**
 * @author Akramul
 */
@RestController
@RequestMapping(ExamBoardController.EXAMBOARD_MAPPING)
public class ExamBoardController {

	public static final String EXAMBOARD_MAPPING= "/examboard";
	
	@Autowired private ExamBoardService examBoardService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping
	public ResponseEntity<List<ExamBoard>> getAllExamBoardInfo () {
		List<ExamBoard> list = examBoardService.getAllExamBoardInfo();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<ExamBoard>> showAllActiveExam () {
		List<ExamBoard> list = examBoardService.showAllActiveExam();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ExamBoard> saveQuesion(@RequestBody ExamBoard board) {
		examBoardService.saveExamdeclaration(board);
		HttpHeaders headers = new HttpHeaders();
		if (board == null) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(board.getExamId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@PutMapping("/{examid}")
	public ResponseEntity<Object> updateExamDeclaration (@RequestBody ExamBoard board, @PathVariable Long examid) {
		Optional<ExamBoard> findExam = examBoardService.findByExamId(examid);
		HttpHeaders headers = new HttpHeaders();
		if (!findExam.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+examid);
			return ResponseEntity.notFound().headers(headers).build();
		}
		
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		examBoardService.saveExamdeclaration(board);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{examId}")
	public ResponseEntity<Void> deleteQuestion (@PathVariable Long examId) {
		Optional<ExamBoard> findExam = examBoardService.findByExamId(examId);
		HttpHeaders headers = new HttpHeaders();
		if(findExam.isPresent() && examId != null) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			examBoardService.deleteByExamId(examId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}

}
