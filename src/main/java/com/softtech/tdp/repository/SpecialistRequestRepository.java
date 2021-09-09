package com.softtech.tdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.tdp.model.SpecialistRequest;
import com.softtech.tdp.model.SpecialistRequestState;

public interface SpecialistRequestRepository extends JpaRepository<SpecialistRequest, Integer>{
	@Transactional
    @Modifying
    @Query("UPDATE SpecialistRequest s " +
            "SET s.state = ?1 WHERE s.idSpecialistRequest = ?2")
    int updateStateById(SpecialistRequestState state,Integer idNews);
	
	
	List<SpecialistRequest> findByState(SpecialistRequestState state);
	
}
