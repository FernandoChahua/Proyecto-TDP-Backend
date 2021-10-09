package com.softtech.tdp.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.model.SpecialistRequestState;
import com.softtech.tdp.service.ISpecialistRequestService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/specialist-request")
@Validated
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
				.body(specialistRequestService.findAll().stream()
						.map(e -> {
							e.setPassword("");
							return e;
							})
						.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SpecialistRequest> findById(@PathVariable("id") @Min(value= 1,message="El id debe ser mayor a 0.")Integer id){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(specialistRequestService.findById(id));
	}
	
	@GetMapping("/state/{state}")
	public ResponseEntity<List<SpecialistRequest>> findByState(@PathVariable("state")SpecialistRequestState state){
	
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(specialistRequestService.findByState(state).stream()
						.map(e -> {
							e.setPassword("");
							return e;
							})
						.collect(Collectors.toList()));
	}
	
	@PutMapping("/changeState/{state}/{id}")
	public ResponseEntity<SpecialistRequest> update(@PathVariable("state") SpecialistRequestState state,@Valid @NotNull @PathVariable("id")Integer id) throws Exception{
		SpecialistRequest body = specialistRequestService.updateState(id, state);
		body.setPassword("");
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(body);
	}
}
