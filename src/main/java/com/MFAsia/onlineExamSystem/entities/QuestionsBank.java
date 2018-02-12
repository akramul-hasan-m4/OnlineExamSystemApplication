package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Entity
@Table(name = "questions_bank")
@Data

public class QuestionsBank implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qusBankId;

	@JsonManagedReference
	@JsonIgnoreProperties({"courseName" , "description" ,"references", "references","exams","books","questionerDefinations","handler", "hibernateLazyInitializer"})
	@JsonUnwrapped
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course courses;

	@Column(nullable = true)
	private String bookId;

	@Column(nullable = true)
	private String refId;

	@Column
	private String chId;

	@JsonManagedReference
	@JsonIgnoreProperties({"users" ,"questionerDefinations","handler","hibernateLazyInitializer"})
	@JsonUnwrapped
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teachers;

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
	private Long ans;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "questionBank")
	@JsonIgnore
	private Set<QuestionPaper> questionpapers = new HashSet<QuestionPaper>(0);

}
