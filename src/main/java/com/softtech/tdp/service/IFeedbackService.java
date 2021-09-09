package com.softtech.tdp.service;

import com.softtech.tdp.model.Feedback;

public interface IFeedbackService extends CRUDService<Feedback, Integer>{
	Feedback createFeedback(Integer idSpecialist, Feedback feedback);
}
