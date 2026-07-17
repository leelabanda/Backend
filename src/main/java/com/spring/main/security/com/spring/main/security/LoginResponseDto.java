package com.spring.main.security.com.spring.main.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
	private String token;
	//private String role;
	private Long userId;
}
