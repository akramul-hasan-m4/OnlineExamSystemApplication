package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "questioner_definations")
@Data
public class QuestionerDefination implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long definationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private ExamBoard exam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teachers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course courses;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book books;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ref_id")
	private Reference reference;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ch_id")
	private Chapter chapters;
	
	@Column
	private Long qusLimitation;

}
