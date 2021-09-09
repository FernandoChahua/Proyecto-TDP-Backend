package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.softtech.tdp.dto.ResponsePatientProfile;
import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.model.Patient;
import com.softtech.tdp.repository.PatientRepository;
import com.softtech.tdp.service.IPatientService;


@Service
public class PatientServiceImpl implements IPatientService{
	@Autowired
	private PatientRepository repo;
	
	@Lazy
	@Autowired
	private AppUserServiceImpl userService;
	
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

	@Override
	public Patient findByUserId(Long idUser) {
		return repo.findByUserId(idUser);
	}

	@Override
	public ResponsePatientProfile getProfileByPatientId(Integer idPatient) {
		Patient patient = repo.findById(idPatient).get();
		
		AppUser user = userService.findById(patient.getUser().getId());
		
		
		
		return ResponsePatientProfile.builder()
									.bornDate(patient.getBornDate())
									.createdAt(patient.getCreatedAt())
									.email(user.getEmail())
									.firstName(patient.getFirstName())
									.lastName(patient.getLastName())
									.build();
	}

}
