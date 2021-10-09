package com.softtech.tdp.controller;

import com.softtech.tdp.dto.AssignmentResource;
import com.softtech.tdp.dto.ResponsePatientProfile;
import com.softtech.tdp.dto.SaveAssignmentResource;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.service.IAssignmentService;
import com.softtech.tdp.service.IPatientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IAssignmentService assignmentService;
	
	@Autowired
    IPatientService patientService;

	@PostMapping("/patient/{patientId}/specialist/{specialistId}")
	public AssignmentResource createAssignment(@PathVariable(name = "patientId") Integer patientId,
			@PathVariable(name = "specialistId") Integer specialistId,
			@Valid @RequestBody SaveAssignmentResource resource) {
		return convertToResource(
				assignmentService.createAssignment(patientId, specialistId, convertToEntity(resource)));
	}

	@GetMapping("/patient/{patientId}/specialist/{specialistId}")
	public AssignmentResource getAssignmentByIdAndPatientId(@PathVariable(name = "patientId") Integer patientId,
			@PathVariable(name = "specialistId") Integer specialistId) {
		return convertToResource(assignmentService.getByPatientIdAndSpecialistId(patientId, specialistId));
	}

	@PutMapping("/patient/{patientId}/specialist/{specialistId}")
	public AssignmentResource updateAssignment(@PathVariable(name = "patientId") Integer patientId,
			@PathVariable(name = "specialistId") Integer specialistId,
			@Valid @RequestBody SaveAssignmentResource resource) {
		return convertToResource(
				assignmentService.updateAssignment(patientId, specialistId, convertToEntity(resource)));
	}

	@DeleteMapping("/patient/{patientId}/specialist/{specialistId}")
	public ResponseEntity<?> deleteAssignment(@PathVariable(name = "patientId") Integer patientId,
			@PathVariable(name = "specialistId") Integer specialistId) {
		return assignmentService.deleteAssignment(patientId, specialistId);
	}

	@GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AssignmentResource>> findAllByPatientId(@PathVariable(name="patientId")Integer patientId){
    	return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(assignmentService.findByPatientId(patientId).stream().map(e -> convertToResource(e)).collect(Collectors.toList()));
    }
	
	@GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<AssignmentResource>> findAllBySpecialistId(@PathVariable(name="specialistId")Integer specialistId){
    	return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(assignmentService.findBySpecialistId(specialistId).stream().map(e -> convertToResource(e)).collect(Collectors.toList()));
    }
	
	@PostMapping("/patient/{patientId}/{category}/{score}")
	public ResponseEntity<AssignmentResource> createAssignmentByCategory(@PathVariable(name="patientId")Integer patientId,
																			@PathVariable(name= "category")String category,
																			@PathVariable(name="score")Integer score) throws Exception{
		return ResponseEntity.ok(convertToResource(assignmentService.createAssignmentWithCategry(patientId, category,score)));
	}
	
	
	private Assignment convertToEntity(SaveAssignmentResource resource) {
		return mapper.map(resource, Assignment.class);
	}
	
	

	private AssignmentResource convertToResource(Assignment entity) {
		AssignmentResource response = new AssignmentResource();
		response.setCriticality(entity.getCriticality());
		response.setId(entity.getId());
		response.setRating(entity.getRating());
		response.setStatus(entity.getStatus());
		
		ResponsePatientProfile patientProfile = patientService.getProfileByPatientId(entity.getPatient().getIdPatient());
		
		response.setPatientEmail(patientProfile.getEmail());
		response.setPatientName(
				String.format("%s %s", entity.getPatient().getFirstName(), entity.getPatient().getLastName()));
		response.setSpecialistName(
				String.format("%s %s", entity.getSpecialist().getFirstName(), entity.getSpecialist().getLastName()));
		return response;
	}

}
