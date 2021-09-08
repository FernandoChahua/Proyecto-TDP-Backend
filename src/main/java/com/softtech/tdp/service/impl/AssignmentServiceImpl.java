package com.softtech.tdp.service.impl;

import com.softtech.tdp.exception.ResourceNotFoundException;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.model.Specialist;
import com.softtech.tdp.repository.AssignmentRepository;
import com.softtech.tdp.repository.PatientRepository;
import com.softtech.tdp.repository.SpecialistRepository;
import com.softtech.tdp.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new
                ResourceNotFoundException("Specialist", "Id", specialistId));
        assignment.setPatient(patient);
        assignment.setSpecialist(specialist);
        assignment.setId(new AssignmentKey(patientId, specialistId));
        return assignmentRepository.save(assignment);
    }

    @Override
    public Assignment getByPatientIdAndSpecialistId(Integer patientId, Integer specialistId) {
        if(!assignmentRepository.existsByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId))
            throw new ResourceNotFoundException("Assignment not found with Patient Id "+patientId+" and " +
                    "Specialist Id "+specialistId);
        else
            return assignmentRepository.findByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId);
    }

    @Override
    public Assignment updateAssignment(Integer patientId, Integer specialistId,
                                       Assignment assignmentDetails) {
        if(!assignmentRepository.existsByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId))
            throw new ResourceNotFoundException("Assignment not found with Patient Id "+patientId+" and " +
                    "Specialist Id "+specialistId);

        Assignment assignment = assignmentRepository.findByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId);
        assignment.setRating(assignmentDetails.getRating());
        assignment.setCriticality(assignmentDetails.getCriticality());
        assignment.setStatus(assignmentDetails.getStatus());
        return assignmentRepository.save(assignment);

    }

    @Override
    public ResponseEntity<?> deleteAssignment(Integer patientId, Integer specialistId) {
        if(!assignmentRepository.existsByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId))
            throw new ResourceNotFoundException("Assignment not found with Patient Id "+patientId+" and " +
                    "Specialist Id "+specialistId);

        Assignment assignment = assignmentRepository.findByPatientIdPatientAndSpecialistIdSpecialist(patientId, specialistId);
        assignmentRepository.delete(assignment);
        return ResponseEntity.ok().build();
    }
}
