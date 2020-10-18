package com.wf.apps.interviewApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGlobalExceptionHandler {
	
	@ExceptionHandler(customExceptions.class)
	public ResponseEntity<customExceptions> fieldValidationHandler(customExceptions customExceptions)
	{
		return new ResponseEntity<customExceptions>(customExceptions,HttpStatus.BAD_REQUEST);
	}

}
