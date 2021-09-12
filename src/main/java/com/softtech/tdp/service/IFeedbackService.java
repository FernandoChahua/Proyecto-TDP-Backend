package com.softtech.tdp.service;

import java.util.List;

import com.softtech.tdp.dto.FeedbackExtraInfoDTO;
import com.softtech.tdp.model.Feedback;

public interface IFeedbackService extends CRUDService<Feedback, Integer>{
	Feedback createFeedback(Integer idSpecialist, Feedback feedback);
	
	List<FeedbackExtraInfoDTO> findAllCustom();
	
	FeedbackExtraInfoDTO findByIdCustom(Integer id);
}
