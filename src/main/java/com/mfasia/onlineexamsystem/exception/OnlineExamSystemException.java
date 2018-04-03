package com.mfasia.onlineexamsystem.exception;


public class OnlineExamSystemException extends Exception{

	private static final long serialVersionUID = 1L;

	public OnlineExamSystemException() {
		super();
	}

	public OnlineExamSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OnlineExamSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public OnlineExamSystemException(String message) {
		super(message);
	}

	public OnlineExamSystemException(Throwable cause) {
		super(cause);
	}
	
}
