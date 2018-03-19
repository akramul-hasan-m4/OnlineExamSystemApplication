package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.Chapter;
import com.mfasia.onlineexamsystem.repositories.ChaptersRepository;

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
	
	@Transactional
	public List<Chapter> getAllChapters (){
		return chapterRepo.findAll();
	}
	
	@Transactional
	public void saveChapter (Chapter chapter) {
		chapterRepo.save(chapter);
	}
	
	@Transactional
	public Optional<Chapter> findByChapterId (Long id){
		return Optional.of(chapterRepo.findByChId(id));
	}
	
	@Transactional
	public void deleteChapterById (Long chapterId) {
		chapterRepo.delete(chapterId);
	}
}
