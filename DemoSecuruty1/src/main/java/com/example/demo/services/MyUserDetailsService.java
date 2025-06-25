package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.MyUser;
import com.example.demo.repository.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser myUser = userRepo.findByName(username).orElseThrow(() -> new UsernameNotFoundException("the username entered is not valied!"));	 
		
		return new MyUserDetails(username, myUser.getPassword(), myUser.getRole(), myUser.isActive());
	}

}
