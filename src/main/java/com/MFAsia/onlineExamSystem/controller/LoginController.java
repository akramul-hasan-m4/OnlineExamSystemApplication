package com.MFAsia.onlineExamSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFAsia.onlineExamSystem.repository.UserRepository;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	UserRepository ur;
	
	@GetMapping
	public String login() {
		return "pages/questionBank";
	}
	@RequestMapping("{singup}")
	public String signup(@PathVariable String singup) {
		System.out.println("ooooooooooooooooooooooooooo");
		return "regestration";
	}
}
