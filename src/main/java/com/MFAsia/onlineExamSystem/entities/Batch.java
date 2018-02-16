package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "batchs")
@Data
public class Batch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long batchId;
	
	@Column
	@NotEmpty
	private Long batchNo;
	
	@Column
	@NotEmpty
	private Long seatLimit;
	
	@JsonBackReference
	@OneToMany(fetch=FetchType.LAZY, mappedBy="batchs")
	private List<Student> students;
	
}
