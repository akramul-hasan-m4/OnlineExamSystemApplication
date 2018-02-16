package com.MFAsia.onlineExamSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MFAsia.onlineExamSystem.entities.Book;

public interface BooksRepository extends JpaRepository<Book, Long>{

	@Query("select b from Book b where b.courses.courseId = :courseId")
	public List<Book> findAllByCourseId(@Param ("courseId") Long courseId);

}
