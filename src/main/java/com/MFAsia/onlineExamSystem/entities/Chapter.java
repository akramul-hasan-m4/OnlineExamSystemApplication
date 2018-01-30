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
	private Long subjectId;
	@Column
	private String chapterName;
	
	public Chapter() {
		super();
	}
	public Chapter(Long chapterId, Long subjectId, String chapterName) {
		super();
		this.chapterId = chapterId;
		this.subjectId = subjectId;
		this.chapterName = chapterName;
	}
	public Long getChapterId() {
		return chapterId;
	}
	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", subjectId=" + subjectId + ", chapterName=" + chapterName + "]";
	}
	
}
