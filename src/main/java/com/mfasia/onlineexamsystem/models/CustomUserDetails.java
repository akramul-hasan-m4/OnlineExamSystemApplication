package com.mfasia.onlineexamsystem.models;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mfasia.onlineexamsystem.entities.User;

public class CustomUserDetails extends User implements UserDetails {

	public CustomUserDetails(final User user) {
		super(user);
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getUsersRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoles().getRoleName().toUpperCase()))
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return super.getFirstName();
	}

	public String getUserPassword() {
		return super.getPassword();
	}

	public Long getUserSessionId() {
		return super.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
