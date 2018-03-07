package com.mfasia.onlineexamsystem.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
	
	@GetMapping("pages/{page}")
	public String pageHandler (@PathVariable String page) {
		return "/pages/"+page;
	}
	
}
