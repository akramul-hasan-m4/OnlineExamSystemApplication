package com.mfasia.onlineexamsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
		.and()
		.authorizeRequests()
		.antMatchers("/courses", "/questionBankPage", "questionerDefinationPage", "questionsPaperPage","/books").authenticated()
		.antMatchers("/courses/p").access("hasRole('ADMIN')") //hasRole("Admin")     //
		.antMatchers("/resources/**","/fonts/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll()
		.and()
		.logout().logoutSuccessUrl("/login").permitAll()
		.and()
		.csrf().disable();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager)
		.userDetailsService(customUserDetailsService);

	}

	
}
