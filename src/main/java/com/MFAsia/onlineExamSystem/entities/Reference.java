package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "references")
public class Reference implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long refId;
	@Column
	private Long courseId;
	@Column
	private String reference;
	
	public Reference() {
		super();
	}
	public Reference(Long refId, Long courseId, String reference) {
		super();
		this.refId = refId;
		this.courseId = courseId;
		this.reference = reference;
	}
	public Long getRefId() {
		return refId;
	}
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	@Override
	public String toString() {
		return "Reference [refId=" + refId + ", courseId=" + courseId + ", reference=" + reference + "]";
	}
	
}
