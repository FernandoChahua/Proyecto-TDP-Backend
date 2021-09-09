package com.softtech.tdp.service;

import java.util.List;

import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.model.SpecialistRequestState;

public interface ISpecialistRequestService extends CRUDService<SpecialistRequest, Integer>{
	List<SpecialistRequest> findByState(SpecialistRequestState state);
	SpecialistRequest updateState(Integer id, SpecialistRequestState state) throws Exception;
}
