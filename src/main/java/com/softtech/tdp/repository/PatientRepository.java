package com.softtech.tdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.tdp.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
