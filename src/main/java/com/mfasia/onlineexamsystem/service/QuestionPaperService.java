package com.mfasia.onlineexamsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.QuestionPaper;
import com.mfasia.onlineexamsystem.repositories.QuestionPaperRepository;

@Service
public class QuestionPaperService {

	@Autowired private QuestionPaperRepository questionPaperRepo;
	
	@Transactional
	public void createQuestion (QuestionPaper questionPaper) {
		questionPaperRepo.save(questionPaper);
	}
}
