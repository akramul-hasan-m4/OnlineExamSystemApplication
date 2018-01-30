package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "text_books")
public class TextBook implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	@Column
	private Long courseId;
	@Column
	private String bookName;
	
	public TextBook() {
		super();
	}
	public TextBook(Long bookId, Long courseId, String bookName) {
		super();
		this.bookId = bookId;
		this.courseId = courseId;
		this.bookName = bookName;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	@Override
	public String toString() {
		return "TextBook [bookId=" + bookId + ", courseId=" + courseId + ", bookName=" + bookName + "]";
	}
	
}
