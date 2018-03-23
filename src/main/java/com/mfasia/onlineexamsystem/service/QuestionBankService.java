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
	public List<QuestionsBank> getQuesBankIdForQuesPaper (Long courseId, String bookId,String chId, String refId, Pageable pageable){
		List<QuestionsBank> list = new ArrayList<>();
		quesBankRepo.getInfoForQuestionPaper(courseId, bookId, chId, refId,pageable).forEach(list::add);
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
	@Transactional
	public QuestionsBank findByBankId(Long id) {
		return quesBankRepo.findOne(id);
	}
	
	@Transactional
	public void deleteQusFromBank (Long bankId) {
		quesBankRepo.delete(bankId);
	}
	
	@Transactional
	public QuestionsBank countResult (Long qusBankId, Integer ans) {
		return quesBankRepo.countResult(qusBankId, ans);
	}
}
