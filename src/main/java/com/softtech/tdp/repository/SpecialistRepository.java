package com.softtech.tdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.tdp.model.Specialist;

public interface SpecialistRepository extends JpaRepository<Specialist, Integer>{
	Specialist findByUserId(Long idUser);
	List<Specialist> findByCategoryIgnoreCase(String category);
}
