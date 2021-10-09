package com.softtech.tdp.dto;

import com.softtech.tdp.model.AssignmentCriticality;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.model.AssignmentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentResource {
    private AssignmentKey id;
    private AssignmentStatus status;
    private float rating;
    private AssignmentCriticality criticality;
    private boolean isRated;
    private String patientName;
    private String specialistName;
    private String patientEmail;
}
