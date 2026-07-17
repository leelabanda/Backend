package com.spring.main.controller.com.spring.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.entity.com.spring.main.entity.UserInfo;
import com.spring.main.iservice.IPersonService;
import com.spring.main.iservice.IUserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://localhost:57238",
        "https://leelabanda.github.io"
})
@RequiredArgsConstructor
public class UserController {
	private final IUserService userService;
	@GetMapping
	public List<UserInfo> getAll(){
		return userService.getAll();
	}
}
