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

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	
	@Column
	private String roleName;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="roles")	
	private Set<UserRole> usersRoleas = new HashSet<UserRole>(0);
	
}
