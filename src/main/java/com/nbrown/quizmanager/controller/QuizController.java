package com.nbrown.quizmanager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbrown.quizmanager.model.Quiz;
import com.nbrown.quizmanager.service.QuizService;

@Controller
@RequestMapping("/quizzes")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping(value="/create", consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String submitQuiz(Quiz quiz, HttpServletResponse response) throws IOException {
		quizService.createQuiz(quiz);
		response.sendRedirect("/quizzes");
		return "quizView";
	}
	
	@ResponseBody
	@DeleteMapping("/removeQuiz")
	public void removeQuiz(@RequestParam("quizId") String quizId) {
		quizService.removeQuiz(quizId);
	}
	
}
