package com.mfasia.onlineexamsystem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.QuestionPaper;

@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Long>{

	@Query(value = "SELECT qp.studentId FROM QuestionPaper qp WHERE qp.studentId =:studentId")
	public Long findByStudentId(@Param("studentId") Long studentId);
	
	@Query(value = "SELECT qp FROM QuestionPaper qp WHERE qp.examId =:examId AND qp.studentId =:studentId")
	public List<QuestionPaper> findByBankId(@Param("examId") Long examId, @Param("studentId") Long studentId);
	
	@Modifying
	@Query(value = "UPDATE question_paper SET collected_ans = :collectedAns "
			+ "WHERE student_id =:studentId AND qus_bank_id =:qusBankId", nativeQuery = true)
	public void collectAns (@Param("collectedAns") Integer collectedAns, 
							@Param("studentId") Integer studentId, 
							@Param("qusBankId") Integer qusBankId);
	
	@Query(value ="select count(qus_bank_id) from question_paper where student_id =:studentId", nativeQuery = true)
	public int countQuesForEachStudent (@Param("studentId") Long studentId);
	
	@Query("SELECT COUNT(q) FROM QuestionPaper q WHERE q.studentId =:studentId")
	public Long countt(@Param("studentId") Long studentId);
	
	public Long countByStudentId(Long studentId);
	
}
