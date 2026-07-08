package com.spring.main.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.main.entity.UserInfo;
import com.spring.main.repo.DashBoardRepo;
import com.spring.main.repo.UserInfoRepo;
import com.spring.main.security.JwtService;

@Service
public class UserSecurity implements UserDetailsService{
	@Autowired
	private UserInfoRepo personRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo existingUser=personRepo.findByEmail(username);
        if (existingUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(existingUser.getEmail())
                .password(existingUser.getPassword())
                .authorities("USER")// encoded password
                .build();
    }

}
