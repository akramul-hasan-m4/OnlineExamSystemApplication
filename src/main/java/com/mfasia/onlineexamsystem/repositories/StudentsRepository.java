package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfasia.onlineexamsystem.entities.Student;

public interface StudentsRepository extends JpaRepository<Student, Long>{

	public Student findByUsersUserId (Long userId);
}
