package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long examId;
	@Column
	private Long courseId;
	@Column
	private Date examDate;
	@Column
	private Long totalQuestion;
	@Column
	private String examDuration;
	
	public Exam() {
		super();
	}
	public Exam(Long examId, Long courseId, Date examDate, Long totalQuestion, String examDuration) {
		super();
		this.examId = examId;
		this.courseId = courseId;
		this.examDate = examDate;
		this.totalQuestion = totalQuestion;
		this.examDuration = examDuration;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public Long getTotalQuestion() {
		return totalQuestion;
	}
	public void setTotalQuestion(Long totalQuestion) {
		this.totalQuestion = totalQuestion;
	}
	public String getExamDuration() {
		return examDuration;
	}
	public void setExamDuration(String examDuration) {
		this.examDuration = examDuration;
	}
	@Override
	public String toString() {
		return "Exam [examId=" + examId + ", courseId=" + courseId + ", examDate=" + examDate + ", totalQuestion="
				+ totalQuestion + ", examDuration=" + examDuration + "]";
	}
	
}
