package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	@Column
	private String selectedCourse;
	@Column
	private String batchNo;
	
	public Student() {
		super();
	}
	public Student(Long studentId, String selectedCourse, String batchNo) {
		super();
		this.studentId = studentId;
		this.selectedCourse = selectedCourse;
		this.batchNo = batchNo;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getSelectedCourse() {
		return selectedCourse;
	}
	public void setSelectedCourse(String selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", selectedCourse=" + selectedCourse + ", batchNo=" + batchNo + "]";
	}
	
}
