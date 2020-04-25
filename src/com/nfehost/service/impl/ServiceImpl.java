package com.nfehost.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lombok.Data;

import com.nfehost.dao.DAO;
import com.nfehost.exception.NfeHostException;
import com.nfehost.model.Persistent;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.service.Service;
import com.nfehost.util.NullUtil;

@Data
public abstract class ServiceImpl <Pojo extends Persistent, DAOInterface extends DAO<Pojo>> implements Service<Pojo> {

	private DAOInterface dao;
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	@Override
	public void save(Pojo pojo) throws NfeHostException {
		if(NullUtil.isNull(pojo.getKey())) {
			this.prepareSave(pojo);
			this.validateSave(pojo);
			Serializable key = this.getDao().save(pojo);
			pojo.setKey(key);
		}else { 
			this.prepareSave(pojo);
			this.validateSave(pojo);
			this.getDao().update(pojo);
		}		
	}

	@Override
	public void remove(Pojo pojo) throws NfeHostException {
		pojo = this.findByPojo(pojo);
		this.validateRemove(pojo);
		this.getDao().delete(pojo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pojo cleanPojo(Pojo pojo) {
		try {
			return (Pojo)pojo.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void fillDefaults(Pojo pojo) {}

	@Override
	public Collection<Pojo> findAll() {
		return this.getDao().findByFilter(null);
	}

	@Override
	public List<Pojo> findByFilter(FilterNfeHost filter) {
		return (List<Pojo>) this.getDao().findByFilter(filter);
	}

	@Override
	public Pojo findByPojo(Pojo pojo) {
		return this.findById(pojo.getId());
	}
	
	@Override
	public Pojo findById(Long id) {
		return this.getDao().findById(id);
	}
	
	public void refreshPojo(Pojo pojo) {
		this.getDao().refreshPojo(pojo);
	}
	
	@Override
	public void initializeLazyAttribute(Object attribute) {
		this.getDao().initializeLazyAttribute(attribute);		
	}

	@Override
	public Long getTotalRows(FilterNfeHost filter) {
		return this.getDao().getTotalRows(filter);
	}
	
	protected void validateSave(Pojo pojo, Class<?>... groups) throws NfeHostException {
		this.validate(pojo, groups);
	}
	
	protected <P> void validate(P pojo, Class<?>... groups) throws NfeHostException {
		Set<ConstraintViolation<P>> erros = this.validator.validate(pojo, groups);	
		if(!NullUtil.isEmpty(erros)) {
			NfeHostException e = new NfeHostException();
			for (ConstraintViolation<P> erro : erros) {
				e.addMessageError(erro.getMessage());
				System.out.println(erros.toString());
			}
			throw e;
		}
	}
	
	protected <P extends Persistent> void validateValue(Class<P> persistentClass, String attr, Object value, Class<?>... groups) throws NfeHostException {
		Set<ConstraintViolation<P>> erros = this.validator.validateValue(persistentClass, attr, value, groups);
		if(!NullUtil.isEmpty(erros)) {
			NfeHostException e = new NfeHostException();
			for (ConstraintViolation<P> erro : erros) {
				e.addMessageError(erro.getMessage());
			}
			throw e;
		}
	}
	
	protected <P extends Persistent> void validateProperty(P pojo, String attr, Class<?>... groups) throws NfeHostException {
		Set<ConstraintViolation<P>> erros = this.validator.validateProperty(pojo, attr, groups);
		if(!NullUtil.isEmpty(erros)) {
			NfeHostException e = new NfeHostException();
			for (ConstraintViolation<P> erro : erros) {
				e.addMessageError(erro.getMessage());
			}
			throw e;
		}
	}
	
	protected void validateRemove(Pojo pojo) throws NfeHostException {
		if(NullUtil.isNull(pojo) || NullUtil.isNull(pojo.getKey())) {
			throw new NfeHostException("msg.remove.erro.key");
		}
	}

	protected void prepareSave(Pojo pojo) {
	
	}

}
