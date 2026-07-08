package com.spring.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.main.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long>{
	UserInfo findByEmail(String email);
	
}
