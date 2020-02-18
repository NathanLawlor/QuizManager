package com.nbrown.quizmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="answers")
public class Answer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="answer_id", nullable=false)
	private int id;
	
	@Column(name="question_id", nullable=false)
	private int questionId;
	
	@Column(name="answer", nullable=false)
	private String answer;
	
	@Column(name="correct", nullable=false)
	private int correct;
	
	public Answer() {}
	
	public Answer(int questionId, String answer) {
		this.questionId = questionId;
		this.answer = answer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		if(this.correct == 1) {
			return true;
		}
		return false;
	}
	
	public void setCorrect(boolean correct) {
		if(correct) {
			this.correct = 1;
		}
		else {
			this.correct = 0;
		}
		
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", questionId=" + questionId + ", answer=" + answer + ", correct=" + correct + "]";
	}
	
}
