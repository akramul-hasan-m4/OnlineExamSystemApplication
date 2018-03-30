package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.QuestionPaper;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.exception.OnlineExamSystemException;
import com.mfasia.onlineexamsystem.repositories.QuestionPaperRepository;

@Service
public class QuestionPaperService {
	
	private Logger logger = LoggerFactory.getLogger(QuestionPaperService.class);

	@Autowired private QuestionPaperRepository questionPaperRepo;
	@Autowired private QuestionerDefinationService qusDefinationService;
	@Autowired private QuestionBankService quesBankservice;
	@Autowired private MessageSource msgSource ;
	
	List<QuestionsBank> quesBankList = new ArrayList<>();
	List<QuestionsBank> qusListForExam = new ArrayList<>();
	
	@Transactional
	public List<QuestionsBank> showgeneratedQusList (Long examid, Long studentId, Long studentIdfromQpaper){
		List<QuestionerDefination> quesDefinationList = qusDefinationService.findByexamExamId(examid);
		
		if (!quesBankList.isEmpty() && studentIdfromQpaper == null) {
			quesBankList.clear();
		}
		if (quesBankList.isEmpty()) {
			quesDefinationList.forEach(d -> {
				if (d.getRefId() == null) {
					quesBankList.addAll(quesBankservice.getQuesBankIdForQuesPaper(
						d.getCourses().getCourseId(),
						d.getBookId().toString(), 
						d.getChId().toString(),
						null,
						new PageRequest(0, d.getQusLimitation().intValue())));
				} else {
					quesBankList.addAll(quesBankservice.getQuesBankIdForQuesPaper(
						d.getCourses().getCourseId(),
						null,
						null,
						d.getRefId().toString(),
						new PageRequest(0, d.getQusLimitation().intValue())));
				}
			});
		}
		if (studentIdfromQpaper == null) {
			insertIntoQusPaper(examid, studentId);
		} else {
			if (!qusListForExam.isEmpty()) {
				qusListForExam.clear();
			}
		}
		if (qusListForExam.isEmpty()) {
			try {
				getQuestionForExam(examid, studentId);
			} catch (OnlineExamSystemException e) {
				logger.error(e.getMessage());
			}
		}
		return qusListForExam;
	}
	
	private void insertIntoQusPaper(Long examId, Long studentId) {
		if (!quesBankList.isEmpty()) {
			quesBankList.forEach(l -> {
				QuestionPaper qusPaper = new QuestionPaper();
				QuestionsBank questionsBankId = new QuestionsBank();
				questionsBankId.setQusBankId(l.getQusBankId());
				qusPaper.setExamId(examId);
				qusPaper.setStudentId(studentId);
				qusPaper.setQuestionBank(questionsBankId);
				createQuestion(qusPaper);
			});
		}
	}

	private void getQuestionForExam(Long examId, Long studentId) throws OnlineExamSystemException {
		List<QuestionPaper> qusBankIdList = findByQuesBankId(examId, studentId);
		if (qusBankIdList.isEmpty()) {
			throw new OnlineExamSystemException(msgSource.getMessage("commons.findByidErrorMsg", null, null)+studentId);
		}
		qusBankIdList.forEach(l -> 
			qusListForExam.add(quesBankservice.findByBankId(l.getQuestionBank().getQusBankId()))
		);
	}
	
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