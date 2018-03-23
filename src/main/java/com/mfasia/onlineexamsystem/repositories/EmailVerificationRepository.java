package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.EmailVerification;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Integer>{

	public EmailVerification findByVerificationCode(String verificationCode); 
}
