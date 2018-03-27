package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "questioner_definations")
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class QuestionerDefination implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long definationId;
	
	@JsonIgnoreProperties({"courses","examDate","totalQuestion","examDuration","totalMark","passMark","handler","hibernateLazyInitializer"})
	@ManyToOne( cascade = CascadeType.DETACH)
	@JoinColumn(name = "exam_id")
	private ExamBoard exam;

	@JsonIgnoreProperties({"users","handler","hibernateLazyInitializer"})
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "teacher_id")
	private Teacher teachers;
	
	@JsonIgnoreProperties({"description" ,"references","exams","books","questionerDefinations","handler", "hibernateLazyInitializer"})
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "course_id")
	private Course courses;

	@Column(name = "book_id")
	private Integer bookId;
	
	@Column(name ="ref_id")
	private Integer refId;
	
	@Column(name ="ch_id")
	private Integer chId;
	
	@Column
	private Long qusLimitation;

}
