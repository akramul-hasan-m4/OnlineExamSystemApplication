package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "courses")
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
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
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private List<Reference> references;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private List<Book> books;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses")
	private List<ExamBoard> exams ;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses"  , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionerDefination> questionerDefinations;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="courses" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionsBank> questionBanks ;
}
