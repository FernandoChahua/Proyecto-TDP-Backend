package com.softtech.tdp.service.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.tdp.model.TipOfDay;
import com.softtech.tdp.repository.TipOfDayRepository;
import com.softtech.tdp.service.ITipOfDayService;

@Service
public class TipOfDayServiceImpl implements ITipOfDayService{

	@Autowired
	private TipOfDayRepository repo;
	
	@Override
	public TipOfDay create(TipOfDay t) {
		return repo.save(t);
	}

	@Override
	public TipOfDay update(TipOfDay t) {
		return repo.save(t);
	}

	@Override
	public List<TipOfDay> findAll() {
		return repo.findAll();
	}

	@Override
	public TipOfDay findById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public TipOfDay findTipOfDay() throws Exception {
		List<TipOfDay> tips = repo.findByTipDateLessThanEqualOrderByTipDateDesc(LocalDate.now());
		if(tips.size() == 0) throw new Exception("No se encontró un consejo del día.");
		
		TipOfDay tip;
		
		if(LocalDate.now().isEqual(tips.get(0).getTipDate())) {
			tip = tips.get(0);
		}else {
			Collections.shuffle(tips);
			tip = tips.get(0);
		}
		
		return tip;
	}
	
}
