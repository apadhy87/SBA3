package com.wf.apps.interviewApp.service;

import java.util.List;

import com.wf.apps.interviewApp.dto.userDto;
import com.wf.apps.interviewApp.entity.user;

public interface userService {
	
	public userDto addUserService(userDto userDto);
	public userDto deleteUserService(String mobile);
	public List<userDto> getAllUsersService();
	//public boolean isUserPresent(Long mobile);
	public boolean isUserPresent(String mobile);
	public userDto getUserById(Integer userId);

}
