package com.softtech.tdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class AssignmentKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "specialist_id")
    private Integer specialistId;

    public AssignmentKey() {

    }
}
