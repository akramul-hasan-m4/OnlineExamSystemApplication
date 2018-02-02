package com.MFAsia.onlineExamSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MFAsia.onlineExamSystem.entities.Course;
import com.MFAsia.onlineExamSystem.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CouseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public List<Course> getAllCourse () {
		return courseService.getAllCourse();
	}
}
