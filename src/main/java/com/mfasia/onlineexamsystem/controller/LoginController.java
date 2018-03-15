package com.mfasia.onlineexamsystem.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.service.UserService;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;

	@GetMapping
	public String login () {
		return "pages/login";
	}
	
	@PostMapping("/{email}/{password}")
	public String loginDb(@PathVariable String email, @PathVariable String password) {
		List<User> list = userService.login(email, password);
		System.out.println(list.get(0).getFirstName());
		if (!list.isEmpty()) {
			return "/pages/questionBank";
		}
		return "/pages/login";
	}
}
