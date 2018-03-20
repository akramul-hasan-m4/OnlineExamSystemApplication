package com.mfasia.onlineexamsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfasia.onlineexamsystem.entities.UserRole;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long>{

}
