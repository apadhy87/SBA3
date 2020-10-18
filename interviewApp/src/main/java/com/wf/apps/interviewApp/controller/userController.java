package com.wf.apps.interviewApp.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wf.apps.interviewApp.dto.userDto;
import com.wf.apps.interviewApp.entity.user;
import com.wf.apps.interviewApp.exceptions.customExceptions;
import com.wf.apps.interviewApp.service.interviewService;
import com.wf.apps.interviewApp.service.userService;

@RestController
public class userController {
	
	@Autowired
	userService userservice;
	@Autowired
	interviewService interviewservice;
	
	//User ops
	
	@PostMapping("/users")
	public ResponseEntity<userDto> addUser(@Valid @RequestBody userDto userDto,BindingResult result) {
		
		if(result.hasErrors())
		{
			customExceptions exception=new customExceptions();
			for(FieldError err:result.getFieldErrors())
			{
				if(exception.getErrorMessage()==null) {
				exception.setErrorCode(err.getCode());
				exception.setErrorMessage(err.getDefaultMessage());
				}
				else
				{
					exception.setErrorCode(exception.getErrorCode()+" || "+err.getCode());
					exception.setErrorMessage(exception.getErrorMessage()+" || "+err.getDefaultMessage());					
				}
			}
			exception.setErrorTimeStamp(LocalDateTime.now());
			throw exception;
		}
		
		return new ResponseEntity<userDto>(userservice.addUserService(userDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{mobile}")
	public ResponseEntity<userDto> deleteUser(@PathVariable("mobile") String mobile) {
		if(!userservice.isUserPresent(mobile))
			throw new customExceptions(LocalDateTime.now(),"Users Exception", "User with Mobile "+mobile+" already Deleted or user does'nt exist");
		return new ResponseEntity<userDto>(userservice.deleteUserService(mobile),HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<userDto>> getAllUsers(){ 
		return new ResponseEntity<List<userDto>>(userservice.getAllUsersService(),HttpStatus.OK);
	}
	


}
