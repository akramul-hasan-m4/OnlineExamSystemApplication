package com.mfasia.onlineexamsystem.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.service.UserService;

@RestController
@RequestMapping("/googleUser")
public class GoogleUserController {

	@Autowired private UserService userService;
	
	@GetMapping
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> checkUser (OAuth2Authentication authentication){
		LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
		String email = (String) properties.get("email");
		User usr = userService.findByEmailAddress(email);
		if (usr != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	@PostMapping
	@SuppressWarnings("unchecked")
	public ResponseEntity<User> saveGoogleUserInfo (@RequestBody User user, OAuth2Authentication authentication){
		LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
		String email = (String) properties.get("email");
		String name = (String) properties.get("name");
		String picture = (String) properties.get("picture");
		User usr = userService.findByEmailAddress(email);
		if (usr != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		user.setEmail(email);
		user.setFirstName(name);
		user.setPassword("google");
		user.setStatus("Active");
		user.setPhoto(picture);
		user.setSecurityQuestion("who are you ?");
		user.setSecurityAns("i am google user");
		userService.saveGoogleUserInfo(user);
		User userinfo = userService.findByEmailAddress(email);
		return new ResponseEntity<>(userinfo,HttpStatus.OK);
	}
}
