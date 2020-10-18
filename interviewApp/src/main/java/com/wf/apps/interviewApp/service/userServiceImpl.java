package com.wf.apps.interviewApp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.apps.interviewApp.dto.userConvertor;
import com.wf.apps.interviewApp.dto.userDto;
import com.wf.apps.interviewApp.entity.user;
import com.wf.apps.interviewApp.repository.interviewRepo;
import com.wf.apps.interviewApp.repository.userRepo;

@Service
@Transactional
public class userServiceImpl implements userService{

	@Autowired
	userRepo userrepository;
	
	@Autowired
	interviewRepo interviewrepository;
	
	@Override
	public userDto addUserService(userDto userDto) {
		// TODO Auto-generated method stub
		user user=userConvertor.userDtoToUserConverted(userDto);
		return userConvertor.userToUserDtoConverter(userrepository.save(user));
	}

	@Override
	public userDto deleteUserService(String mobile) {
		// TODO Auto-generated method stub
		user user=userrepository.findByMobile(mobile);
		if(user==null)
			return null;
		user.getInterviews().forEach(u->u.getUsers().remove(user));
		interviewrepository.saveAll(user.getInterviews());
		userrepository.deleteByMobile(mobile);
		return userConvertor.userToUserDtoConverter(user);
	}

	@Override
	public List<userDto> getAllUsersService() {
		
		List<userDto> list=new ArrayList<userDto>();
		// TODO Auto-generated method stub
		//System.out.println(userrepository.findAll().toString());
		for(user user:userrepository.findAll())
			list.add(userConvertor.userToUserDtoConverter(user));
			
		return list;//Arrays.asList(userrepository.findByMobile("9505911111"));
	}

	@Override
	public boolean isUserPresent(String mobile) {
		// TODO Auto-generated method stub
		if(userrepository.findByMobile(mobile)==null)
			return false;
		return true;
	}

	@Override
	public userDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		user user=userrepository.findById(userId).orElse(null);
		if(user==null)
			return null;
		return userConvertor.userToUserDtoConverter(user);
	}



}
