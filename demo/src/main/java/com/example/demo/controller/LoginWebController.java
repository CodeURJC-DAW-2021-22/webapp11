package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/loginError")
	public String loginError() {
		return "loginError";
	}
}
