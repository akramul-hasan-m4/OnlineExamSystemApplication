package com.MFAsia.onlineExamSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.Chapter;
import com.MFAsia.onlineExamSystem.service.ChapterService;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

	@Autowired
	private ChapterService chaterService;
	
	@GetMapping("/{bookId}")
	public List<Chapter> findAllChaperByBookId (@PathVariable("bookId") Long bookId) {
		return chaterService.findAllChaperByBookId(bookId);
	}
}
