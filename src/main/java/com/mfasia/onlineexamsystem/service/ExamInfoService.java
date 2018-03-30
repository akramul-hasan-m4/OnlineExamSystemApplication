package com.mfasia.onlineexamsystem.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.ExamInfo;
import com.mfasia.onlineexamsystem.repositories.ExamInfoRepository;

@Service
public class ExamInfoService {
	
	@Autowired private ExamInfoRepository examInfoRepository;
	
	@Transactional
	public void saveExamInfo (ExamInfo examInfo) {
		examInfoRepository.save(examInfo);
	}
	
	@Transactional
	public List<ExamInfo> getAllExamInfo (){
		return examInfoRepository.findAll();
	}

}
