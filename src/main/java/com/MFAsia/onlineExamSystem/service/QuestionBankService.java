package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
		return list;
	}
	
	@Transactional
	public List<Object> getAllQuestionP (){
		Long cours = (long) 1;
		String book = "1";
		String ch = "1";
		List<Object> list = new ArrayList<>();
		quesBankRepo.getInfoForQuestionPaper(cours, book, ch, null,new PageRequest(0, 2)).forEach(list::add);
		return list;
	}
	@Transactional
	public void saveQuestion (QuestionsBank quesBank) {
		quesBankRepo.save(quesBank);
	}
	@Transactional
	public Optional<QuestionsBank> findById(Long id) {
		return Optional.of(quesBankRepo.findOne(id));
	}
}
