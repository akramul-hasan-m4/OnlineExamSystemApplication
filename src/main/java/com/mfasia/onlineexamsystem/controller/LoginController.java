package com.mfasia.onlineexamsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Akramul
 */
@Controller
@RequestMapping(LoginController.LOGIN_MAPPING)
public class LoginController {
	
	public static final String LOGIN_MAPPING= "/login";
	
	@GetMapping
	public String login () {
		return "pages/login";
	}
}
