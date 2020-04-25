package com.nfehost.dao.impl;

import org.hibernate.Criteria;

import com.nfehost.dao.DestinatarioDAO;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Destinatario;
import com.nfehost.model.filter.DestinatarioFilter;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.util.NullUtil;

public class DestinatarioDAOImpl<Pojo extends Destinatario> extends HibernateDAOImpl<Pojo> implements DestinatarioDAO<Pojo> {
	
	@Override
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {

		if (!NullUtil.isNull(filter) && filter instanceof DestinatarioFilter) {

			DestinatarioFilter filtro = (DestinatarioFilter) filter;
			criteria.createAlias("pojo.dadosEmpresa", "dadosEmpresa");

			return CriterionFactory.getInstance(criteria)
				.ilike("dadosEmpresa.razaoSocial", filtro.getRazaoSocial())
				.eq("dadosEmpresa.cnpj", filtro.getCnpj())
				.eq("dadosEmpresa.inscricaoEstadual", filtro.getInscricaoEstadual())
				.getCriteria();
		}

		return criteria;	
	}
}
