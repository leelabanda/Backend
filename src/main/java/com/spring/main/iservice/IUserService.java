package com.spring.main.iservice;

import java.util.List;

import com.spring.main.entity.com.spring.main.entity.Person;
import com.spring.main.entity.com.spring.main.entity.UserInfo;

public interface IUserService {
	UserInfo save(UserInfo users);
	UserInfo findByEmail(String email);
	void sendOtp(String email);
	void delete(Long id);
	List<UserInfo> getAll();
}
