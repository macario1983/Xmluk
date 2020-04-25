package com.nfehost.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;

import com.nfehost.dao.NotaFiscalDAO;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.model.filter.NotaFiscalFilter;
import com.nfehost.util.NullUtil;

public class NotaFiscalDAOImpl<Pojo extends NotaFiscal> extends HibernateDAOImpl<Pojo> implements NotaFiscalDAO<Pojo> {

	@Override
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {
		
		if (!NullUtil.isNull(filter) && filter instanceof NotaFiscalFilter) {
			
			NotaFiscalFilter filtro = (NotaFiscalFilter) filter;
			criteria.createAlias("pojo.emitente", "emitente");
			
			return CriterionFactory.getInstance(criteria)
				.eq("emitente.id", filtro.getEmitente())
				.eq("pojo.numero", filtro.getNumeroNota())
				.between("pojo.dataEmissao", filtro.getDataEmissaoNotaInicial(), filtro.getDataEmissaoNotaFinal())
				.getCriteria();
		}
	
		return criteria;
	}
	
	@Override
	public boolean hasNotaFiscal(Pojo pojo) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(NotaFiscal.class));

		criteria.createAlias("pojo.emitente", "emitente");
		criteria.createAlias("pojo.destinatario", "destintario");
		
		criteria.eq("pojo.numero", pojo.getNumero())
				.eq("pojo.tipoEmissao", pojo.getTipoEmissao())
				.eq("emitente.id", pojo.getEmitente())
				.eq("destinatario.id", pojo.getDestinatario());
		
		return criteria.getCriteria().list().size() > 0;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotaFiscal> getNotaFiscalByEmitente(Emitente emitenteNota) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(NotaFiscal.class));
		criteria.createAlias("pojo.emitente", "emitente");
		criteria.eq("emitente.id", emitenteNota);
		
		return (List<NotaFiscal>) criteria.getCriteria().list();
	}

	@Override
	public Destinatario hasDestinatario(Destinatario destinatario) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(Destinatario.class));
		criteria.createAlias("pojo.dadosEmpresa", "dadosEmpresa");

		criteria.eq("dadosEmpresa.cnpj", destinatario.getDadosEmpresa().getCnpj())
				.eq("dadosEmpresa.cpf", destinatario.getDadosEmpresa().getCpf());
		
		return (Destinatario) criteria.getCriteria().uniqueResult();		
	}
	
	@Override
	public Emitente hasEmitente(Emitente emitente) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(Emitente.class));
		criteria.createAlias("pojo.dadosEmpresa", "dadosEmpresa");
		
		criteria.eq("dadosEmpresa.cnpj", emitente.getDadosEmpresa().getCnpj())
				.eq("dadosEmpresa.cpf", emitente.getDadosEmpresa().getCpf());
		
		return (Emitente) criteria.getCriteria().uniqueResult();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotaFiscal> getSourceNotasFiscais(Set<Long> setNotasFiscais) {

		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(NotaFiscal.class));
		criteria.in("pojo.id", setNotasFiscais);
		
		return criteria.getCriteria().list();
	}
	
}
