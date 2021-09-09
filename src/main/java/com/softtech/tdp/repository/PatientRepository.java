package com.softtech.tdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.tdp.model.Patient;
import com.softtech.tdp.model.Specialist;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
	Patient findByUserId(Long idUser);
}
