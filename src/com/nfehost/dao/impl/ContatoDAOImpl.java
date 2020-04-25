package com.nfehost.dao.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.nfehost.dao.ContatoDAO;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.model.filter.ContatoFilter;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.util.NullUtil;

public class ContatoDAOImpl<Pojo extends Contato> extends HibernateDAOImpl<Pojo> implements ContatoDAO<Pojo> {
	
	@Override
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {
		
		if (!NullUtil.isNull(filter) && filter instanceof ContatoFilter) {
			
			ContatoFilter filtro = (ContatoFilter) filter;
			criteria.createAlias("pojo.emitente", "emitente");
			
			return CriterionFactory.getInstance(criteria)
				.eq("emitente.id", filtro.getEmitente())
				.ilike("pojo.nome", filtro.getNome())
				.ilike("pojo.email", filtro.getEmail())
				.eq("pojo.telefone", filtro.getTelefone())
				.getCriteria();
		}
	
		return criteria;	
	}

	@SuppressWarnings("unchecked")
	public List<Long> getListEmitenteWithContato() {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(Contato.class));
		criteria.createAlias("pojo.emitente", "emitente");		
		criteria.projectionDistinct("emitente.id");
		
		return criteria.getCriteria().list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contato> getListContatoByEmitente(Emitente emitenteContato) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(Contato.class));
		criteria.createAlias("pojo.emitente", "emitente");		
		criteria.eq("emitente.id", emitenteContato);
		
		return (List<Contato>) criteria.getCriteria().list();
	}

}
