package com.nbrown.quizmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nbrown.quizmanager.service.AnswerService;

@RestController
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@PostMapping("/submitAnswer")
	public void submitAnswer(@RequestParam("questionId") String questionId,
			@RequestParam("answer") String answer,
			@RequestParam("correct") String correct) {
		answerService.submitAnswer(questionId, answer, correct);
	}
	
	@PutMapping("/updateAnswer")
	public void updateAnswer(@RequestParam("answerId") String answerId,
			@RequestParam("answer") String answer) {
		answerService.updateAnswer(answerId, answer);
	}
	
	@DeleteMapping("/removeAnswer")
	public void removeAnswer(@RequestParam("answerId") String answerId) {
		answerService.removeAnswer(answerId);
	}
}
