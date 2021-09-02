package com.softtech.tdp.repository;

import com.softtech.tdp.model.Assignment;
import com.softtech.tdp.model.AssignmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    Optional<Assignment> findByIdAndPatientIdPatient(AssignmentKey id, Integer patientId);
    Optional<Assignment> findByIdAndSpecialistIdSpecialist(AssignmentKey id, Integer specialistId);
}
