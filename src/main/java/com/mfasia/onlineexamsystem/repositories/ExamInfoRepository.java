package com.mfasia.onlineexamsystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.ExamInfo;

@Repository
public interface ExamInfoRepository extends JpaRepository<ExamInfo, Long>{

	public ExamInfo findByStudentsStudentId(Long studentId);
}
