package com.mfasia.onlineexamsystem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepo ;
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private static final String SEPARATOR = "_" ;
	
	@Transactional
	public void saveUserRegistrationInfo (HttpServletRequest request, MultipartFile photo, String uploadPath) throws IOException {
		String firstName = request.getParameter("firstName");
		String photoName = firstName + SEPARATOR + photo.getOriginalFilename();
		String encodedPassword = passwordEncoder.encode(request.getParameter("password"));
		User user = new User();
		
		user.setFirstName(firstName);
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(encodedPassword);
		user.setPhone(request.getParameter("phone"));
		user.setPhoto(photoName);
		user.setGender(request.getParameter("gender"));
		user.setCurrentAddress(request.getParameter("currentAddress"));
		user.setPermanentAddress(request.getParameter("permanentAddress"));
		user.setSecurityQuestion(request.getParameter("securityQuestion"));
		user.setSecurityAns(request.getParameter("securityAns"));
		
		File dir = new File(uploadPath+photoName);
		boolean createNewFile = dir.createNewFile();
		FileOutputStream fout = new FileOutputStream(dir);

		try {
			if (createNewFile) {
				fout.write(photo.getBytes());
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		} finally {
			fout.close();
		}
		
		userRepo.save(user);
	}
	
	@Transactional
	public List<User> getAllUser (){
		return userRepo.findAll();
	}
	
	@Transactional
	public List<User> login (String email, String password){
		return userRepo.customlogin(email, password);
	}
	
	@Transactional
	public Optional<Optional<User>> findByEmail (String email){
		return Optional.of(userRepo.findByEmail(email));
	}
	
	@Transactional
	public User findByEmailAddress (String email){
		return userRepo.findByEmailAddress(email);
	}
	
	@Transactional
	public User findByuserId (long userId) {
		return userRepo.findOne(userId);
	}
	
	public void updateUserStatus (User usr) {
		User user = new User();
		user.setUserId(usr.getUserId());
		user.setFirstName(usr.getFirstName());
		user.setLastName(usr.getLastName());
		user.setEmail(usr.getEmail());
		user.setPassword(usr.getPassword());
		user.setPhone(usr.getPhone());
		user.setPhoto(usr.getPhoto());
		user.setGender(usr.getGender());
		user.setCurrentAddress(usr.getCurrentAddress());
		user.setPermanentAddress(usr.getPermanentAddress());
		user.setSecurityQuestion(usr.getSecurityQuestion());
		user.setSecurityAns(usr.getSecurityAns());
		user.setStatus("Active");
		
		userRepo.save(user);
	}
}

