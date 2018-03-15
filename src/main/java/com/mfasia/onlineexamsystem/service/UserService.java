package com.mfasia.onlineexamsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepo ;
	
	@Transactional
	public void saveUserRegistrationInfo (User user) {
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
}
