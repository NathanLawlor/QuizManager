package com.nbrown.quizmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nbrown.quizmanager.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/submitQuestion")
	public void submitQuestion(@RequestParam("quizId") String quizId, 
			@RequestParam("question") String question,
			@RequestParam("answers") String answers) {
		questionService.createQuestion(quizId, question, answers);
	}
	
	@PutMapping("/updateQuestion")
	public void updateQuestion(@RequestParam("questionId") String questionId,
			@RequestParam("question") String question) {
		questionService.updateQuestion(questionId, question);
	}
	
	@DeleteMapping("/removeQuestion")
	public void removeQuestion(@RequestParam("questionId") String questionId) {
		questionService.removeQuestion(questionId);
	}
	
}
