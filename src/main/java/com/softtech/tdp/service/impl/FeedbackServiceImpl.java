package com.softtech.tdp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.tdp.dto.FeedbackExtraInfoDTO;
import com.softtech.tdp.exception.ResourceNotFoundException;
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
	public Feedback findById(Integer id){
		return feedbackRepository.findById(id).get();
		
	}
	@Override
	public List<FeedbackExtraInfoDTO> findAllCustom() {
		return feedbackRepository.findAll()
				.stream()
				.map(e -> feedbackToExtraInfoDTO(e))
				.sorted((f1,f2) -> f1.getRegistrationDate().compareTo(f2.getRegistrationDate()))
				.collect(Collectors.toList());
	}

	@Override
	public FeedbackExtraInfoDTO findByIdCustom(Integer id) {
		FeedbackExtraInfoDTO response = feedbackToExtraInfoDTO(feedbackRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("No se encontró ningun registro con el id %s.",id.toString()))));
		return response;
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
		
		return feedbackRepository.save(feedback);
	}
	
	private FeedbackExtraInfoDTO feedbackToExtraInfoDTO(Feedback feedback) {
		return FeedbackExtraInfoDTO.builder()
									.createdBy(String.format("%s %s",feedback.getSpecialist().getFirstName(),feedback.getSpecialist().getLastName()))
									.description(feedback.getDescription())
									.title(feedback.getTitle())
									.idFeedback(feedback.getIdFeedback())
									.state(feedback.isState())
									.registrationDate(feedback.getRegistrationDate())
									.build();
											
									
	}

}
