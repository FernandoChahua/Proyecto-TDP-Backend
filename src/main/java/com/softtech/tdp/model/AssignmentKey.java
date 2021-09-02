package com.softtech.tdp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class AssignmentKey implements Serializable {
    @Column(name = "specialist_id")
    Integer idSpecialist;

    @Column(name = "patient_id")
    Integer idPatient;
}
