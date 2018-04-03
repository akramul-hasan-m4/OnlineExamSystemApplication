package com.mfasia.onlineexamsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long>{

	@Query("select b from Book b where b.courses.courseId = :courseId")
	public List<Book> findAllByCourseId(@Param ("courseId") Long courseId);

}
