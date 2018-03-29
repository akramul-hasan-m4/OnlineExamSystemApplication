package com.mfasia.onlineexamsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfasia.onlineexamsystem.entities.Student;
import com.mfasia.onlineexamsystem.repositories.StudentsRepository;

@Service
public class StudentsService {

	@Autowired private StudentsRepository studentRepo;
	
	@Transactional
	public Student findStudentByUserId (Long userId) {
		return studentRepo.findByUsersUserId(userId);
	}
	
	@Transactional
	public void saveStudent (Student student) {
		studentRepo.save(student);
	}
	
	@Transactional
	public List<Student> getAllStudents (){
		return studentRepo.findAll();
	}
}
