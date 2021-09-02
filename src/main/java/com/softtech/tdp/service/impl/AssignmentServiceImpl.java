package com.softtech.tdp.service.impl;

import com.softtech.tdp.exception.ResourceNotFoundException;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.repository.AssignmentRepository;
import com.softtech.tdp.repository.PatientRepository;
import com.softtech.tdp.repository.SpecialistRepository;
import com.softtech.tdp.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements IAssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    SpecialistRepository specialistRepository;

    @Override
    public Assignment createAssignment(Integer patientId, Integer specialistId, Assignment assignment) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new
                ResourceNotFoundException("Patient", "Id", patientId));

        return  specialistRepository.findById(specialistId).map(specialist -> {
            assignment.setSpecialist(specialist);
            assignment.setPatient(patient);
            return assignmentRepository.save(assignment);
        }).orElseThrow(() -> new ResourceNotFoundException("Specialist", "Id", specialistId));
    }

    @Override
    public Assignment update(Assignment assignment) {
        return null;
    }

    @Override
    public List<Assignment> findAll() {
        return null;
    }

    @Override
    public Assignment findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
