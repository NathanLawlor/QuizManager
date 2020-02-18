package com.nbrown.quizmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbrown.quizmanager.model.Question;
import com.nbrown.quizmanager.model.Quiz;
import com.nbrown.quizmanager.repository.QuestionRepository;
import com.nbrown.quizmanager.repository.QuizRepository;


@Service
public class QuizService {
	
	@Autowired
    private QuestionService questionService;
	
	@Autowired
    private QuizRepository quizRepo;
	
	@Autowired
    private QuestionRepository questionRepo;

	public List<Quiz> getAllQuizzes() {
		return quizRepo.findAll();
	}
	
	public void createQuiz(Quiz quiz){
		quizRepo.save(quiz);
    }

	public Quiz getQuizById(String id) {
		int quizId = Integer.parseInt(id);
		return quizRepo.getQuizById(quizId);
	}

	public void removeQuiz(String id) {
		int quizId = Integer.parseInt(id);

		for(Question question : questionRepo.getQuestionsByQuizId(quizId)) {
			questionService.removeQuestion(String.valueOf(question.getId()));
		}
		quizRepo.deleteById(quizId);
	}
}
