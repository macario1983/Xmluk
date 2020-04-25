package com.nfehost.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.nfehost.dao.DAO;
import com.nfehost.dao.HibernateSessionFactory;
import com.nfehost.dao.UsuarioDAO;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Persistent;
import com.nfehost.model.Usuario;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;

public abstract class HibernateDAOImpl<Pojo extends Persistent> implements DAO<Pojo> {

	private Class<? extends Persistent> persistentClass;
	private UsuarioDAO<Usuario> usuarioDao;
	
	public UsuarioDAO<Usuario> getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO<Usuario> usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	protected Class<? extends Persistent> getPersistentClass() {
		return this.persistentClass;
	}
	
	public void setPersistentClass(Pojo pojo) {
		this.persistentClass = pojo.getClass();
	}
	
	@Override
	public void setEntityClass(Class<? extends Pojo> classPojo) {
		this.persistentClass = classPojo;
	}
	
	@Override
	public Serializable save(Pojo pojo) {
		try {
			return HibernateSessionFactory.getSession().save(pojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Pojo pojo) {
		HibernateSessionFactory.getSession().update(pojo);		
	}

	@Override
	public void delete(Pojo pojo) {
		HibernateSessionFactory.getSession().delete(pojo);			
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pojo findById(Long id) {
		return (Pojo)HibernateSessionFactory.getSession().byId(this.getPersistentClass()).load(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pojo findByNaturalId(Object naturalId) {
		return (Pojo) HibernateSessionFactory.getSession().bySimpleNaturalId(this.getPersistentClass()).load(naturalId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pojo> findByFilter(FilterNfeHost filter) {
		Criteria criteria = this.createCriteria();
		
		if (!NullUtil.isNull(filter)) {
			this.setUpCriteria(criteria, filter);
			this.setUpOrder(criteria, filter);
			
			if(!NullUtil.isNull(filter.getFirstRegister())) {
				criteria.setFirstResult(filter.getFirstRegister().intValue());			
			}
			if(!NullUtil.isNull(filter.getMaxRegister())) {
				criteria.setMaxResults(filter.getMaxRegister().intValue());			
			}
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Pojo> findByField(String field, String value) {
		return CriterionFactory.getInstance(this.createCriteria()).eq(field, value).getCriteria().list();
	}

	@Override
	public <D extends Pojo> void saveDetail(Pojo master, String referenceMaster, Class<? extends Persistent> classDetail, Collection<D> details) {
		// Exclui os dados necessários
		this.deleteDetails(master, referenceMaster, classDetail, details);
		
		// Grava os restantes
		for (D detail : details) {
			this.save(detail);
		}		
	}
	
	@Override
	public void refreshPojo(Pojo pojo) {
		HibernateSessionFactory.getSession().refresh(pojo);
	}
	
	@Override
	public void initializeLazyAttribute(Object attribute) {
		if (!Hibernate.isInitialized(attribute)) {
			Hibernate.initialize(attribute);
		}
	}

	@Override
	public Long getTotalRows(FilterNfeHost filter) {
		try {
			Criteria criteria = this.createCriteria().setProjection(Projections.rowCount());
			if (!NullUtil.isNull(filter)) {
				this.setUpCriteria(criteria, filter);
			}
			Object obj = criteria.uniqueResult();
			return (Long) obj;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected <D extends Pojo> void deleteDetails(Pojo master, String referenceMaster, Class<? extends Persistent> classDetail, Collection<D> details) {
		// Lista os dados a serem excluidos.
		Criteria criteriaDelete = HibernateSessionFactory.getSession().createCriteria(classDetail, "detail").setFlushMode(FlushMode.MANUAL);
		criteriaDelete.add(Restrictions.eq(referenceMaster, master.getId()));
		List<Pojo> listDel = (List<Pojo>) criteriaDelete.list();
			
		// Exclui todos os detalhes
		for (Pojo detail : listDel) {
			this.delete(detail);
		}		
	}
	
	protected Criteria createCriteria() {
		//return HibernateSessionFactory.getSession().createCriteria(this.getPersistentClass(), "pojo");
		return this.createCriteria(this.getPersistentClass());
	}
	
	protected Criteria createCriteria(Class<? extends Persistent> classPojo) {
		return HibernateSessionFactory.getSession().createCriteria(classPojo, "pojo");
	}
	
	protected Query createQuery(String query) {
		return HibernateSessionFactory.getSession().createQuery(query);
	}
	
	protected Query createSQLQuery(String query) {
		return HibernateSessionFactory.getSession().createSQLQuery(query);
	}
	
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {
		return criteria;
	}
	
	protected void setUpOrder(Criteria criteria, FilterNfeHost filter) {
		if(!NullUtil.isNull(filter) && !NullUtil.isEmptyMap(filter.getOrder())) {
			String order = null;
			for(String propertyName : filter.getOrder().keySet()) {
				order = propertyName;
				if (!propertyName.startsWith(criteria.getAlias())) {
					order = criteria.getAlias() + "." + propertyName; 
				}
				if (StringUtil.isStringNullOrEmpty(propertyName)) {
					continue;
				}
				if(NullUtil.isNull(filter.getOrder().get(propertyName)) 
						|| filter.getOrder().get(propertyName).equals(FilterNfeHost.ModeOrder.ASCENDING)) {
					criteria.addOrder(Order.asc(order));					
				}
				else {
					criteria.addOrder(Order.desc(order));		
				}
			}
		}
	}
	
}
