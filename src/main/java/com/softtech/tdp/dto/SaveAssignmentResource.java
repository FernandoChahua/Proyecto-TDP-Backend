package com.softtech.tdp.dto;

import com.softtech.tdp.model.AssignmentCriticality;
import com.softtech.tdp.model.AssignmentStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveAssignmentResource {
    private AssignmentStatus status;

    @NotNull
    private float rating;

    private AssignmentCriticality criticality;
}
