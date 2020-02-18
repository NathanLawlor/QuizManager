package com.nbrown.quizmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbrown.quizmanager.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Integer> {
	public List<Question> getQuestionsByQuizId(int quizId);
}
