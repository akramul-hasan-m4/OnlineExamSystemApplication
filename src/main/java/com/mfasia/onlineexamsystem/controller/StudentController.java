package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.Role;
import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.entities.UserRole;
import com.mfasia.onlineexamsystem.service.RolesService;
import com.mfasia.onlineexamsystem.service.StudentsService;
import com.mfasia.onlineexamsystem.service.UserRolesService;

/**
 * @author Akramul
 */
@RestController
@RequestMapping(StudentController.STUDENT_MAPPING)
public class StudentController {
	
	public static final String STUDENT_MAPPING= "/student";
	
	@Autowired private StudentsService studentsService;
	@Autowired private MessageSource msgSource ;
	@Autowired private UserRolesService userRolesService;
	@Autowired private RolesService rolesService;
	
	@PostMapping
	public ResponseEntity<Student> saveStudent(@RequestBody Student student, BindingResult result) {
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		if(student.getUsers() !=null) {
			User user = new User();
			user.setUserId(student.getUsers().getUserId());
			String separator = "_";
			String generatedStudentId = student.getSelectedCourse()+separator+student.getBatchs().getBatchId()+separator+student.getUsers().getUserId();
			Student students = new Student();
			students.setBatchs(student.getBatchs());
			students.setUsers(user);
			students.setSelectedCourse(student.getSelectedCourse());
			students.setGeneratedStId(generatedStudentId);
			Role role = rolesService.findByRolename("Student");
			if (role != null) {
				UserRole userRole = new UserRole();
				userRole.setRoles(role);
				userRole.setUsers(user);
				userRolesService.saveUserRole(userRole);
				studentsService.saveStudent(students);
			}
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(student.getStudentId()).toUri();
			headers.setLocation(location);
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
			return ResponseEntity.created(location).headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
		return ResponseEntity.noContent().headers(headers).build();
	} 

	@GetMapping
	public ResponseEntity<List<Student>> getAllstudents () {
		List<Student> list = studentsService.getAllStudents();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
