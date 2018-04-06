package com.mfasia.onlineexamsystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.EmailVerification;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.models.EmailNotification;
import com.mfasia.onlineexamsystem.service.EmailVerificationService;
import com.mfasia.onlineexamsystem.service.MailService;
import com.mfasia.onlineexamsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired private UserService userService;
	@Autowired private MessageSource msgSource ;
	@Autowired private MailService mailService ;
	@Autowired private EmailVerificationService emailVerificationService ;
	
	@GetMapping
	public ResponseEntity<List<User>> getAlluser () {
		List<User> list = userService.getAllUser();
		HttpHeaders headers = new HttpHeaders();
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
		if (list.isEmpty()) {
			return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveUserRegistrationInfo(HttpServletRequest request, @RequestParam("file") MultipartFile photo) throws IOException {
		User email = userService.findByEmailAddress(request.getParameter("email"));
		HttpHeaders headers = new HttpHeaders();
		if (email != null) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.userFound", null, null));
			return new ResponseEntity<>(headers,HttpStatus.OK);
		}
		userService.saveUserRegistrationInfo(request, photo, uploadPath ());
		EmailNotification notification = new EmailNotification();
		notification.setToAddress(request.getParameter("email"));
		mailService.sendNotification(notification, request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/code/{verificationCode}")
	public ResponseEntity<EmailVerification> verifyEmail (@PathVariable String verificationCode) {
		EmailVerification vCode = emailVerificationService.findByVerificationCode(verificationCode);
		HttpHeaders headers = new HttpHeaders();	
		if (vCode != null) {
			EmailVerification emailVerification = new EmailVerification();
			User user = new User ();
			user.setUserId(vCode.getUsers().getUserId());
			emailVerification.setVerificationId(vCode.getVerificationId());
			emailVerification.setUsers(user);
			emailVerification.setVerificationCode(verificationCode);
			emailVerification.setVerificationStatus("Verified");
			emailVerificationService.saveVerificationCode(emailVerification);
			User userinfo = userService.findByuserId(vCode.getUsers().getUserId());
			userService.updateUserStatus(userinfo);
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.emailVerified", null, null));
			return new ResponseEntity<>(vCode,headers,HttpStatus.OK);
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.emailNotVerified", null, null));
		return new ResponseEntity<>(headers,HttpStatus.NOT_FOUND);
	}
	
	private String uploadPath () throws UnsupportedEncodingException {
		String mainPath = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"uploads"+File.separator;
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split(File.separator+"target"+File.separator+"classes"+File.separator);
		LOGGER.info(pathArr[0]);
		return new File(pathArr[0]).getPath()+mainPath;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/userInfo")
	public ResponseEntity<User> userProfileInfo (Authentication authentication, OAuth2Authentication oauthentication){
		User user = null;
		try {
			user = (User) authentication.getPrincipal();
		}catch (Exception e) {
			LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) oauthentication.getUserAuthentication().getDetails();
			String email = (String) properties.get("email");
			user = userService.findByEmailAddress(email);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser (@PathVariable Long userId) {
		User user = userService.findByuserId(userId);
		HttpHeaders headers = new HttpHeaders();
		if(user != null) {
			headers.add(Messages.SUCCESS_MSG, msgSource.getMessage("commons.deleteSuccessMsg", null, null));
			userService.deleteUser(userId);
			return ResponseEntity.noContent().headers(headers).build();
		}
		headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.deleteFailedMsg ", null, null));
		return ResponseEntity.notFound().headers(headers).build();
	}
}
