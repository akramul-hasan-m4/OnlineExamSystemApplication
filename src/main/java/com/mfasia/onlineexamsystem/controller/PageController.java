package com.mfasia.onlineexamsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/qb")
	public String qb() {
		return "pages/questionerDefination";
	}
}
