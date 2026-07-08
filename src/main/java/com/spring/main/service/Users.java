package com.spring.main.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.main.entity.Person;
import com.spring.main.entity.UserInfo;
import com.spring.main.iservice.IUserService;
import com.spring.main.repo.DashBoardRepo;
import com.spring.main.repo.PersonRepository;
import com.spring.main.repo.UserInfoRepo;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class Users implements IUserService {
	private final UserInfoRepo userRepo;
	private final PasswordEncoder passwordEncoder;
//	private final PersonRepository personRepo;
	@Override
	public UserInfo save(UserInfo users) {
		// TODO Auto-generated method stub
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return userRepo.save(users);
	}

	@Override
	public UserInfo findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public void sendOtp(String email) {
		// TODO Auto-generated method stub
		UserInfo user=findByEmail(email);
		String otp=String.valueOf(1000+new Random().nextInt(900000));
		System.out.println("OTP--> " +otp);
		//personRepo.save(otp);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		UserInfo user=userRepo.findById(id).orElseThrow(()->new RuntimeException("Id Not Found"));
		userRepo.delete(user);
	}

	@Override
	public List<UserInfo> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}
}
