package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	public Teacher findByUsersUserId (Long userId);
}
