package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Entity
@Table(name = "exam_board")
@Data
public class ExamBoard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long examId;

	@JsonIgnoreProperties({"description"})
	@JsonUnwrapped
	@ManyToOne()
	@JoinColumn(name = "course_id")
	private Course courses;

	@Column
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date examDate;

	@Column
	private Long totalQuestion;

	@Column
	private String examDuration;

	@Column
	private Integer totalMark;

	@Column
	private Integer passMark;
	
	@Column
	private String examStatus;

	@JsonIgnore
	@OneToMany( mappedBy = "exam")
	private List<QuestionerDefination> questionerDefinations;
}
