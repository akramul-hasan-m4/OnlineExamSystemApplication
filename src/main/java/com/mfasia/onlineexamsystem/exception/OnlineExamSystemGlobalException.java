package com.mfasia.onlineexamsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OnlineExamSystemGlobalException extends Exception{

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(OnlineExamSystemException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public String globalException(OnlineExamSystemException e){
		return e.getMessage();
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String nullPointerException(NullPointerException e){
		return "Null pointer Message = "+e.getMessage();
	}
	
	@ExceptionHandler(InterruptedException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String interruptedException(InterruptedException e){
		return "Null pointer Message = "+e.getMessage();
	}
}
