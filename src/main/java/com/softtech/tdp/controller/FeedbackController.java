package com.softtech.tdp.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.tdp.dto.FeedbackExtraInfoDTO;
import com.softtech.tdp.model.Feedback;
import com.softtech.tdp.model.News;
import com.softtech.tdp.service.IFeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired
	private IFeedbackService feedbackService;
	
	
	@GetMapping
	public ResponseEntity<List<FeedbackExtraInfoDTO>> findAll(){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(feedbackService.findAllCustom());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FeedbackExtraInfoDTO> findById(@PathVariable("id") Integer id){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(feedbackService.findByIdCustom(id));
	}
	
	@PostMapping("/{idSpecialist}")
	public ResponseEntity<Feedback> create(@PathVariable("idSpecialist")Integer idSpecialist,@RequestBody Feedback request, final HttpServletRequest req){
		request = feedbackService.createFeedback(idSpecialist,request);
		return ResponseEntity
				.created(URI.create(req.getRequestURI().toString().concat("/").concat(request.getIdFeedback().toString())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(request);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Feedback> update(@PathVariable("id") Integer id,@RequestBody Feedback request){
		Feedback feedbackBD = feedbackService.findById(id);
		
		if( feedbackBD.getIdFeedback() == request.getIdFeedback()) {
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(request);
		}
		return new ResponseEntity<Feedback>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		Feedback feedbackBD = feedbackService.findById(id);
		if(feedbackBD.getIdFeedback() != null) {
			feedbackService.deleteById(feedbackBD.getIdFeedback());
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
