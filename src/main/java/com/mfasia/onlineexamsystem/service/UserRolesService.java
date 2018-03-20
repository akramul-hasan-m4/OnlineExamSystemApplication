package com.mfasia.onlineexamsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.UserRole;
import com.mfasia.onlineexamsystem.repositories.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired private UserRolesRepository userRoleRepo ;
	
	@Transactional
	public List<UserRole> getAllUserRole (){
		return userRoleRepo.findAll();
	}
	
	@Transactional
	public void saveUserRole (UserRole userRole) {
		userRoleRepo.save(userRole);
	}
	
	@Transactional
	public Optional<UserRole> findByUserRoleid (Long userRoleId) {
		return Optional.of(userRoleRepo.findOne(userRoleId));
	}
	
	@Transactional
	public void deleteUserRole (Long userroleId) {
		userRoleRepo.delete(userroleId);
	}
}
