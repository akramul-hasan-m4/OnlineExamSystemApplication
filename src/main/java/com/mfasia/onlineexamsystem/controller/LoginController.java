package com.mfasia.onlineexamsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mfasia.onlineexamsystem.repositories.UserRepository;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserRepository ur;

}
