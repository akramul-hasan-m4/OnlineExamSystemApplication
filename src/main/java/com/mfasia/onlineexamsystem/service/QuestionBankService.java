package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.repositories.QuestionBankRepository;

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
	public List<QuestionsBank> getAllQuestionP (Long courseId, Long bookId,Long chId, Long refId, Pageable pageable){
		/*Long cours = (long) 1;
		String book = "1";
		String ch = "1";*/
		List<QuestionsBank> list = new ArrayList<>();
	//	quesBankRepo.getInfoForQuestionPaper(courseId, bookId, chId, refId,pageable)).forEach(list::add);
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
