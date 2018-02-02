package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chapters")
public class Chapter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long chapterId;
	@Column
	private Long bookId;
	@Column
	private String chapterName;
	
	public Chapter() {
		super();
	}
	public Chapter(Long chapterId, Long bookId, String chapterName) {
		super();
		this.chapterId = chapterId;
		this.bookId = bookId;
		this.chapterName = chapterName;
	}
	public Long getChapterId() {
		return chapterId;
	}
	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}
	public Long getSubjectId() {
		return bookId;
	}
	public void setSubjectId(Long bookId) {
		this.bookId = bookId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", bookId=" + bookId + ", chapterName=" + chapterName + "]";
	}
	
}
