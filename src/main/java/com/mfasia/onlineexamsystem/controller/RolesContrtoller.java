package com.mfasia.onlineexamsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfasia.onlineexamsystem.entities.Role;
import com.mfasia.onlineexamsystem.service.RolesService;


@RestController
@RequestMapping("/roles")
public class RolesContrtoller {

	@Autowired private RolesService rolesService;
	
	@GetMapping
	public List<Role> getAllrole (){
		return rolesService.getAllRole();
	}
}
