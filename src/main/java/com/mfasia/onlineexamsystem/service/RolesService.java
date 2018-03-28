package com.mfasia.onlineexamsystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.Role;
import com.mfasia.onlineexamsystem.repositories.RolesRepository;

@Service
public class RolesService {

	@Autowired private RolesRepository rolesRepository;
	
	@Transactional
	public Role findByRolename (String roleName) {
		return rolesRepository.findByRoleName(roleName);
	}
}
