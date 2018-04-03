package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{

	public Course findByCourseName(String courseName);
}
