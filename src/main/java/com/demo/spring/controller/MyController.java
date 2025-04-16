package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.model.User;
import com.demo.spring.service.MyUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MyController {
	
	@Autowired
	private MyUserDetailsService service;
	
	@GetMapping("/hello")
	public String hello(HttpServletRequest request) {
		return "Hello User " + request.getSession().getId();
	}
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.register(user);
	}
}
