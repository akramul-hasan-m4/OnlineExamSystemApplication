package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.Book;
import com.MFAsia.onlineExamSystem.repository.BooksRepository;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepo;
	
	@Transactional
	public List<Book> getAllBooks () {
		List<Book> list= new ArrayList<>();
		booksRepo.findAll().forEach(list::add);
		return list;
	}
	
	@Transactional
	public List<Book> getBooksBycourseId (Long courseId) {
		List<Book> list= new ArrayList<>();
		booksRepo.findAllByCourseId(courseId).forEach(list::add);
		return list;
	}
}
