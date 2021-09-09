package com.softtech.tdp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Assignment implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    AssignmentKey id;

    private AssignmentStatus status;

    private float rating;

    private AssignmentCriticality criticality;

    @ManyToOne
    @MapsId("specialistId")
    @JoinColumn(name = "specialist_id")
    Specialist specialist;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    Patient patient;

    @OneToMany(mappedBy = "assignment")
    private Set<Message> messages;
}
