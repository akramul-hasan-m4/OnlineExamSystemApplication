package com.mfasia.onlineexamsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfasia.onlineexamsystem.entities.QuestionerDefination;

public interface QuestionerDefinationRepository extends JpaRepository<QuestionerDefination, Long> {
	
	public List<QuestionerDefination> findByexamExamId(Long examId);
}
