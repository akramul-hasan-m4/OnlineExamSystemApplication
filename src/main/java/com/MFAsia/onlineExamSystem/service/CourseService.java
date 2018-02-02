package com.MFAsia.onlineExamSystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MFAsia.onlineExamSystem.entities.Course;
import com.MFAsia.onlineExamSystem.repository.CourseRepository;
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
}
