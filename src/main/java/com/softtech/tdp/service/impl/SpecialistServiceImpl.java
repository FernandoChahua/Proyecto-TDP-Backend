package com.softtech.tdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.softtech.tdp.dto.ResponseSpecialistProfile;
import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.model.Specialist;
import com.softtech.tdp.repository.SpecialistRepository;
import com.softtech.tdp.service.ISpecialistService;

@Service
public class SpecialistServiceImpl implements ISpecialistService{

	@Autowired
	private SpecialistRepository repo;
	
	@Lazy
	@Autowired
	private AppUserServiceImpl userService;
	
	@Override
	public Specialist create(Specialist t) {
		return repo.save(t);
	}

	@Override
	public Specialist update(Specialist t) {
		return repo.save(t);
	}

	@Override
	public List<Specialist> findAll() {
		return null;
	}

	@Override
	public Specialist findById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Specialist findByUserId(Long idUser) {
		
		return repo.findByUserId(idUser);
	}

	@Override
	public ResponseSpecialistProfile getSpecialistProfileById(Integer idSpecialist) {
		Specialist specialist = repo.findById(idSpecialist).get();
		
		AppUser user = userService.findById(specialist.getUser().getId());
		
		
		
		return ResponseSpecialistProfile.builder()
									.bornDate(specialist.getBornDate())
									.email(user.getEmail())
									.firstName(specialist.getFirstName())
									.lastName(specialist.getLastName())
									.category(specialist.getCategory())
									.urlCertificate(specialist.getUrlCertificate())
									.build();
	}

}
