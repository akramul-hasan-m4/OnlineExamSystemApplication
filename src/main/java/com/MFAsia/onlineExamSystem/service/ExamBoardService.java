package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.ExamBoard;
import com.MFAsia.onlineExamSystem.repository.ExamBoardRepository;

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
}
