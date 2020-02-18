package com.nbrown.quizmanager.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbrown.quizmanager.model.Question;
import com.nbrown.quizmanager.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
    private QuestionRepository questionRepo;
	
	@Autowired
    private AnswerService answerService;
	
	public List<Question> getQuestionsForQuiz(String id) {
		int quizId = Integer.parseInt(id);
		return questionRepo.getQuestionsByQuizId(quizId);
	}
	
	public Question getQuestionById(String id) {
		int questionId = Integer.parseInt(id);
		return questionRepo.getOne(questionId);
	}

	public void createQuestion(String quizIdString, String questionText, String answers) {
		int quizIdInt = Integer.parseInt(quizIdString);
		Question newQuestion = new Question(quizIdInt, questionText);
		
		questionRepo.save(newQuestion);
		
		List<Question> questions = questionRepo.getQuestionsByQuizId(quizIdInt);
		int questionId = questions.get(questions.size() - 1).getId();
		JSONArray jsonAnswers = new JSONArray(answers);

		answerService.saveAnswers(questionId, jsonAnswers);
		
	}

	public void removeQuestion(String id) {
		int questionId = Integer.parseInt(id);
		answerService.removeAnswersFromQuestion(questionId);
		questionRepo.deleteById(questionId);
	}

	public void updateQuestion(String questionid, String question) {
		int questionId = Integer.parseInt(questionid);
		Question questionToUpdate = questionRepo.getOne(questionId);
		questionToUpdate.setQuestion(question);
		questionRepo.save(questionToUpdate);
	}

}
