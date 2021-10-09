package com.softtech.tdp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.softtech.tdp.dto.RequestEditProfileDTO;
import com.softtech.tdp.dto.RequestSignUp;
import com.softtech.tdp.dto.ResponsePatientProfile;
import com.softtech.tdp.dto.ResponseSignUp;
import com.softtech.tdp.dto.ResponseSpecialistProfile;
import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.model.Specialist;
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
	public ResponseEntity<ResponseSignUp> registerRequest(@Valid @RequestBody RequestSignUp body, final HttpServletRequest req){
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
	
	@PutMapping("/patient-edit/{id}")
	public ResponseEntity<ResponsePatientProfile> editPatientProfile(@PathVariable("id")Integer idPatient,@RequestBody RequestEditProfileDTO request){
		Patient patientBD = patientService.findById(idPatient);
		
		if( patientBD.getIdPatient() != null) {
			patientBD.setBornDate(request.getBornDate());
			patientBD.setFirstName(request.getFirstName());
			patientBD.setLastName(request.getLastName());
			
			patientService.update(patientBD);
			
			
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(patientService.getProfileByPatientId(idPatient));
		}
		return new ResponseEntity<ResponsePatientProfile>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/specialist-edit/{id}")
	public ResponseEntity<ResponseSpecialistProfile> editSpecialistProfile(@PathVariable("id")Integer idSpecialist,@RequestBody RequestEditProfileDTO request){
		Specialist specialistBD = specialistService.findById(idSpecialist);
		
		if( specialistBD.getIdSpecialist() != null) {
			specialistBD.setBornDate(request.getBornDate());
			specialistBD.setFirstName(request.getFirstName());
			specialistBD.setLastName(request.getLastName());
			
			specialistService.update(specialistBD);
			
			
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(specialistService.getSpecialistProfileById(idSpecialist));
		}
		return new ResponseEntity<ResponseSpecialistProfile>(HttpStatus.NOT_FOUND);
	}
	
}
