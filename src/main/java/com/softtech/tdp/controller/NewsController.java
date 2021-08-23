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

import com.softtech.tdp.model.News;
import com.softtech.tdp.service.INewsService;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private INewsService newsService;
	
	@GetMapping
	public ResponseEntity<List<News>> findAll(){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(newsService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<News> findById(@PathVariable("id") Integer id){
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(newsService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<News> create(@RequestBody News request, final HttpServletRequest req){
		request = newsService.create(request);
		return ResponseEntity
				.created(URI.create(req.getRequestURI().toString().concat("/").concat(request.getIdNews().toString())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(request);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<News> update(@PathVariable("id") Integer id,@RequestBody News request){
		News newsBd = newsService.findById(id);
		
		if( newsBd.getIdNews() == request.getIdNews()) {
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(request);
		}
		return new ResponseEntity<News>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		News newsBd = newsService.findById(id);
		if(newsBd.getIdNews() != null) {
			newsService.deleteById(newsBd.getIdNews());
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
