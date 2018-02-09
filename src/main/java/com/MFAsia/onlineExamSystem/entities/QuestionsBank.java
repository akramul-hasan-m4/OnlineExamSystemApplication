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

import lombok.Data;


@Entity
@Table(name = "questionsbank")
@Data
public class QuestionsBank implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qusBankId;
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id")
	private Course courses;
	@Column(nullable = true)
	private String bookId;
	@Column(nullable = true)
	private String refId;
	@Column
	private String chId;
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="teacher_id")
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
	@OneToMany(fetch=FetchType.LAZY, mappedBy="questionBank")
	private Set<QuestionPaper> questionpapers = new HashSet<QuestionPaper>(0);
	
}
