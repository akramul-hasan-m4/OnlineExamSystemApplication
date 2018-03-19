package com.mfasia.onlineexamsystem.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.models.EmailNotification;

@Service
public class MailService {
	
	@Autowired private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromAddress;

	public void sendNotification(EmailNotification notification) {
		

		SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setTo(notification.getToAddress());
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("Verification Code for Online exam system");
			mailMessage.setText("Your Verification code is = "+UUID.randomUUID().toString());
			
			javaMailSender.send(mailMessage);
	}

}
