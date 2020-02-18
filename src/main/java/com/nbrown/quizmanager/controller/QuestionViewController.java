package com.nbrown.quizmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbrown.quizmanager.service.QuestionService;
import com.nbrown.quizmanager.service.QuizService;

@Controller
@RequestMapping("/quizzes")
public class QuestionViewController {
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuestionService questionService;

	@GetMapping("/{id}/questions")
	public String getQuestionViewForQuiz(@PathVariable(name="id") String quizId, Model model) {
		model.addAttribute("quiz", quizService.getQuizById(quizId));
		model.addAttribute("questionList", questionService.getQuestionsForQuiz(quizId));
		return "questionView";
	}
	
	@GetMapping("/{id}/questions/add")
	public String getAddQuestionView(@PathVariable(name="id") String quizId, Model model) {
		model.addAttribute("quiz", quizService.getQuizById(quizId));
		return "addQuestionView";
	}
	
	@GetMapping("/{id}/questions/edit")
	public String getEditQuestionsView(@PathVariable(name="id") String quizId, 
			@RequestParam(name="questionId") String questionId, Model model) {
		model.addAttribute("quiz", quizService.getQuizById(quizId));
		model.addAttribute("question", questionService.getQuestionById(questionId));
		return "editQuestionView";
	}
	
}
