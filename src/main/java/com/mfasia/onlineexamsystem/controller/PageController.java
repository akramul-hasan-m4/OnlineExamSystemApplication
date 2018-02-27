package com.mfasia.onlineexamsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/questionBankPage")
	public String questionBankPage () {
		return "pages/questionBank";
	}
	
	@GetMapping("/questionsPaperPage")
	public String questionsPaperPage () {
		return "pages/questionsPaper";
	}
	
	@GetMapping("/questionerDefinationPage")
	public String questionerDefinationpage () {
		return "pages/questionerDefination";
	}
}
