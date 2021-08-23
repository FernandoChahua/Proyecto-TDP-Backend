package com.softtech.tdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.tdp.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
