package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.QuestionerDefination;
import com.MFAsia.onlineExamSystem.repository.QuestionerDefinationRepository;

@Service
public class QuestionerDefinationService {

	@Autowired
	private QuestionerDefinationRepository qusDefinationRepo;
	
	@Transactional
	public List<QuestionerDefination> getAllQuestionerDefination (){
		List<QuestionerDefination> list = new ArrayList<>();
		qusDefinationRepo.findAll().forEach(list::add);
		return list;
	}
	@Transactional
	public List<QuestionerDefination> findByExamId (Long examId){
		List<QuestionerDefination> list = new ArrayList<>();
		qusDefinationRepo.findByexamExamId(examId).forEach(list::add);
		return list;
	}
	
	@Transactional
	public void saveQuestionDefination (QuestionerDefination quesDefination) {
		qusDefinationRepo.save(quesDefination);
	}
}
