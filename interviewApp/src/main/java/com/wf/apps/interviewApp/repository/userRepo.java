package com.wf.apps.interviewApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wf.apps.interviewApp.entity.user;

@Repository
public interface userRepo extends JpaRepository<user, Integer>{

	public Integer deleteByMobile(String mobile);
	public user findByMobile(String mobile);
}
