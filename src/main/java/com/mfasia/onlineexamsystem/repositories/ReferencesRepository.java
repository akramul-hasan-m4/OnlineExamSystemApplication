package com.mfasia.onlineexamsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mfasia.onlineexamsystem.entities.Reference;

public interface ReferencesRepository extends JpaRepository<Reference, Long>{

	@Query("select r from Reference r where r.courses.courseId = :courseId")
	public List<Reference> findAllRefByCourseId(@Param ("courseId") Long courseId);
}
