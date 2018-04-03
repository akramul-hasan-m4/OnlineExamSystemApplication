package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "exam_info")
@Data
public class ExamInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long infoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student students;

	@Column
	private String generatedStId;

	@Column
	private Time startTime;

	@Column
	private Time endTime;

	@Column
	@JsonFormat(pattern ="yyyy-MM-dd")
	private Date date;

	@Column
	private String score;

	@Column
	private String grade;
}
