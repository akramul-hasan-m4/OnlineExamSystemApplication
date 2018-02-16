package com.MFAsia.onlineExamSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.Book;
import com.MFAsia.onlineExamSystem.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	private BooksService booksService;
	
	@GetMapping
	public List<Book> getAllBooks (){
		return booksService.getAllBooks();
	}
	
	@GetMapping("/{courseId}")
	public List<Book> getAllBooksByCourseId (@PathVariable("courseId") Long courseId) {
		return booksService.getBooksBycourseId(courseId);
	}

}
