package com.MFAsia.onlineExamSystem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.MFAsia.onlineExamSystem.entities.QuestionsBank;

public interface QuestionBankRepository extends CrudRepository<QuestionsBank, Long>{

	@Query(value ="SELECT b FROM QuestionsBank b WHERE "
			+ "b.courses.courseId =:courseId AND ((b.bookId =:bookId AND b.chId =:chId )"
			+ " OR b.refId =:refId) ORDER BY RANDOM()")
	public List<Object> getInfoForQuestionPaper (@Param ("courseId") Long courseId, @Param ("bookId") String bookId, @Param ("chId") String chId, @Param ("refId") String refId, Pageable pageable);
}
