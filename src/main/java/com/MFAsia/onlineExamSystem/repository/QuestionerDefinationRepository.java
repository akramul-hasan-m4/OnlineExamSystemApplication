package com.MFAsia.onlineExamSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MFAsia.onlineExamSystem.entities.QuestionerDefination;

public interface QuestionerDefinationRepository extends JpaRepository<QuestionerDefination, Long> {
	
	public List<QuestionerDefination> findByexamExamId(Long examId);
}
