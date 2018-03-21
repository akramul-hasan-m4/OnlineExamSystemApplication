package com.mfasia.onlineexamsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.QuestionPaper;
import com.mfasia.onlineexamsystem.entities.QuestionerDefination;
import com.mfasia.onlineexamsystem.entities.QuestionsBank;
import com.mfasia.onlineexamsystem.exception.OnlineExamSystemException;
import com.mfasia.onlineexamsystem.service.QuestionBankService;
import com.mfasia.onlineexamsystem.service.QuestionPaperService;
import com.mfasia.onlineexamsystem.service.QuestionerDefinationService;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

	private Logger logger = LoggerFactory.getLogger(QuestionPaperController.class);

	@Autowired private QuestionPaperService questionPaperService;
	@Autowired private QuestionerDefinationService qusDefinationService;
	@Autowired private QuestionBankService quesBankservice;
	@Autowired private MessageSource msgSource ;

	List<QuestionsBank> quesBankList = new ArrayList<>();
	List<QuestionsBank> qusListForExam = new ArrayList<>();

	@GetMapping("/{examId}/{studentId}")
	public ResponseEntity<List<QuestionsBank>> createQuestionPaper(@PathVariable Long examId, @PathVariable Long studentId) {
		List<QuestionerDefination> quesDefinationList = qusDefinationService.findByexamExamId(examId);
		Long studentIdfromQpaper = questionPaperService.findStudentIdFromQusPaper(studentId);
		if (!quesBankList.isEmpty() && studentIdfromQpaper == null) {
			quesBankList.clear();
		}
		if (quesBankList.isEmpty()) {
			quesDefinationList.forEach(d -> {
				if (d.getRef() == null) {
					quesBankList.addAll(quesBankservice.getQuesBankIdForQuesPaper(d.getCourses().getCourseId(),
							d.getBooks().getBookId().toString(), d.getChapters().getChId().toString(), null,
							new PageRequest(0, d.getQusLimitation().intValue())));
				} else {
					quesBankList.addAll(quesBankservice.getQuesBankIdForQuesPaper(d.getCourses().getCourseId(), null,
							null, d.getRef().getRefId().toString(),
							new PageRequest(0, d.getQusLimitation().intValue())));
				}
			});
		}
		if (studentIdfromQpaper == null) {
			insertIntoQusPaper(examId, studentId);
		} else {
			if (!qusListForExam.isEmpty()) {
				qusListForExam.clear();
			}
		}
		if (qusListForExam.isEmpty()) {
			try {
				getQuestionForExam(examId, studentId);
			} catch (OnlineExamSystemException e) {
				logger.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(qusListForExam, HttpStatus.OK);
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
				questionPaperService.createQuestion(qusPaper);
			});
		}
	}

	private void getQuestionForExam(Long examId, Long studentId) throws OnlineExamSystemException {
		List<QuestionPaper> qusBankIdList = questionPaperService.findByQuesBankId(examId, studentId);
		if (qusBankIdList.isEmpty()) {
			throw new OnlineExamSystemException(msgSource.getMessage("commons.findByidErrorMsg", null, null)+studentId);
		}
		qusBankIdList.forEach(l -> 
			qusListForExam.add(quesBankservice.findByBankId(l.getQuestionBank().getQusBankId()))
		);

	}
	
	@PutMapping
	private void collectAns (@RequestBody QuestionPaper paper) {
		paper.setStudentId(1l);
		questionPaperService.collectAns( paper.getCollectedAns(), paper.getStudentId(), paper.getQuestionBank().getQusBankId());
	}
	
	@GetMapping
	public List<QuestionPaper> getall (){
		return questionPaperService.getAllQustions();
	}

}
