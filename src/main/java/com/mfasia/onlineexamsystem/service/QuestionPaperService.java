package com.mfasia.onlineexamsystem.service;

import java.util.List;

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
	
	@Transactional
	public Long findStudentIdFromQusPaper (Long studentId) {
		return questionPaperRepo.findByStudentId(studentId);
	}
	
	@Transactional
	public List<QuestionPaper> findByQuesBankId (Long examId, Long studentId) {
		return questionPaperRepo.findByBankId(examId, studentId);
	}
	
	@Transactional
	public void collectAns (Integer collectedAns, Integer studentId, Integer qusBankId) {
		 questionPaperRepo.collectAns( collectedAns, studentId, qusBankId);
	}
	
	@Transactional
	public List<QuestionPaper> getAllQustions (){
		return questionPaperRepo.findAll();
	}
}