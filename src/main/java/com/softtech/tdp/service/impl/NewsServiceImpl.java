package com.softtech.tdp.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.tdp.model.News;
import com.softtech.tdp.repository.NewsRepository;
import com.softtech.tdp.service.INewsService;

@Service
public class NewsServiceImpl implements INewsService{

	@Autowired
	private NewsRepository newsRepository;
	
	@Override
	public News create(News t) {
		return newsRepository.save(t);
	}

	@Override
	public News update(News t) {
		return newsRepository.save(t);
	}

	@Override
	public List<News> findAll() {
		return newsRepository.findByStateOrderByPublicationDateDesc(true);
	}

	@Override
	public News findById(Integer id) {
		return newsRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No se encontró el registro."));
	}

	@Override
	public void deleteById(Integer id) {
		newsRepository.disableNewsById(id);
	}

}
