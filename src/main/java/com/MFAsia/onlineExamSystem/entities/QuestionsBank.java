package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "questionsbank")
public class QuestionsBank implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qbId;
	@Column
	private Long courseId;
	@Column(nullable = true)
	private Long bookId;
	@Column(nullable = true)
	private Long refId;
	@Column
	private Long chapterId;
	@Column
	private String teacherIdentificationNo;
	@Column
	private String questionCreatedDate;
	@Column
	private String questionTitle;
	@Column
	private String option1;
	@Column
	private String option2;
	@Column
	private String option3;
	@Column
	private String option4;
	@Column
	private Long correctAnsNo;
	
	public QuestionsBank() {
		super();
	}
	public QuestionsBank(Long qbId, Long courseId, Long bookId, Long refId, Long chapterId,
			String teacherIdentificationNo, String questionCreatedDate, String questionTitle, String option1,
			String option2, String option3, String option4, Long correctAnsNo) {
		super();
		this.qbId = qbId;
		this.courseId = courseId;
		this.bookId = bookId;
		this.refId = refId;
		this.chapterId = chapterId;
		this.teacherIdentificationNo = teacherIdentificationNo;
		this.questionCreatedDate = questionCreatedDate;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnsNo = correctAnsNo;
	}
	public Long getQbId() {
		return qbId;
	}
	public void setQbId(Long qbId) {
		this.qbId = qbId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getRefId() {
		return refId;
	}
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	public Long getChapterId() {
		return chapterId;
	}
	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}
	public String getTeacherIdentificationNo() {
		return teacherIdentificationNo;
	}
	public void setTeacherIdentificationNo(String teacherIdentificationNo) {
		this.teacherIdentificationNo = teacherIdentificationNo;
	}
	public String getQuestionCreatedDate() {
		return questionCreatedDate;
	}
	public void setQuestionCreatedDate(String questionCreatedDate) {
		this.questionCreatedDate = questionCreatedDate;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public Long getCorrectAnsNo() {
		return correctAnsNo;
	}
	public void setCorrectAnsNo(Long correctAnsNo) {
		this.correctAnsNo = correctAnsNo;
	}
	@Override
	public String toString() {
		return "QuestionsBank [qbId=" + qbId + ", courseId=" + courseId + ", bookId=" + bookId + ", refId=" + refId
				+ ", chapterId=" + chapterId + ", teacherIdentificationNo=" + teacherIdentificationNo
				+ ", questionCreatedDate=" + questionCreatedDate + ", questionTitle=" + questionTitle + ", option1="
				+ option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", correctAnsNo="
				+ correctAnsNo + "]";
	}
	
	

}
