package com.softtech.tdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.tdp.model.MedicalCenter;

public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Integer>{
    
}
