package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long>{

	public Student findByUsersUserId (Long userId);
}
