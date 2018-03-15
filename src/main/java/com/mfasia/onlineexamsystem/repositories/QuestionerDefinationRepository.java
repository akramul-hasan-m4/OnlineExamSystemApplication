package com.mfasia.onlineexamsystem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfasia.onlineexamsystem.entities.QuestionerDefination;

public interface QuestionerDefinationRepository extends JpaRepository<QuestionerDefination, Long> {
	
	public List<QuestionerDefination> findByexamExamId(Long examId);
	
	
	@Query(value = "insert into questioner_definations"
			+ "(exam_id , teacher_id , course_id , book_id , ch_id , ref_id , qus_limitation) "
			+ "values (:examId, :teacherId, :courseId, :bookId, :chId, :refId, :qusLimitation)",nativeQuery= true)
	public void saveQuesDefination (@Param("examId") Long examId, 
									@Param("teacherId") Long teacherId,
									@Param("courseId") Long courseId,
									@Param("bookId") Long bookId,
									@Param("chId") Long chId,
									@Param("refId") Integer refId,
									@Param("qusLimitation") Long qusLimitation
			);
}
