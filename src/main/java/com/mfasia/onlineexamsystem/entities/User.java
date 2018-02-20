package com.mfasia.onlineexamsystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String photo;
	
	@Column
	private String password;
	
	@Column
	private String gender;
	
	@Column
	private String currentAddress;
	
	@Column
	private String permanentAddress;
	
	@Column
	private String status;

	@Column
	private String securityQuestion;

	@Column
	private String securityAns;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teacher> teacherses ;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<UserRole> usersRoleas;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<Student> students;

}
