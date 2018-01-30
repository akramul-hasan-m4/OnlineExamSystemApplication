package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batches")
public class Batch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long batchId;
	@Column
	private Long batchNo;
	@Column
	private Long maxSeatCapacity;
	
	public Batch() {
		super();
	}
	public Batch(Long batchId, Long batchNo, Long maxSeatCapacity) {
		super();
		this.batchId = batchId;
		this.batchNo = batchNo;
		this.maxSeatCapacity = maxSeatCapacity;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	public Long getMaxSeatCapacity() {
		return maxSeatCapacity;
	}
	public void setMaxSeatCapacity(Long maxSeatCapacity) {
		this.maxSeatCapacity = maxSeatCapacity;
	}
	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchNo=" + batchNo + ", maxSeatCapacity=" + maxSeatCapacity + "]";
	}
	
}
