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
@Table(name = "chapters")
@Data
public class Chapter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long chId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Book books;
	
	@Column
	private String chapterName;
	
	/*@JsonIgnore
	@OneToMany( mappedBy="chapters", cascade = CascadeType.ALL)
	private List<QuestionerDefination> questionerDefinations;*/
	
	
}
