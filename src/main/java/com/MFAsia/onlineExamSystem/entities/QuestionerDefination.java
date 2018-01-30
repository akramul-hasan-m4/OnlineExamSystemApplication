package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questioner_definations")
public class QuestionerDefination implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long definationId;
	@Column
	private Long examId;
	@Column
	private Long teacherId;
	@Column
	private Long courseId;
	@Column
	private Long bookId;
	@Column
	private Long refId;
	@Column
	private Long chapterId;
	@Column
	private Long questionLimitation;
	
	public QuestionerDefination() {
		super();
	}
	public QuestionerDefination(Long definationId, Long examId, Long teacherId, Long courseId, Long bookId, Long refId,
			Long chapterId, Long questionLimitation) {
		super();
		this.definationId = definationId;
		this.examId = examId;
		this.teacherId = teacherId;
		this.courseId = courseId;
		this.bookId = bookId;
		this.refId = refId;
		this.chapterId = chapterId;
		this.questionLimitation = questionLimitation;
	}
	public Long getDefinationId() {
		return definationId;
	}
	public void setDefinationId(Long definationId) {
		this.definationId = definationId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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
	public Long getQuestionLimitation() {
		return questionLimitation;
	}
	public void setQuestionLimitation(Long questionLimitation) {
		this.questionLimitation = questionLimitation;
	}
	@Override
	public String toString() {
		return "QuestionerDefination [definationId=" + definationId + ", examId=" + examId + ", teacherId=" + teacherId
				+ ", courseId=" + courseId + ", bookId=" + bookId + ", refId=" + refId + ", chapterId=" + chapterId
				+ ", questionLimitation=" + questionLimitation + "]";
	}
	
	

}
