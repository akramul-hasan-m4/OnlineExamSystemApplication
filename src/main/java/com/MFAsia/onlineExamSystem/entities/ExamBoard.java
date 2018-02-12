package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "exam_board")
@Data
public class ExamBoard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long examId;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="course_id")
	private Course courses;
	
	@Column
	private Date examDate;
	
	@Column
	private Long totalQuestion;
	
	@Column
	private String examDuration;
	
	@Column
	private String totalMark;
	
	@Column
	private String passMark;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="exam")
	private Set<QuestionerDefination> questionerDefinations = new HashSet<QuestionerDefination>(0);
}
