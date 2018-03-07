package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long roleId;
	
	@Column
	private String roleName;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="roles")	
	private List<UserRole> usersRoleas;
	
}
