package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teacherId;
	@Column
	private Boolean isExaminer;
	
	public Teacher() {
		super();
	}
	public Teacher(Long teacherId, Boolean isExaminer) {
		super();
		this.teacherId = teacherId;
		this.isExaminer = isExaminer;
	}
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public Boolean getIsExaminer() {
		return isExaminer;
	}
	public void setIsExaminer(Boolean isExaminer) {
		this.isExaminer = isExaminer;
	}
	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", isExaminer=" + isExaminer + "]";
	}
	
}
