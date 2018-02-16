package com.MFAsia.onlineExamSystem.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "books")
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	private Course courses;
	
	@Column
	@NotNull
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
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="books")
	private List<QuestionerDefination> questionerDefinations;
}
