package com.example.demo.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	@GetMapping("/")
	public String getSlash() {
		return "in opne to all route";
	}
	
	@GetMapping("/user")
	public String getAuthUser() {
		return "WELCOME USER";
	}
	
	@GetMapping("/admin")
	public String getAuthAdmin() {
		return "W	ELCOME ADMIN";
	}

}
