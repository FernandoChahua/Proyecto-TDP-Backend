package com.softtech.tdp.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.service.ISpecialistRequestService;

@RestController
@RequestMapping("/specialist-request")
public class SpecialistRequestController {
	
	@Autowired
	private ISpecialistRequestService specialistRequestService;
	
	@PostMapping
	public ResponseEntity<SpecialistRequest> registerRequest(@RequestBody SpecialistRequest body, final HttpServletRequest req){
		body = specialistRequestService.create(body);
		return ResponseEntity
				.created(URI.create(req.getRequestURI().toString().concat("/").concat(body.getIdSpecialistRequest().toString())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);
	}
	
	@GetMapping
	public ResponseEntity<List<SpecialistRequest>> findAllRequests(){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(specialistRequestService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SpecialistRequest> findById(@PathVariable("id")Integer id){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(specialistRequestService.findById(id));
	}
}
