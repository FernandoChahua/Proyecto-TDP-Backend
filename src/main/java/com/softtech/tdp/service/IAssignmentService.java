package com.softtech.tdp.service;

import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentKey;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IAssignmentService{
    Assignment createAssignment(Integer patientId, Integer specialistId, Assignment assignment);
    Assignment getByPatientIdAndSpecialistId(Integer patientId, Integer specialistId);
    Assignment updateAssignment(Integer patientId, Integer specialistId, Assignment assignmentDetails);
    ResponseEntity<?> deleteAssignment(Integer patientId, Integer specialistId);
}
