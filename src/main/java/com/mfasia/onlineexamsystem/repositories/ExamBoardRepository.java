package com.mfasia.onlineexamsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mfasia.onlineexamsystem.entities.ExamBoard;

@CrossOrigin
public interface ExamBoardRepository extends JpaRepository<ExamBoard, Long>{

	@Query(value = "SELECT * FROM exam_board WHERE exam_status = 'Active'", nativeQuery = true)
	public List<ExamBoard> showAllActiveExam ();
	
	@Query(value = "SELECT * FROM exam_board WHERE exam_status = 'Active' AND course_id =:courseId", nativeQuery = true)
	public ExamBoard findActiveExamBycourseId (@Param("courseId")Long courseId);
}
