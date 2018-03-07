package com.mfasia.onlineexamsystem.entities;

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
@Table(name = "question_paper")
@Data
public class QuestionPaper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long qusId;

	@Column
	private Long examId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qus_bank_id")
	private QuestionsBank questionBank;

	@Column
	private Long studentId;

	@Column
	private Boolean markQuestion;

	@Column
	private Long collectedAns;

}
