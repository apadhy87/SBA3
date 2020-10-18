package com.wf.apps.interviewApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class userDto {
	
	private Integer userId;
	@NotBlank(message = "First Name should'nt be blank.")
	@Length(min = 5,max = 30,message = "First Name should be min 5 and max 30 characters.")
	private String firstName;
	@NotBlank(message = "Last Name should'nt be blank.")
	@Length(min = 3,max = 25,message = "Last Name should be min 3 and max 25 characters.")
	private String lastName;
	@NotBlank(message = "email should'nt be blank")
	@Email(message = "Enter appropriate email id.")
	private String email;
	@NotBlank(message = "Cell no should'nt be empty")
	@Length(min = 10,max = 10,message = "cell no should be exactly a size of 10 integers from [0-10) <mathematial inclusive/exclusive> not starting with 0.")
	private String mobile;
}
