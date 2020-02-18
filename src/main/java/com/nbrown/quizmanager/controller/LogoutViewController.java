package com.nbrown.quizmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutViewController {

	@GetMapping("/loggedout")
	public String getLogoutView() {
		return "logout";
	}
}
