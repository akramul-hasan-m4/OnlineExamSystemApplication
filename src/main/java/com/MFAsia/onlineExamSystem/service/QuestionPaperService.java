package com.MFAsia.onlineExamSystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.QuestionPaper;
import com.MFAsia.onlineExamSystem.repository.QuestionPaperRepository;

@Service
public class QuestionPaperService {

	@Autowired private QuestionPaperRepository questionPaperRepo;
	
	@Transactional
	public void createQuestion (QuestionPaper questionPaper) {
		questionPaperRepo.save(questionPaper);
	}
}
