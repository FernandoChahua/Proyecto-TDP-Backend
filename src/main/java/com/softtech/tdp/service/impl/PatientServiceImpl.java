package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.tdp.model.Patient;
import com.softtech.tdp.repository.PatientRepository;
import com.softtech.tdp.service.IPatientService;


@Service
public class PatientServiceImpl implements IPatientService{
	@Autowired
	private PatientRepository repo;
	
	@Override
	public Patient create(Patient t) {
		t.setCreatedAt(LocalDateTime.now());
		return repo.save(t);
	}

	@Override
	public Patient update(Patient t) {
		return null;
	}

	@Override
	public List<Patient> findAll() {
		return null;
	}

	@Override
	public Patient findById(Integer id) {
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		
	}

}
