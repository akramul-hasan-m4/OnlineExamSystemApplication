package com.mfasia.onlineexamsystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByFirstName(String firstName);
	
	@Query("SELECT Al from User Al WHERE email = :email AND password =:password")
	public List<User> customlogin (@Param("email") String email, @Param("password") String password);
}
