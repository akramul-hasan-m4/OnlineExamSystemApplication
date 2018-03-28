package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfasia.onlineexamsystem.entities.Role;

public interface RolesRepository extends JpaRepository<Role, Long>{

	public Role findByRoleName(String roleName);
}
