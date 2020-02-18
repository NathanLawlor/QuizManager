package com.nbrown.quizmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbrown.quizmanager.service.QuizService;

@Controller
@RequestMapping("/quizzes")
public class QuizViewController {
	
	@Autowired
	private QuizService quizService;
	
	@GetMapping
	public String getQuizView(Model model) {
		model.addAttribute("quizList", quizService.getAllQuizzes());
		return "quizView";
	}

	@GetMapping("/create")
	public String getCreateQuizView() {
		return "createQuizView";
	}
	
}
