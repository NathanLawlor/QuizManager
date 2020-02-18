package com.nbrown.quizmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="question_id")
	private int id;
	
	@Column(name="quiz_id", nullable=false)
	private int quizId;
	
	@Column(name="question")
	private String question;

	public Question() {}
	
	public Question(int quizId, String question) {
		this.quizId = quizId;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public int getQuizId() {
		return quizId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", quizId=" + quizId + ", question=" + question + "]";
	}
	
}
