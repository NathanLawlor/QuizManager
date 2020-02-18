package com.nbrown.quizmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbrown.quizmanager.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository <Answer, Integer>{

	List<Answer> getAnswersByQuestionId(int questionId);

	@Transactional
	void deleteAnswersByQuestionId(@Param("question_id")int questionId);
}
