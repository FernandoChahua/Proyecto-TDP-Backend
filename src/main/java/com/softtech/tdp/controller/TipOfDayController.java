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

import com.softtech.tdp.model.TipOfDay;
import com.softtech.tdp.service.ITipOfDayService;

@RestController
@RequestMapping("/tips")
public class TipOfDayController {
	@Autowired
	private ITipOfDayService tipService;
	
	@GetMapping
	public ResponseEntity<List<TipOfDay>> findAll(){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(tipService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipOfDay> findById(@PathVariable("id") Integer id){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(tipService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<TipOfDay> create(@RequestBody TipOfDay request, final HttpServletRequest req){
		request = tipService.create(request);
		return ResponseEntity
				.created(URI.create(req.getRequestURI().toString().concat("/").concat(request.getIdTipOfDay().toString())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(request);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipOfDay> update(@PathVariable("id") Integer id,@RequestBody TipOfDay request){
		TipOfDay tipBD = tipService.findById(id);
		
		if( tipBD.getIdTipOfDay() == request.getIdTipOfDay()) {
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(tipService.update(request));
		}
		return new ResponseEntity<TipOfDay>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		TipOfDay newsBd = tipService.findById(id);
		if(newsBd.getIdTipOfDay() != null) {
			tipService.deleteById(newsBd.getIdTipOfDay());
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/of-the-day")
	public ResponseEntity<TipOfDay> findTipOfDay() throws Exception{
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(tipService.findTipOfDay());
	}
}
