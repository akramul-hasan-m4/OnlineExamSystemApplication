package com.MFAsia.onlineExamSystem.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	@Column
	private String roleName;
	
	public Role() {
		super();
	}
	public Role(Long roleId, String rolename) {
		super();
		this.roleId = roleId;
		this.roleName = rolename;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRolename() {
		return roleName;
	}
	public void setRolename(String rolename) {
		this.roleName = rolename;
	}
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", rolename=" + roleName + "]";
	}
	
}
