package com.mfasia.onlineexamsystem.service;

import java.util.ArrayList;
import java.util.List;

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
}
