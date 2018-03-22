package com.mfasia.onlineexamsystem.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mfasia.onlineexamsystem.entities.QuestionsBank;

public interface QuestionBankRepository extends CrudRepository<QuestionsBank, Long>{

	@Query(value ="SELECT b FROM QuestionsBank b WHERE "
			+ "b.courses.courseId =:courseId AND ((b.bookId IS :bookId AND b.chId IS :chId )"
			+ " OR b.refId IS :refId) ORDER BY RANDOM()")
	public List<QuestionsBank> getInfoForQuestionPaper (@Param ("courseId") Long courseId, @Param ("bookId") String bookId, @Param ("chId") String chId, @Param ("refId") String refId, Pageable pageable);

	@Query(value = "SELECT al FROM QuestionsBank al WHERE qusBankId =:qusBankId AND ans =:ans")
	public QuestionsBank countResult (@Param ("qusBankId") Long qusBankId, @Param ("ans") Integer ans);
}
