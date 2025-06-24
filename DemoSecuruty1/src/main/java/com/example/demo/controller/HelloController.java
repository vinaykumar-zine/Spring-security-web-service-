package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HelloController {

	@GetMapping("/")
	public String homeController() {
		return "this is hoem controller";
	}
	
	@GetMapping("/area")
	public String homeAreaController() {
		return "this is hoem area controller";
	}
}
