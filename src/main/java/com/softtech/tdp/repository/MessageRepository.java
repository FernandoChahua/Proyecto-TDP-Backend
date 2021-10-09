package com.softtech.tdp.repository;

import com.softtech.tdp.model.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	List<Message> findAllByAssignmentPatientIdPatientAndAssignmentSpecialistIdSpecialist(Integer patientId,Integer specialistId);
}
