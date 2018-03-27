package com.mfasia.onlineexamsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.Teacher;
import com.mfasia.onlineexamsystem.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired private TeacherRepository teacherRepo;
	
	@Transactional
	public Teacher findByuserId (Long userId) {
		return teacherRepo.findByUsersUserId(userId);
	}
}
