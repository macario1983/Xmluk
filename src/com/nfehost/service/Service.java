package com.nfehost.service;

import java.util.Collection;
import java.util.List;

import com.nfehost.exception.NfeHostException;
import com.nfehost.model.filter.FilterNfeHost;

public interface Service<Pojo> {

	void save(Pojo pojo) throws NfeHostException;
	
	void remove(Pojo pojo) throws NfeHostException;

	Pojo cleanPojo(Pojo pojo);
	
	void fillDefaults(Pojo pojo);
	
	Collection<Pojo> findAll();
	
	List<Pojo> findByFilter(FilterNfeHost filter);
	
	Pojo findByPojo(Pojo pojo);
	
	Pojo findById(Long id);
	
	Long getTotalRows(FilterNfeHost filter);	
	
	void refreshPojo(Pojo pojo);

	void initializeLazyAttribute(Object attribute);

}
