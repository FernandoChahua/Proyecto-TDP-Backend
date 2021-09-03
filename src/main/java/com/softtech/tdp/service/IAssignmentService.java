package com.softtech.tdp.service;

import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentKey;
import org.springframework.http.ResponseEntity;

public interface IAssignmentService{
    Assignment createAssignment(Integer patientId, Integer specialistId, Assignment assignment);
    Assignment getAssignmentByIdAndPatientId(AssignmentKey assignmentId, Integer patientId);
    Assignment getAssignmentByIdAndSpecialistId(AssignmentKey assignmentId, Integer specialistId);
    Assignment updateAssignmentByPatientId(AssignmentKey assignmentId, Integer patientId, Assignment assignmentDetails);
    ResponseEntity<?> deleteAssignment(AssignmentKey assignmentId, Integer patientId);
}
