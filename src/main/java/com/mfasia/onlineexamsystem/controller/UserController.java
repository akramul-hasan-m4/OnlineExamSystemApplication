package com.mfasia.onlineexamsystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mfasia.onlineexamsystem.commons.Messages;
import com.mfasia.onlineexamsystem.entities.User;
import com.mfasia.onlineexamsystem.models.EmailNotification;
import com.mfasia.onlineexamsystem.service.MailService;
import com.mfasia.onlineexamsystem.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired private UserService userService;
	@Autowired private MessageSource msgSource ;
	@Autowired private MailService mailService ;
	
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
	
	@PostMapping()
	public ResponseEntity<Object> saveUserRegistrationInfo(HttpServletRequest request, @RequestParam("file") MultipartFile photo) throws IOException {
		Optional<Optional<User>> email = userService.findByEmail(request.getParameter("email"));
		HttpHeaders headers = new HttpHeaders();
		if (!email.isPresent()) {
			headers.add(Messages.ERROR_MSG, msgSource.getMessage("commons.getAllErrorMsg", null, null));
			return new ResponseEntity<>(headers,HttpStatus.OK);
		}
		userService.saveUserRegistrationInfo(request, photo, uploadPath ());
		EmailNotification notification = new EmailNotification();
		notification.setToAddress(request.getParameter("email"));
		mailService.sendNotification(notification);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private String uploadPath () throws UnsupportedEncodingException {
		String mainPath = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"uploads"+File.separator;
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split(File.separator+"target"+File.separator+"classes"+File.separator);
		LOGGER.info(pathArr[0]);
		return new File(pathArr[0]).getPath()+mainPath;
	}
}
