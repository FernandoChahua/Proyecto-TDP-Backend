package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.tdp.model.Feedback;
import com.softtech.tdp.model.Specialist;
import com.softtech.tdp.repository.FeedbackRepository;
import com.softtech.tdp.service.IFeedbackService;
import com.softtech.tdp.service.ISpecialistService;

@Service
public class FeedbackServiceImpl implements IFeedbackService{

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ISpecialistService specialistService;
	
	@Override
	public Feedback create(Feedback t) {
		return feedbackRepository.save(t);
	}

	@Override
	public Feedback update(Feedback t) {
		return feedbackRepository.save(t);
	}

	@Override
	public List<Feedback> findAll() {
		return feedbackRepository.findAll()
				.stream()
				.sorted((f1,f2) -> f1.getRegistrationDate().compareTo(f2.getRegistrationDate()))
				.collect(Collectors.toList());
	}

	@Override
	public Feedback findById(Integer id) {
		return feedbackRepository.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		feedbackRepository.deleteById(id);
	}

	@Override
	public Feedback createFeedback(Integer idSpecialist, Feedback feedback) {
		Specialist specialist = specialistService.findById(idSpecialist);
		feedback.setSpecialist(specialist);
		feedback.setRegistrationDate(LocalDateTime.now());
		System.out.println(specialist.toString());
		return feedbackRepository.save(feedback);
	}

}
