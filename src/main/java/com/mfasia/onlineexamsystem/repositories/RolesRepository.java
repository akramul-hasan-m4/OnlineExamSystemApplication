package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long>{

	public Role findByRoleName(String roleName);
}
