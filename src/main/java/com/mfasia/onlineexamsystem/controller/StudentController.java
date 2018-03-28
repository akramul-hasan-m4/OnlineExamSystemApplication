package com.mfasia.onlineexamsystem.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.Role;
import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.entities.UserRole;
import com.mfasia.onlineexamsystem.service.RolesService;
import com.mfasia.onlineexamsystem.service.StudentsService;
import com.mfasia.onlineexamsystem.service.UserRolesService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
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
		if(student !=null) {
			String separator = "_";
			String generatedStudentId = student.getSelectedCourse()+separator+student.getBatchs().getBatchId()+separator+student.getUsers().getUserId();
			Student students = new Student();
			students.setBatchs(student.getBatchs());
			students.setUsers(student.getUsers());
			students.setSelectedCourse(student.getSelectedCourse());
			students.setGeneratedStId(generatedStudentId);
			studentsService.saveStudent(students);
			Role role = rolesService.findByRolename("Student");
			if (role != null) {
				UserRole userRole = new UserRole();
				userRole.setRoles(role);
				userRole.setUsers(student.getUsers());
				userRolesService.saveUserRole(userRole);
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

}
