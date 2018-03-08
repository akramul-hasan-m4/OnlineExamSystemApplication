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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns =
	@JoinColumn(name = "user_id"), inverseJoinColumns =
	@JoinColumn(name = "role_id"))
	@JsonIgnore
	private List<UserRole> usersRoles;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private List<Student> students;

	public User(User user) {
		super();
		this.userId = user.userId;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.email = user.email;
		this.phone = user.phone;
		this.photo = user.photo;
		this.password = user.password;
		this.gender = user.gender;
		this.currentAddress = user.currentAddress;
		this.permanentAddress = user.permanentAddress;
		this.status = user.status;
		this.securityQuestion = user.securityQuestion;
		this.securityAns = user.securityAns;
		this.teacherses = user.teacherses;
		this.usersRoles = user.usersRoles;
		this.students = user.students;
	}

	public User() {
		super();
	}
	
	

}
