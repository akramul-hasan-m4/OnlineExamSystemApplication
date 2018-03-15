package com.mfasia.onlineexamsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService userService;
	
	@PostMapping
	public void saveUserRegistrationInfo (@RequestBody User user) {
		//user.setStatus("Active");
		userService.saveUserRegistrationInfo(user);
	}
	
	@GetMapping
	public List<User> getAllUser (){
		return userService.getAllUser();
	}
}
