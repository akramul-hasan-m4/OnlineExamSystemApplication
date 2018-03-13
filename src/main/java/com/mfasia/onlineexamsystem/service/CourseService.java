package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.Course;
import com.mfasia.onlineexamsystem.repositories.CourseRepository;
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepo;
	
	@Transactional
	public List<Course> getAllCourse () {
		List<Course> list= new ArrayList<>();
		courseRepo.findAll().forEach(list::add);
		return list;
	}
	
	@Transactional
	public void savCourse (Course course) {
		courseRepo.save(course);
	}
	
	@Transactional
	public Optional<Course> findBycourseId (Long courseId){
		return Optional.of(courseRepo.findOne(courseId));
	}
	
	@Transactional
	public void deleteCourse (Long courseId) {
		courseRepo.delete(courseId);
	}
}
