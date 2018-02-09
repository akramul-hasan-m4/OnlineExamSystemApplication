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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	
	@Column
	@NotEmpty
	private String courseName;
	
	@Column
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private Set<Reference> references = new HashSet<Reference>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private Set<Book> books = new HashSet<Book>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private Set<ExamBoard> exams = new HashSet<ExamBoard>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private Set<QuestionerDefination> questionerDefinations = new HashSet<QuestionerDefination>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private Set<QuestionsBank> questionBanks = new HashSet<QuestionsBank>(0);
}
