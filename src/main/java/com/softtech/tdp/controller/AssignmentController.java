package com.softtech.tdp.controller;

import com.softtech.tdp.dto.AssignmentResource;
import com.softtech.tdp.dto.SaveAssignmentResource;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.service.IAssignmentService;
import com.softtech.tdp.service.IPatientService;
import com.softtech.tdp.service.ISpecialistService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient/{patientId}/specialist/{specialistId}/assignments")
public class AssignmentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAssignmentService assignmentService;

    @PostMapping
    public AssignmentResource createAssignment(@PathVariable(name = "patientId") Integer patientId,
                                               @PathVariable(name = "specialistId") Integer specialistId,
                                               @Valid @RequestBody SaveAssignmentResource resource){
        return convertToResource(assignmentService.createAssignment(patientId, specialistId, convertToEntity(resource)));
    }

    @GetMapping()
    public AssignmentResource getAssignmentByIdAndPatientId(@PathVariable(name = "patientId") Integer patientId,
                                                            @PathVariable(name = "specialistId") Integer specialistId){
        return convertToResource(assignmentService.getByPatientIdAndSpecialistId(patientId, specialistId));
    }

    @PutMapping()
    public AssignmentResource updateAssignment(@PathVariable(name = "patientId") Integer patientId,
                                               @PathVariable(name = "specialistId") Integer specialistId,
                                               @Valid @RequestBody SaveAssignmentResource resource){
        return convertToResource(assignmentService.updateAssignment(patientId, specialistId,
                convertToEntity(resource)));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAssignment(@PathVariable(name = "patientId") Integer patientId,
                                              @PathVariable(name = "specialistId") Integer specialistId){
        return assignmentService.deleteAssignment(patientId, specialistId);
    }

    private Assignment convertToEntity(SaveAssignmentResource resource){ return mapper.map(resource, Assignment.class); }

    private AssignmentResource convertToResource(Assignment entity){ return mapper.map(entity, AssignmentResource.class); }


}
