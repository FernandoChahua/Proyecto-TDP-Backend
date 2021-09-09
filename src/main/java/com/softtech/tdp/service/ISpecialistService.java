package com.softtech.tdp.service;

import com.softtech.tdp.dto.ResponseSpecialistProfile;
import com.softtech.tdp.model.Specialist;
import org.springframework.stereotype.Service;

@Service
public interface ISpecialistService extends CRUDService<Specialist, Integer>{
	Specialist findByUserId(Long idUser);
	ResponseSpecialistProfile getSpecialistProfileById(Integer idSpecialist);
}
