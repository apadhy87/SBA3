package com.wf.apps.interviewApp.service;

import java.util.List;

import javax.validation.Valid;

import com.wf.apps.interviewApp.dto.interviewAttendees;
import com.wf.apps.interviewApp.dto.interviewCountDto;
import com.wf.apps.interviewApp.dto.interviewDto;
import com.wf.apps.interviewApp.entity.interview;

public interface interviewService {
	
	public interviewDto saveInterviewDetail(interviewDto interviewDto);
	public interviewDto addAttendeesDetail(String interviewName,Integer userId);
	public interviewDto getAttendees(String intName);
	public interviewDto getInterview(String technology);
	public interviewDto deleteInterview(String technology);
	public List<interviewDto> getAllInterviews();
	public interviewDto modifyStatus(String interviewName, String status);
	public interviewCountDto getInterviewCount();
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId);
	public List<interviewDto> getInterviewByInterviewor(String interviewerName);

}
