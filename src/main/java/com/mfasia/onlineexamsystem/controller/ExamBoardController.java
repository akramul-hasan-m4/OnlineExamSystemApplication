package com.mfasia.onlineexamsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
