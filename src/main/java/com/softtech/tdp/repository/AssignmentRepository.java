package com.softtech.tdp.repository;

import com.softtech.tdp.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    Assignment findByPatientIdPatientAndSpecialistIdSpecialist(Integer patientId, Integer specialistId);
    Boolean existsByPatientIdPatientAndSpecialistIdSpecialist(Integer patientId, Integer specialistId);
    
    List<Assignment> findAllByPatientIdPatient(Integer patientId);
    List<Assignment> findAllBySpecialistIdSpecialist(Integer specialistId);
    
}
