package com.softtech.tdp.service;

import com.softtech.tdp.dto.ResponsePatientProfile;
import com.softtech.tdp.model.Patient;


public interface IPatientService extends CRUDService<Patient, Integer>{
	Patient findByUserId(Long idUser);
	ResponsePatientProfile getProfileByPatientId(Integer idPatient);
}
