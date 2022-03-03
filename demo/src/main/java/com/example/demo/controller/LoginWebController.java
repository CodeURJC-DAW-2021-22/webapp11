package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {
	
	@RequestMapping("/login.html")
	public String login() {
		return "login";
	}

	@RequestMapping("/loginerror.html")
	public String loginerror() {
		return "loginerror";
	}
}
