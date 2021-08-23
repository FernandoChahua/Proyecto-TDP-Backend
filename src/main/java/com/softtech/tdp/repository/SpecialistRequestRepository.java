package com.softtech.tdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.tdp.model.SpecialistRequest;

public interface SpecialistRequestRepository extends JpaRepository<SpecialistRequest, Integer>{
	@Transactional
    @Modifying
    @Query("UPDATE SpecialistRequest s " +
            "SET s.state = ?1 WHERE s.idSpecialistRequest = ?2")
    int updateStateById(boolean state,Integer idNews);
}
