package com.mfasia.onlineexamsystem.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.UserRole;
import com.mfasia.onlineexamsystem.service.UserRolesService;

/**
 * @author Akramul
 */
@RestController
@RequestMapping(UserRolesController.USERS_ROLES_MAPPING)
public class UserRolesController {

	public static final String USERS_ROLES_MAPPING= "/users_roles";
	
	@Autowired private UserRolesService userRoleService ;
	@Autowired private MessageSource msgSource ;
	
	@GetMapping
	public ResponseEntity<List<UserRole>> getAlluserRoles () {
		List<UserRole> list = userRoleService.getAllUserRole();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<UserRole> saveUserRole(@RequestBody UserRole userRole, BindingResult result) {
		userRoleService.saveUserRole(userRole);
		HttpHeaders headers = new HttpHeaders();
		if (result.hasErrors()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.saveErrorMsg", null, null));
			return ResponseEntity.noContent().headers(headers).build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userRole.getUserRoleid()).toUri();
		headers.setLocation(location);
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.saveSuccessMsg", null, null));
		return ResponseEntity.created(location).headers(headers).build();
	}
	
	@PutMapping("/{userRoleId}")
	public ResponseEntity<UserRole> updateUserRole (@RequestBody UserRole userRole, @PathVariable Long userRoleId) {
		Optional<UserRole> findUserRole = userRoleService.findByUserRoleid(userRoleId);
		HttpHeaders headers = new HttpHeaders();
		if (!findUserRole.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.findByidErrorMsg", null, null)+userRoleId);
			return ResponseEntity.notFound().headers(headers).build();
		}
		headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.updatemsg", null, null));
		userRoleService.saveUserRole(userRole);
		return ResponseEntity.noContent().headers(headers).build();
	}
	
	@DeleteMapping("/{userRoleId}")
	public ResponseEntity<Void> deleteUserRole (@PathVariable Long userRoleId) {
		Optional<UserRole> finduserRole = userRoleService.findByUserRoleid(userRoleId);
		HttpHeaders headers = new HttpHeaders();
		if(finduserRole.isPresent()) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			userRoleService.deleteUserRole(userRoleId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
}
