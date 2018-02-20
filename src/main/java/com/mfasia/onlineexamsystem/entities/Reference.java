package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "reference")
@Data
public class Reference implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long refId;

	@ManyToOne()
	@JoinColumn(name = "course_id")
	private Course courses;

	@Column
	private String referenceHeader;

	@JsonIgnore
	@OneToMany(mappedBy = "ref" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionerDefination> questionerDefinations;

}
