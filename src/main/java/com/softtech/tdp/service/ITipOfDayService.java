package com.softtech.tdp.service;

import com.softtech.tdp.model.TipOfDay;

public interface ITipOfDayService extends CRUDService<TipOfDay, Integer>{
	public TipOfDay findTipOfDay() throws Exception;
}
