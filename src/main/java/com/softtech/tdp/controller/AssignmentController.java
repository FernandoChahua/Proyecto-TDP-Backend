package com.softtech.tdp.controller;

import com.softtech.tdp.dto.AssignmentResource;
import com.softtech.tdp.dto.SaveAssignmentResource;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.service.IAssignmentService;
import com.softtech.tdp.service.IPatientService;
import com.softtech.tdp.service.ISpecialistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patients")
public class AssignmentController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAssignmentService assignmentService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private ISpecialistService specialistService;

    @PostMapping("/{patientId}/assignments")
    public AssignmentResource createAssignment(@PathVariable(name = "patientId") Integer patientId,
                                               Integer specialistId,
                                               @Valid @RequestBody SaveAssignmentResource resource){
        return convertToResource(assignmentService.createAssignment(patientId, specialistId, convertToEntity(resource)));
    }

    

    private Assignment convertToEntity(SaveAssignmentResource resource){ return mapper.map(resource, Assignment.class); }

    private AssignmentResource convertToResource(Assignment entity){ return mapper.map(entity, AssignmentResource.class); }


}
