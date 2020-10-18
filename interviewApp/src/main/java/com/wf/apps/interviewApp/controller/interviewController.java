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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wf.apps.interviewApp.dto.interviewAttendees;
import com.wf.apps.interviewApp.dto.interviewConvertor;
import com.wf.apps.interviewApp.dto.interviewCountDto;
import com.wf.apps.interviewApp.dto.interviewDto;
import com.wf.apps.interviewApp.entity.interview;
import com.wf.apps.interviewApp.exceptions.customExceptions;
import com.wf.apps.interviewApp.exceptions.userAlreadyAddedExcecption;
import com.wf.apps.interviewApp.service.interviewService;
import com.wf.apps.interviewApp.service.userService;

@RestController
public class interviewController {
	
	@Autowired
	userService userservice;
	@Autowired
	interviewService interviewservice;	

	
	@PostMapping("/interviews")
	public ResponseEntity<interviewDto> addInterview(@Valid @RequestBody interviewDto interviewDto,BindingResult result) {
		
	
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
		
		if(interviewDto.getUserSkills() == null || interviewDto.getUserSkills().size() == 0 || interviewDto.getUserSkills().contains(null) || interviewDto.getUserSkills().contains(""))
		{
			throw new customExceptions(LocalDateTime.now(),"User Skill Error","User Skills should'nt be empty or blank");
		}
		return new ResponseEntity<interviewDto>(interviewservice.saveInterviewDetail(interviewDto),HttpStatus.OK);
	}
	
	@GetMapping("/addAttendees/{interviewName}/{userId}")
	public ResponseEntity<interviewDto> addAttendees(@PathVariable("interviewName") String interviewName,@PathVariable("userId") Integer userId)
	{
		if(interviewservice.getInterview(interviewName)==null || userservice.getUserById(userId)==null)
			throw new customExceptions(LocalDateTime.now(),"Association Exception","Inerview or user doesnt exist");
		
		if(interviewservice.isAttendeeAddedToInterview(interviewName,userId))
			throw new customExceptions(LocalDateTime.now(),"Users Exception","One or more of the user Ids in the request are already added to the "+interviewName+" Interview");
		return new ResponseEntity<interviewDto>(interviewservice.addAttendeesDetail(interviewName,userId),HttpStatus.OK);
	}
	
	@GetMapping("/getAttendeesByInterviewName/{name}")
	public ResponseEntity<interviewDto> getAttendeesList(@PathVariable("name") String intName)
	{
		if(interviewservice.getInterview(intName)==null)
			throw new customExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<interviewDto>(interviewservice.getAttendees(intName),HttpStatus.OK);
	}
	
	@GetMapping("/searchInterviewByInterviewName/{interviewName}")
	public ResponseEntity<interviewDto> searchInterviewByName(@PathVariable("interviewName") String interviewName) {
		interviewDto interview=interviewservice.getInterview(interviewName);
		if(interview==null)
			throw new customExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<interviewDto>(interview,HttpStatus.OK);
	}
	
	@GetMapping("/searchInterviewByIntervieworName/{interviewerName}")
	public ResponseEntity<List<interviewDto>> searchInterview(@PathVariable("interviewerName") String interviewerName) {
		List<interviewDto> interviews=interviewservice.getInterviewByInterviewor(interviewerName);
		if(interviews==null)
			throw new customExceptions(LocalDateTime.now(),"Inexpected interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<List<interviewDto>>(interviews,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeInterview/{interviewName}")
	public ResponseEntity<interviewDto> removeInterview(@PathVariable("interviewName") String interviewName) {
		
		if(searchInterview(interviewName)==null)
			throw new customExceptions(LocalDateTime.now(),"Interview Exception", interviewName+" Interview already Deleted Or does'nt exist");
		return new ResponseEntity<interviewDto>(interviewservice.deleteInterview(interviewName),HttpStatus.OK);
	}
	
	@GetMapping("/interviews")
	public ResponseEntity<List<interviewDto>> searchAllInterviews()
	{
		return new ResponseEntity<List<interviewDto>>(interviewservice.getAllInterviews(),HttpStatus.OK);
	}
	
	@PutMapping("/modifyStatus/{interviewName}/{status}")
	public ResponseEntity<interviewDto> modifyInterviewStatus(@PathVariable("interviewName") String interviewName,@PathVariable("status") String status)
	{
		interviewDto interview=interviewservice.getInterview(interviewName);
		if(interview==null)
			throw new customExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		return new ResponseEntity<interviewDto>(interviewservice.modifyStatus(interviewName,status),HttpStatus.OK);
	}
	
	@GetMapping("/interviewCount")
	public ResponseEntity<interviewCountDto> getInterviewCount()
	{
		return new ResponseEntity<interviewCountDto>(interviewservice.getInterviewCount(),HttpStatus.OK);
	}
}
