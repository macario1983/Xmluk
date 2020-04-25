package com.nfehost.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.nfehost.model.Persistent;
import com.nfehost.model.filter.FilterNfeHost;

public interface DAO<Pojo extends Persistent> {

	Serializable save(Pojo pojo);

	void update(Pojo pojo);

	void delete(Pojo pojo);

	Pojo findById(Long id);

	Pojo findByNaturalId(Object naturalId);

	List<Pojo> findByFilter(FilterNfeHost filter);

	Collection<Pojo> findByField(String field, String value);
	
	void refreshPojo(Pojo pojo);
	
	<D extends Pojo> void saveDetail(Pojo master, String referenceMaster, Class<? extends Persistent> classDetail, Collection<D> details);
	
	Long getTotalRows(FilterNfeHost filter);
	
	void initializeLazyAttribute(Object attribute);

	void setEntityClass(Class<? extends Pojo> classPojo);

}
