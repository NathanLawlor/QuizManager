package com.nbrown.quizmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nbrown.quizmanager.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	public Quiz getQuizById(int quizId);
}
