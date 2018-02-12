package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.QuestionsBank;
import com.MFAsia.onlineExamSystem.repository.QuestionBankRepository;

@Service
public class QuestionBankService {

	@Autowired
	private QuestionBankRepository quesBankRepo;
	
	@Transactional
	public List<QuestionsBank> getAllQuestion (){
		List<QuestionsBank> list = new ArrayList<>();
		quesBankRepo.findAll().forEach(list::add);
		//System.out.println("resulttt + = "+list.toString());
		return list;
	}
	@Transactional
	public void saveQuestion (QuestionsBank quesBank) {
		quesBankRepo.save(quesBank);
	}
}
