package com.softtech.tdp.service;

import com.softtech.tdp.model.Assignment;

public interface IAssignmentService{
    Assignment createAssignment(Integer patientId, Integer specialistId, Assignment assignment);

}
