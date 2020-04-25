package com.nfehost.dao.impl;

import org.hibernate.Criteria;

import com.nfehost.dao.ArquivoDAO;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Arquivo;
import com.nfehost.model.filter.ArquivoFilter;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.util.NullUtil;

public class ArquivoDAOImpl<Pojo extends Arquivo> extends HibernateDAOImpl<Pojo> implements ArquivoDAO<Pojo> {
	
	@Override
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {

		if (!NullUtil.isNull(filter) && filter instanceof ArquivoFilter) {

			ArquivoFilter filtro = (ArquivoFilter) filter;

			return CriterionFactory.getInstance(criteria)
				.between("pojo.dataRegistro", filtro.getDataInicial(), filtro.getDataFinal())
				.getCriteria();
		}

		return criteria;	
	}
}
