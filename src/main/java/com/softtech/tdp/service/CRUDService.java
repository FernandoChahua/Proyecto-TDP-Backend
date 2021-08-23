package com.softtech.tdp.service;

import java.util.List;

public interface CRUDService<T, ID> {
	T create(T t);
	T update(T t);
	List<T> findAll();
	T findById(ID id);
	void deleteById(ID id);
}
