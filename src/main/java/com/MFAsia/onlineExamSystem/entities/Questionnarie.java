package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Questionnarie implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long quesId;
	@Column
	private Long examId;
	@Column
	private Long qbId;
	@Column
	private Long studentId;
	@Column
	private Long correctAnsNo;
	
	public Questionnarie() {
		super();
	}
	public Questionnarie(Long quesId, Long examId, Long qbId, Long studentId, Long correctAnsNo) {
		super();
		this.quesId = quesId;
		this.examId = examId;
		this.qbId = qbId;
		this.studentId = studentId;
		this.correctAnsNo = correctAnsNo;
	}
	public Long getQuesId() {
		return quesId;
	}
	public void setQuesId(Long quesId) {
		this.quesId = quesId;
	}
	public Long getExamId() {
		return examId;
	}
	public void setExamId(Long examId) {
		this.examId = examId;
	}
	public Long getQbId() {
		return qbId;
	}
	public void setQbId(Long qbId) {
		this.qbId = qbId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getCorrectAnsNo() {
		return correctAnsNo;
	}
	public void setCorrectAnsNo(Long correctAnsNo) {
		this.correctAnsNo = correctAnsNo;
	}
	@Override
	public String toString() {
		return "Questionnarie [quesId=" + quesId + ", examId=" + examId + ", qbId=" + qbId + ", studentId=" + studentId
				+ ", correctAnsNo=" + correctAnsNo + "]";
	}
	
}
