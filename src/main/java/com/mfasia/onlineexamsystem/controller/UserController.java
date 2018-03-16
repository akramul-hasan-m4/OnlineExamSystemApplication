package com.mfasia.onlineexamsystem.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService userService;
	
	@PostMapping()
	public ResponseEntity<Object> saveUserRegistrationInfo (
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("phone") String phone,
			@RequestParam("gender") String gender,
			@RequestParam("currentAddress") String currentAddress,
			@RequestParam("permanentAddress") String permanentAddress,
			@RequestParam("securityQuestion") String securityQuestion,
			@RequestParam("securityAns") String securityAns,
			@RequestParam("file") MultipartFile file) throws IOException{
		
		String photo = file.getOriginalFilename();
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setPhoto(photo);
		user.setCurrentAddress(currentAddress);
		user.setPermanentAddress(permanentAddress);
		user.setSecurityQuestion(securityQuestion);
		user.setSecurityAns(securityAns);
		user.setStatus("Active");
		FileOutputStream fout = null ;
		
		try {
			File convertFile = new File("/home/metafour/Desktop"+photo);
			convertFile.createNewFile();
			fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
		}catch (Exception e) {
		} finally {
			if (fout != null) {
				fout.close();
			}
			
		}
		
		
		userService.saveUserRegistrationInfo(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public List<User> getAllUser (){
		return userService.getAllUser();
	}

}
