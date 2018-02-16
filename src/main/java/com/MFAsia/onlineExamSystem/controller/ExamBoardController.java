package com.MFAsia.onlineExamSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.ExamBoard;
import com.MFAsia.onlineExamSystem.service.ExamBoardService;

@RestController
@RequestMapping("/examboard")
public class ExamBoardController {

	@Autowired private ExamBoardService examBoardService;
	
	@GetMapping
	public List<ExamBoard> getAllExamBoardInfo () {
		return examBoardService.getAllExamBoardInfo();
	}
}
