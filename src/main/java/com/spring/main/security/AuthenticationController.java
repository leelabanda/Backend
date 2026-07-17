package com.spring.main.security;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.entity.com.spring.main.entity.UserInfo;
import com.spring.main.iservice.IUserService;
import com.spring.main.repo.DashBoardRepo;
import com.spring.main.repo.UserInfoRepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://localhost:57238"
})
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private IUserService userService;
	@Autowired
	private UserSecurity userSecurity;
	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserInfoRepo personRepo;
	@GetMapping("/login")
	public String loginPage() {
		return "Hello Welcome.";
	}
	@GetMapping("/login/1")
	public String loginPage1() {
	    return "Hello Welcome Version 2";
	}
	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody UserInfo request) {
		System.out.println("Login API called");
	    System.out.println("Email = " + request.getEmail());
	    System.out.println("Password = " + request.getPassword());
	    System.out.println("BEFORE AUTHENTICATE");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		 System.out.println("AFTER AUTHENTICATE");
		if (authentication.isAuthenticated()) {
			UserDetails userDetails=userSecurity.loadUserByUsername(request.getEmail());
			System.out.println("Login email: " + request.getEmail());
			System.out.println("UserDetails username: " + userDetails.getUsername());
	        String token =
	                jwtService.generateToken(userDetails);

//	       String role =	                userDetails.getAuthorities()
//	                           .iterator()
//	                           .next()
//	                           .getAuthority();
	        UserInfo user = userService.findByEmail(request.getEmail());
	        System.out.println("TOKEN = " + token);
	       // System.out.println("ROLE = " + role);
	        System.out.println("UserId= "+user);
			return  new LoginResponseDto(token,user.getId());
		}
		

		throw new RuntimeException("Invalid Login");
	}

	@PostMapping("/register")
	public String register(@RequestBody UserInfo user) {
	    try {

	        System.out.println("Register API Called");
	        System.out.println(user);

	        userService.save(user);

	        return "User Successfully";

	    } catch (Exception e) {

	        e.printStackTrace();

	        return "Not Registerd";
	    }
	    }
	@GetMapping("/me")
	public UserInfo getCurrentUser(Authentication authentication) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();

	    return userService.findByEmail(email);
	}

	@GetMapping
	public String getMessage(HttpServletRequest request) {
		return "greeting <br/>" + request.getSession().getId();
	}

	@GetMapping("/csrf-token")
	public CsrfToken getToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestBody Map<String, String> request) {

	    String email = request.get("email");

	    userService.sendOtp(email);

	    return "OTP Sent Successfully";
	}
	
}
