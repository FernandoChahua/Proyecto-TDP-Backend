package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.tdp.model.AppUser;
import com.softtech.tdp.model.Specialist;
import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.model.SpecialistRequestState;
import com.softtech.tdp.repository.SpecialistRequestRepository;
import com.softtech.tdp.service.ISpecialistRequestService;
import com.softtech.tdp.service.ISpecialistService;

@Service
public class SpecialistRequestServiceImpl implements ISpecialistRequestService{

	
	@Autowired
	private SpecialistRequestRepository repository;
	
	@Autowired
	private ISpecialistService specialistService;
	
	@Autowired 
	private AppUserServiceImpl userService;
	
	
	@Override
	public SpecialistRequest create(SpecialistRequest t) {
		if(userService.checkExistEmail(t.getEmail())) {
			throw new IllegalStateException("El email ya existe");
		}
		
		t.setCreatedAt(LocalDateTime.now());
		t.setState(SpecialistRequestState.CREATED);
		return repository.save(t);
	}

	@Override
	public SpecialistRequest update(SpecialistRequest t) {
		return repository.save(t);
	}

	@Override
	public List<SpecialistRequest> findAll() {
		return repository.findAll();
	}

	@Override
	public SpecialistRequest findById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<SpecialistRequest> findByState(SpecialistRequestState state) {
		return repository.findByState(state);
	}

	@Override
	@Transactional
	public SpecialistRequest updateState(Integer id, SpecialistRequestState state) throws Exception{
		SpecialistRequest request = repository.findById(id).orElseThrow(() -> new Exception("No se encontró la solicitud"));
		
		if(request.getState() == SpecialistRequestState.CREATED) {
			request.setState(state);
			if(state == SpecialistRequestState.APPROVED) {
				
				if( userService.signUpSpecialist(request)) {
					repository.save(request);
					
					AppUser user = userService.findByEmail(request.getEmail());
					specialistService.create(Specialist.builder()
							.bornDate(request.getBornDate())
							.firstName(request.getFirstName())
							.lastName(request.getLastName())
							.category(request.getCategory())
							.createdAt(LocalDateTime.now())
							.urlCertificate(request.getUrlCertificate())
							.state(true)
							.user(user)
							.build());
					
					
				}else {
					throw new Exception("La solicitud no se pudo guardar, debido a que no se generó el usuario");
				}
			}
		}else {
			throw new Exception("La solicitud ya ha sido actualizado");
		}
		
		return request;
	}

}
