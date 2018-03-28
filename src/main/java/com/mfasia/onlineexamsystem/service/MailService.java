package com.mfasia.onlineexamsystem.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.EmailVerification;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.models.EmailNotification;

@Service
public class MailService {
	
	@Autowired private JavaMailSender javaMailSender;
	@Autowired private EmailVerificationService emailVerificationService;
	@Autowired private UserService userService;
	
	@Value("${spring.mail.username}")
	private String fromAddress;

	public void sendNotification(EmailNotification notification, HttpServletRequest request) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
			User newUser = userService.findByEmailAddress(request.getParameter("email"));
			Long userid = newUser.getUserId();
			String verificationCode =UUID.randomUUID().toString()+userid;
		if (userid != null) {
			
			mailMessage.setTo(notification.getToAddress());
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("Verification Code for Online exam system");
			mailMessage.setText("Your Verification code is = "+verificationCode);
			
			javaMailSender.send(mailMessage);
			
			EmailVerification emailVerification = new EmailVerification();
			User user = new User();
			user.setUserId(userid);
			emailVerification.setUsers(user);
			emailVerification.setVerificationCode(verificationCode);
			emailVerificationService.saveVerificationCode(emailVerification);
		}
			
	}

}
