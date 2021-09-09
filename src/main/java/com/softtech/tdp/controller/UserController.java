package com.softtech.tdp.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.tdp.dto.RequestSignUp;
import com.softtech.tdp.dto.ResponsePatientProfile;
import com.softtech.tdp.dto.ResponseSignUp;
import com.softtech.tdp.dto.ResponseSpecialistProfile;
import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.service.IPatientService;
import com.softtech.tdp.service.ISpecialistService;
import com.softtech.tdp.service.impl.AppUserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private AppUserServiceImpl userService;
	
	@Lazy
	@Autowired
	private ISpecialistService specialistService;
	
	
	@Autowired
	private IPatientService patientService;
	
	@PostMapping("/register-patient")
	public ResponseEntity<ResponseSignUp> registerRequest(@RequestBody RequestSignUp body, final HttpServletRequest req){
		ResponseSignUp response = userService.signUpPatient(body);
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(response);
	}
	
	
	@PutMapping("/status-online/{id}/{status}")
	public ResponseEntity<Void> changeStatusOnline(@PathVariable("id")Long idUser,@PathVariable("status")boolean status){
		AppUser userBD = userService.findById(idUser);
		if(userBD.getId() != null) {
			userService.changeStatusOnline(idUser,status);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/patient-profile/{id}")
	public ResponseEntity<ResponsePatientProfile> getPatientProfile(@PathVariable("id")Integer idPatient){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(patientService.getProfileByPatientId(idPatient));
	}
	
	@GetMapping("/specialist-profile/{id}")
	public ResponseEntity<ResponseSpecialistProfile> getSpecialistProfile(@PathVariable("id")Integer idSpecialist){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(specialistService.getSpecialistProfileById(idSpecialist));
	}
	
}
