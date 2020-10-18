package com.wf.apps.interviewApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wf.apps.interviewApp.entity.interview;

@Repository
public interface interviewRepo extends JpaRepository<interview, Integer>{
	
	public interview findByInterviewName(String technology);
	public List<interview> findAllByInterviewerName(String name);
	public Integer deleteByInterviewName(String technology);
	@Query("SELECT COUNT(x) from interview x")
	public Integer interviewCount();
}
