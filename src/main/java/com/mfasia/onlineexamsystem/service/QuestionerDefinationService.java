package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.repositories.QuestionerDefinationRepository;

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
	public List<QuestionerDefination> findByexamExamId (Long examId){
		List<QuestionerDefination> list = new ArrayList<>();
		qusDefinationRepo.findByexamExamId(examId).forEach(list::add);
		return list;
	}
	
	@Transactional
	public void saveQuestionDefination (QuestionerDefination quesDefination) {
		qusDefinationRepo.save(quesDefination);
	}
}
