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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="batchs")
	private Set<Student> students = new HashSet<Student>(0);
	
}
