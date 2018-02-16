package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.Chapter;
import com.MFAsia.onlineExamSystem.repository.ChaptersRepository;

@Service
public class ChapterService {

	@Autowired
	private ChaptersRepository chapterRepo;
	
	@Transactional
	public List<Chapter> findAllChaperByBookId (Long bookId) {
		List<Chapter> list= new ArrayList<>();
		chapterRepo.findAllChaperByBookId(bookId).forEach(list::add);
		return list;
	}
}
