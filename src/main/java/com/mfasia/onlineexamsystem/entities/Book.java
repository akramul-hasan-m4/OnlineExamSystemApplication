package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;
import java.util.List;

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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "books")
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long bookId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course courses;
	
	@Column
	@Size(min = 2, max = 20 , message = "Books Name should be {min} charecter to {max} charecter")
	private String bookName;
	
	@Column
	@Size(max = 20 , message = "Author name should be {max} charecter")
	private String authorName;
	
	@Column
	private String edition;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="books")
	private List<Chapter> chapters;
	
	/*@JsonIgnore
	@OneToMany( mappedBy="books", cascade = CascadeType.ALL)
	private List<QuestionerDefination> questionerDefinations;*/
}
