package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Entity
@Table(name = "questioner_definations")
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class QuestionerDefination implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long definationId;
	
	@JsonUnwrapped
	@JsonIgnoreProperties({"courses","examDate","totalQuestion","examDuration","totalMark","passMark","handler","hibernateLazyInitializer"})
	@ManyToOne()
	@JoinColumn(name = "exam_id")
	private ExamBoard exam;

	@JsonUnwrapped
	@JsonIgnoreProperties({"users","handler","hibernateLazyInitializer"})
	@ManyToOne()
	@JoinColumn(name = "teacher_id")
	private Teacher teachers;
	
	@JsonUnwrapped
	@JsonIgnoreProperties({"courseName","description"})
	@ManyToOne()
	@JoinColumn(name = "course_id")
	private Course courses;

	@JsonUnwrapped
	@JsonIgnoreProperties({"courses","bookName","authorName","edition"})
	@ManyToOne()
	@JoinColumn(name = "book_id")
	private Book books;
	
	@JsonUnwrapped
	@JsonIgnoreProperties({"courses","handler","hibernateLazyInitializer","referenceHeader"})
	@ManyToOne()
	@JoinColumn(name = "ref_id")
	private Reference ref;
	
	@JsonUnwrapped
	@JsonIgnoreProperties({"books","chapterName","handler","hibernateLazyInitializer"})
	@ManyToOne()
	@JoinColumn(name = "ch_id")
	private Chapter chapters;
	
	@Column
	private Long qusLimitation;

}
