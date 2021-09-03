package com.softtech.tdp.service.impl;

import com.softtech.tdp.exception.ResourceNotFoundException;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.repository.AssignmentRepository;
import com.softtech.tdp.repository.PatientRepository;
import com.softtech.tdp.repository.SpecialistRepository;
import com.softtech.tdp.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Assignment getAssignmentByIdAndPatientId(AssignmentKey assignmentId, Integer patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            return assignmentRepository.findByIdAndPatientIdPatient(assignmentId, patientId).
                    orElseThrow(() -> new ResourceNotFoundException("Assignment not found with Id "+assignmentId+" and" +
                            "Patient Id "+patientId));
        }).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));
    }

    @Override
    public Assignment getAssignmentByIdAndSpecialistId(AssignmentKey assignmentId, Integer specialistId) {
        if (!specialistRepository.existsById(specialistId))
            throw new ResourceNotFoundException("Specialist", "Id", specialistId);
        return assignmentRepository.findByIdAndSpecialistIdSpecialist(assignmentId, specialistId).
                orElseThrow(() -> new ResourceNotFoundException("Assignment not found with Id "+assignmentId+" and " +
                        "specialist Id "+specialistId));
    }

    @Override
    public Assignment updateAssignmentByPatientId(AssignmentKey assignmentId, Integer patientId, Assignment assignmentDetails) {
        if (!patientRepository.existsById(patientId))
            throw new ResourceNotFoundException("Patient", "Id", patientId);
        return assignmentRepository.findByIdAndPatientIdPatient(assignmentId, patientId).map(assignment -> {
            assignment.setRating(assignmentDetails.getRating());
            assignment.setCriticality(assignmentDetails.getCriticality());
            assignment.setStatus(assignmentDetails.getStatus());
            return assignmentRepository.save(assignment);
        }).orElseThrow(() -> new ResourceNotFoundException("Assignment not found with Id "+assignmentId+" and patient " +
                "Id "+patientId));
    }

    @Override
    public ResponseEntity<?> deleteAssignment(AssignmentKey assignmentId, Integer patientId) {
        return assignmentRepository.findByIdAndPatientIdPatient(assignmentId, patientId).map(assignment -> {
            assignmentRepository.delete(assignment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Assignment not found with Id "+assignmentId+" and " +
                        "patient Id "+patientId));
    }
}
