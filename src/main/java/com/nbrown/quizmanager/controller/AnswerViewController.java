package com.nbrown.quizmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbrown.quizmanager.service.AnswerService;
import com.nbrown.quizmanager.service.QuestionService;
import com.nbrown.quizmanager.service.QuizService;

@Controller
@RequestMapping("/quizzes")
public class AnswerViewController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;

	@GetMapping("/{id}/questions/answer/view")
	public String getAnswerViewForQuestion(@PathVariable(name="id") String quizId, 
			@RequestParam(name="questionId") String questionId, Model model) {
		model.addAttribute("quiz", quizService.getQuizById(quizId));
		model.addAttribute("question", questionService.getQuestionById(questionId));
		model.addAttribute("answerList", answerService.getAnswersForQuestion(questionId));
		return "answerView";
	}
	
	@GetMapping("/{id}/questions/answer/add")
	public String getAddAnswerView(Model model,
			@PathVariable(name="id") String quizId, 
			@RequestParam(name="questionId") String questionId) {
		model.addAttribute("quiz", quizService.getQuizById(quizId));
		model.addAttribute("question", questionService.getQuestionById(questionId));
		return "addAnswerView";
	}
	
	@GetMapping("/{id}/questions/answer/edit")
	public String getEditAnswerView(Model model,
			@PathVariable(name="id") String quizId, 
			@RequestParam(name="questionId") String questionId, 
			@RequestParam(name="answerId") String answerId) {
		model.addAttribute("quiz", quizService.getQuizById(quizId));
		model.addAttribute("question", questionService.getQuestionById(questionId));
		model.addAttribute("answer", answerService.getAnswerById(answerId));
		return "editAnswerView";
	}
}
