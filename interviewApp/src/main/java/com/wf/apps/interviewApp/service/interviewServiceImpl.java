package com.wf.apps.interviewApp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.apps.interviewApp.dto.interviewAttendees;
import com.wf.apps.interviewApp.dto.interviewConvertor;
import com.wf.apps.interviewApp.dto.interviewCountDto;
import com.wf.apps.interviewApp.dto.interviewDto;
import com.wf.apps.interviewApp.entity.interview;
import com.wf.apps.interviewApp.entity.user;
import com.wf.apps.interviewApp.repository.interviewRepo;
import com.wf.apps.interviewApp.repository.userRepo;

@Service
@Transactional
public class interviewServiceImpl implements interviewService{

	@Autowired
	public interviewRepo interviewrepository;
	@Autowired
	public userRepo userrepository;

	@Override
	public interviewDto addAttendeesDetail(String interviewName,Integer userId) {
		// TODO Auto-generated method stub
		user user;		
		interview interview=interviewrepository.findByInterviewName(interviewName);
		List<user> interviewusers=interview.getUsers();
		if(interviewusers==null)
			interviewusers=new ArrayList<user>();
			user=userrepository.findById(userId).orElse(null);
			interviewusers.add(user);
		interview.setUsers(interviewusers);
		interviewrepository.save(interview);
		return interviewConvertor.interviewToInterviewDtoConvertor(interview);
	}

	@Override
	public interviewDto getAttendees(String intName) {
		// TODO Auto-generated method stub
		return interviewConvertor.interviewToInterviewDtoConvertor(interviewrepository.findByInterviewName(intName));
	}

	@Override
	public interviewDto getInterview(String technology) {
		// TODO Auto-generated method stub	
		if(interviewrepository.findByInterviewName(technology)==null)
			return null;
		return interviewConvertor.interviewToInterviewDtoConvertor(interviewrepository.findByInterviewName(technology));
	}
	@Override
	public List<interviewDto> getInterviewByInterviewor(String name) {
		// TODO Auto-generated method stub	
		List<interviewDto> list= new ArrayList<interviewDto>();
		if(interviewrepository.findAllByInterviewerName(name)==null)
			return null;
		for(interview interview:interviewrepository.findAllByInterviewerName(name))
		{
			list.add(interviewConvertor.interviewToInterviewDtoConvertor(interview));
		}
		return list;
	}
	
	@Override
	public interviewDto deleteInterview(String technology) {
		// TODO Auto-generated method stub
		interviewDto interviewDto=getInterview(technology);
		interviewrepository.deleteByInterviewName(technology);
		return interviewDto;
	}

	@Override
	public List<interviewDto> getAllInterviews() {
		// TODO Auto-generated method stub
		List<interviewDto> intList=new ArrayList<interviewDto>();
		for(interview item:interviewrepository.findAll()) {
			intList.add(interviewConvertor.interviewToInterviewDtoConvertor(item));
		}
		return intList;
	}

	@Override
	public interviewDto modifyStatus(String interviewName, String status) {
		// TODO Auto-generated method stub
		interview interview=interviewrepository.findByInterviewName(interviewName);
		interview.setInterviewStatus(status);
		return interviewConvertor.interviewToInterviewDtoConvertor(interview);
	}

	@Override
	public interviewCountDto getInterviewCount() {
		// TODO Auto-generated method stub
		interviewCountDto countDto=new interviewCountDto();
		countDto.setNoOfInterviews(interviewrepository.interviewCount());
		return countDto;
	}

	@Override
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId) {
		// TODO Auto-generated method stub
		interview interview=interviewrepository.findByInterviewName(interviewName);
		boolean found=false;
		if(interview!=null)
		{
			List<user> users=interview.getUsers();
			for(user user:users) {
					if(userId==user.getUserid())
						found=true;				
			}
			
		}
		return found;
	}

	@Override
	public interviewDto saveInterviewDetail(interviewDto interviewDto) {
		// TODO Auto-generated method stub
				interview interview=interviewConvertor.interviewDtoToInterviewConvertor(interviewDto);
				interview.setDate(LocalDate.now());
				interview.setTime(LocalTime.now());
				interviewrepository.save(interview); 
				return interviewConvertor.interviewToInterviewDtoConvertor(interview);
				
	}

}
