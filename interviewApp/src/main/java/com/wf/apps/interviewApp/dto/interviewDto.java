package com.wf.apps.interviewApp.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.wf.apps.interviewApp.entity.user;

import lombok.Data;

@Data
public class interviewDto {
	
	private Integer interviewId;
	@NotBlank(message = "Interviewer Name should'nt be blank.")
	@Length(min = 5,max = 30,message = "Interviewer Name should be min 5 and max 30 characters.")
	private String interviewerName;
	@NotBlank(message = "Interview Name should'nt be blank.")
	@Length(min = 3,max = 30,message = "Interview Name should be min 3 and max 30 characters.")
	private String interviewName;
	private List<String> userSkills;
	@NotBlank(message = "Interview Status should'nt be blank.")
	@Length(min = 5,max = 30,message = "Interview status should be min 5 and max 100 characters.")
	private String interviewStatus;
	@NotBlank(message = "Remarks should'nt be blank.")
	@Length(min = 5,max = 30,message = "Remarks should be min 5 and max 100 characters.")
	private String remarks;	
	private LocalTime time;
	private LocalDate date;
	private List<userDto> users;

}
