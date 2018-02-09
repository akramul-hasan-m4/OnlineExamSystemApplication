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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "books")
@Data
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
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="books")
	private Set<Chapter> chapters = new HashSet<Chapter>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="books")
	private Set<QuestionerDefination> questionerDefinations = new HashSet<QuestionerDefination>(0);
}
