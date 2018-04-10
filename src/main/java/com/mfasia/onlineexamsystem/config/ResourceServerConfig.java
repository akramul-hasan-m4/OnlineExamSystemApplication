package com.mfasia.onlineexamsystem.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Akramul
 */
@Configuration
@EnableOAuth2Client
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{

	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserDetailsService customUserDetailsService;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired private DataSource dataSource;
	@Autowired OAuth2ClientContext oauth2ClientContext;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
		.and()
			.authorizeRequests()
			.antMatchers("/pages/teachersPanel", "/pages/questionBank", "/pages/questionerDefination").hasAnyAuthority("ROLE_TEACHER")
			.antMatchers("/pages/adminPanel", "/pages/book", "/pages/courseDetails", "/pages/examBoard").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/resources/**","/fonts/**", "/customJS/**").permitAll()
			.antMatchers("/pages/regestration","/pages/emailVerification","/user/code/**","/user/save","/courses","/batch","/student").permitAll()
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error=true")
			.successHandler(new CustomAuthenticationSuccessHandler())
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedPage("/pages/403")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
			.permitAll()
		.and()
			.rememberMe()
			.rememberMeCookieName("online-exam-system-remember-me")
			.tokenValiditySeconds(24 * 60 * 60)
			.tokenRepository(persistentTokenRepository())
		.and()
			.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
			.csrf()
			.disable();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager)
		.userDetailsService(customUserDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	private Filter ssoFilter() {
		OAuth2ClientAuthenticationProcessingFilter gmailFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/gmail");
		gmailFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(){
			@Override public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				this.setDefaultTargetUrl("/pages/gmailUserInfo");
				super.onAuthenticationSuccess(request, response, authentication);
			}});
		
		OAuth2RestTemplate gmailTemplate = new OAuth2RestTemplate(gmail(), oauth2ClientContext);
		gmailFilter.setRestTemplate(gmailTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(gmailResource().getUserInfoUri(), gmail().getClientId());
		tokenServices.setRestTemplate(gmailTemplate);
		gmailFilter.setTokenServices(tokenServices);
		return gmailFilter;
	}
	
	@Bean
	@ConfigurationProperties("security.oauth2.client")
	public AuthorizationCodeResourceDetails gmail() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("security.oauth2.resource")
	public ResourceServerProperties gmailResource() {
		return new ResourceServerProperties();
	}
}
