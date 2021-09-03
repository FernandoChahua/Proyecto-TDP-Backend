package com.softtech.tdp.dto;

import com.softtech.tdp.model.AssignmentCriticality;
import com.softtech.tdp.model.AssignmentKey;
import com.softtech.tdp.model.AssignmentStatus;
import lombok.Data;

@Data
public class AssignmentResource {
    private AssignmentKey id;
    private AssignmentStatus status;
    private float rating;
    private AssignmentCriticality criticality;
}
