package com.mfasia.onlineexamsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mfasia.onlineexamsystem.entities.ExamBoard;

public interface ExamBoardRepository extends JpaRepository<ExamBoard, Long>{

	@Query(value = "SELECT * FROM exam_board WHERE exam_status = 'Active'", nativeQuery = true)
	public List<ExamBoard> showAllActiveExam ();
}
