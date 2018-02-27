package com.mfasia.onlineexamsystem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfasia.onlineexamsystem.entities.QuestionPaper;

public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Long>{

	@Query(value = "SELECT qp.studentId FROM QuestionPaper qp WHERE qp.studentId =:studentId")
	public Long findByStudentId(@Param("studentId") Long studentId);
	
	@Query(value = "SELECT qp FROM QuestionPaper qp WHERE qp.examId =:examId AND qp.studentId =:studentId")
	public List<QuestionPaper> findByBankId(@Param("examId") Long examId, @Param("studentId") Long studentId);
	
	@Modifying
	@Query(value = "UPDATE question_paper SET mark_question = :markQuestion, collected_ans = :collectedAns "
			+ "WHERE student_id =:studentId AND qus_bank_id =:qusBankId", nativeQuery = true)
	public void collectAns (@Param("markQuestion") Boolean markQuestion ,@Param("collectedAns") Long collectedAns, 
			@Param("studentId") Long studentId, @Param("qusBankId") Long qusBankId);
}
