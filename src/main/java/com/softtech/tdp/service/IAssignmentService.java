package com.softtech.tdp.service;

import com.softtech.tdp.model.Assignment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAssignmentService{
    Assignment createAssignment(Integer patientId, Integer specialistId, Assignment assignment);
    Assignment getByPatientIdAndSpecialistId(Integer patientId, Integer specialistId);
    Assignment updateAssignment(Integer patientId, Integer specialistId, Assignment assignmentDetails);
    ResponseEntity<?> deleteAssignment(Integer patientId, Integer specialistId);
    
    List<Assignment> findByPatientId(Integer patientId);
    List<Assignment> findBySpecialistId(Integer specialistId);
    
    Assignment createAssignmentWithCategry(Integer patientId,String category,Integer score) throws Exception;
}
