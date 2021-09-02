package com.softtech.tdp.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.tdp.dto.RequestSignUp;
import com.softtech.tdp.dto.ResponseSignUp;
import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.service.ISpecialistRequestService;
import com.softtech.tdp.service.impl.AppUserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private AppUserServiceImpl userService;
	
	@PostMapping("/register-patient")
	public ResponseEntity<ResponseSignUp> registerRequest(@RequestBody RequestSignUp body, final HttpServletRequest req){
		ResponseSignUp response = userService.signUpPatient(body);
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
}
