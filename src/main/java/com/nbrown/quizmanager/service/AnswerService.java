package com.nbrown.quizmanager.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbrown.quizmanager.model.Answer;
import com.nbrown.quizmanager.repository.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
    private AnswerRepository answerRepo;

	public List<Answer> getAnswersForQuestion(String id) {
		int questionId = Integer.parseInt(id);
		return answerRepo.getAnswersByQuestionId(questionId);
	}
	
	public Answer getAnswerById(String answerid) {
		int answerId = Integer.parseInt(answerid);
		return answerRepo.getOne(answerId);
	}
	
	public void saveAnswers(int questionId, JSONArray jsonAnswers) {
		jsonAnswers.forEach(entry -> {
			JSONObject answerObj = new JSONObject(entry.toString());		
			Answer answer = new Answer(questionId, (String) answerObj.get("answer"));
			answer.setCorrect((boolean) answerObj.get("correct"));
			
			System.out.println(answer.toString());
			
			answerRepo.save(answer);
        });
	}
	
	public void submitAnswer(String questionid, String answerValue, String correct) {
		int questionId = Integer.parseInt(questionid);
		boolean isCorrect = Boolean.getBoolean(correct);
		Answer answer = new Answer(questionId, answerValue);
		answer.setCorrect(isCorrect);
		answerRepo.save(answer);
	}
	
	public void updateAnswer(String answerid, String answer) {
		int answerId = Integer.parseInt(answerid);
		Answer answerToUpdate = answerRepo.getOne(answerId);
		answerToUpdate.setAnswer(answer);
		answerRepo.save(answerToUpdate);
	}

	public void removeAnswersFromQuestion(int questionId) {
		answerRepo.deleteAnswersByQuestionId(questionId);	
	}
	
	public void removeAnswer(String id) {
		int answerId = Integer.parseInt(id);
		answerRepo.deleteById(answerId);
	}
	
}
