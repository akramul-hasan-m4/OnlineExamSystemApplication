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

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	
	@Column
	@NotEmpty
	private String selectedCourse;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="batch_id")
	private Batch batchs;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User users;
	
	@Column
	private String generatedStId;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="students")
	private Set<ExamInfo> examShedulars = new HashSet<ExamInfo>(0);
	
}
