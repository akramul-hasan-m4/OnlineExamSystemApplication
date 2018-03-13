package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.ExamBoard;
import com.mfasia.onlineexamsystem.repositories.ExamBoardRepository;

@Service
public class ExamBoardService {

	@Autowired
	private ExamBoardRepository examBoardRepo;
	
	@Transactional
	public List<ExamBoard> getAllExamBoardInfo (){
		List<ExamBoard> list = new ArrayList<>();
		examBoardRepo.findAll().forEach(list::add);
		return list;
	}
	
	@Transactional
	public List<ExamBoard> showAllActiveExam (){
		List<ExamBoard> list = new ArrayList<>();
		examBoardRepo.showAllActiveExam().forEach(list::add);
		return list;
	}
	
	@Transactional
	public void saveExamdeclaration (ExamBoard board) {
		examBoardRepo.save(board);
	}
	
	@Transactional
	public Optional<ExamBoard> findByExamId (Long examid) {
		return Optional.of(examBoardRepo.findOne(examid));
	}
	
	@Transactional
	public void deleteByExamId (Long examId) {
		examBoardRepo.delete(examId);
	}
}
