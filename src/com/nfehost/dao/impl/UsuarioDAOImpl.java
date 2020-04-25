package com.nfehost.dao.impl;

import org.hibernate.Criteria;

import com.nfehost.dao.UsuarioDAO;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Usuario;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.model.filter.UsuarioFilter;
import com.nfehost.util.NullUtil;

public class UsuarioDAOImpl<Pojo extends Usuario> extends HibernateDAOImpl<Pojo> implements UsuarioDAO<Pojo> {

	
	@Override
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {
		
		if (!NullUtil.isNull(filter) && filter instanceof UsuarioFilter) {
			
			UsuarioFilter filtro = (UsuarioFilter) filter;
			
			return CriterionFactory.getInstance(criteria)
				.eq("pojo.login", filtro.getUsername())
				.eq("pojo.nivel", filtro.getNivel())
				.getCriteria();
		}
	
		return criteria;

	}
	
	@Override
	public Usuario userExists(String username, String password) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(Usuario.class));

		criteria.eq("pojo.login", username);
		criteria.eq("pojo.senha", password);

		return (Usuario) criteria.getCriteria().uniqueResult();
	}
	
}
