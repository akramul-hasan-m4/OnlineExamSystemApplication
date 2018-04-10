package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.Course;
import com.mfasia.onlineexamsystem.service.CourseService;

/**
 * @author Akramul
 */
@RestController
@RequestMapping(CourseController.COURSE_MAPPING)
public class CourseController {

	public static final String COURSE_MAPPING= "/courses";
	
	@Autowired private CourseService courseService;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses () {
		List<Course> list = courseService.getAllCourse();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Course> saveCourse(@RequestBody Course course, BindingResult result) {
		courseService.savCourse(course);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(course.getCourseId()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	} 
	
	@PutMapping("/{courseId}")
	public ResponseEntity<Course> updateCourse (@RequestBody Course course, @PathVariable Long courseId) {
		Optional<Course> findCourse = courseService.findBycourseId(courseId);
		HttpHeaders headers = new HttpHeaders();
		if (!findCourse.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+courseId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		courseService.savCourse(course);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Void> deleteCourse (@PathVariable Long courseId) {
		Optional<Course> findCourse = courseService.findBycourseId(courseId);
		HttpHeaders headers = new HttpHeaders();
		if(courseId != null && findCourse.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			courseService.deleteCourse(courseId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
}
