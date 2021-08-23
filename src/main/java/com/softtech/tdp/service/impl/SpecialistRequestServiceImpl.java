package com.softtech.tdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.repository.SpecialistRequestRepository;
import com.softtech.tdp.service.ISpecialistRequestService;

@Service
public class SpecialistRequestServiceImpl implements ISpecialistRequestService{

	
	@Autowired
	private SpecialistRequestRepository repository;
	
	@Override
	public SpecialistRequest create(SpecialistRequest t) {
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

}
