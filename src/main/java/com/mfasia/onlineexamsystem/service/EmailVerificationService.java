package com.mfasia.onlineexamsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfasia.onlineexamsystem.entities.EmailVerification;
import com.mfasia.onlineexamsystem.repositories.EmailVerificationRepository;

@Service
public class EmailVerificationService {

	@Autowired private EmailVerificationRepository emailVerificationRepository;
	
	@Transactional
	public EmailVerification findByVerificationCode (String verificationCode) {
		return emailVerificationRepository.findByVerificationCode(verificationCode);
	}
	
	@Transactional
	public void saveVerificationCode (EmailVerification emailVerification) {
		emailVerificationRepository.save(emailVerification);
	}
}
