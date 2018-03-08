package com.mfasia.onlineexamsystem.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.Course;
import com.mfasia.onlineexamsystem.service.CourseService;

@RestController
@RequestMapping("/courses")

public class CouseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_Admin')")
	public List<Course> getAllCourse () {
		return courseService.getAllCourse();
	}
	
	@GetMapping("/p")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Principal getprinc (Principal p) {
		return p;
	}
}
