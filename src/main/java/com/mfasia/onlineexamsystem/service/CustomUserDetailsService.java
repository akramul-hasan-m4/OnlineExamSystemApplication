package com.mfasia.onlineexamsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.models.CustomUserDetails;
import com.mfasia.onlineexamsystem.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired private UserRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<User> usersOptional = usersRepository.findByEmail(email);
			return usersOptional
					.map(CustomUserDetails::new)
					.orElseThrow(()->new UsernameNotFoundException("Username not found!"));
	}

}
