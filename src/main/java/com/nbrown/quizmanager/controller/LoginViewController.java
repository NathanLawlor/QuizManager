package com.nbrown.quizmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginViewController {
	
	@GetMapping("/login")
	public String getLoginView(Model model, 
			@RequestParam(value="error", required=false) String isError) {
		if(null!=isError && isError.equals("true")) {
			model.addAttribute("loginMessage", "Invalid Username or Password");
		}
		else {
			model.addAttribute("loginMessage", "Enter Username and Password to Login");
		}
		return "login";
	}
}
