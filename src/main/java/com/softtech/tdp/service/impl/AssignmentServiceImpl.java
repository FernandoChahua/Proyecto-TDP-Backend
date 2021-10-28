package com.softtech.tdp.service.impl;

import com.softtech.tdp.exception.ResourceNotFoundException;
import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentCriticality;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.model.AssignmentStatus;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.model.Specialist;
import com.softtech.tdp.repository.AssignmentRepository;
import com.softtech.tdp.repository.SpecialistRepository;
import com.softtech.tdp.service.IAssignmentService;
import com.softtech.tdp.service.IPatientService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements IAssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    IPatientService patientService;

    @Autowired
    SpecialistRepository specialistRepository;

    @Override
    public Assignment createAssignment(Integer patientId, Integer specialistId, Assignment assignment) {
        Patient patient = Optional.ofNullable(patientService.findById(patientId)).orElseThrow(() -> new
                ResourceNotFoundException("Patient", "Id", patientId));

        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new
                ResourceNotFoundException("Specialist", "Id", specialistId));
        assignment.setPatient(patient);
        assignment.setSpecialist(specialist);
        assignment.setRated(false);
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
        assignment.setRated(true);
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

	@Override
	public List<Assignment> findByPatientId(Integer patientId) {
		
		return assignmentRepository.findAllByPatientIdPatient(patientId);
	}

	@Override
	public List<Assignment> findBySpecialistId(Integer specialistId) {
		return assignmentRepository.findAllBySpecialistIdSpecialist(specialistId);
	}

	@Override
	public Assignment createAssignmentWithCategry(Integer patientId, String category,Integer score) throws Exception {
		
		List<Assignment> assignments = assignmentRepository.findAllByPatientIdPatient(patientId);
		if(!assignments.isEmpty()) {
			throw new Exception("El paciente ya cuenta con una asignación activa.");
		}
		
		Patient patient = Optional.ofNullable(patientService.findById(patientId)).orElseThrow(() -> new
                ResourceNotFoundException("Patient", "Id", patientId));
		
		List<Specialist> specialists = specialistRepository.findByCategoryIgnoreCase(category.toUpperCase());
		
		if( specialists.isEmpty())
			throw new Exception("No se encuentra ningun especialista con esa categoria.");
        
		Collections.shuffle(specialists);
		
		Specialist specialist = specialists.get(0);
        
        Assignment assignment = Assignment.builder()
        								.criticality((score < 14)?AssignmentCriticality.low:
        											(score<20)?AssignmentCriticality.medium:
        												AssignmentCriticality.high)
        								.score(score)
        								.isRated(false)
        								.rating(0)
        								.patient(patient)
        								.status(AssignmentStatus.active)
        								.specialist(specialist)
        								.build();
        assignment.setPatient(patient);
        assignment.setSpecialist(specialist);
        assignment.setId(new AssignmentKey(patientId, specialist.getIdSpecialist()));
        return assignmentRepository.save(assignment);
	}
}
