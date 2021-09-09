package com.softtech.tdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.tdp.model.Specialist;

public interface SpecialistRepository extends JpaRepository<Specialist, Integer>{
	Specialist findByUserId(Long idUser);
}
