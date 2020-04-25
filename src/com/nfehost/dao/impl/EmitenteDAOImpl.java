package com.nfehost.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;

import com.nfehost.dao.EmitenteDAO;
import com.nfehost.dao.HibernateSessionFactory;
import com.nfehost.dao.hibernate.CriterionFactory;
import com.nfehost.model.Contato;
import com.nfehost.model.DadosEmpresa;
import com.nfehost.model.Emitente;
import com.nfehost.model.filter.EmitenteFilter;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.util.NullUtil;

public class EmitenteDAOImpl<Pojo extends Emitente> extends HibernateDAOImpl<Pojo> implements EmitenteDAO<Pojo> {
	
	@Override
	protected Criteria setUpCriteria(Criteria criteria, FilterNfeHost filter) {

		if (!NullUtil.isNull(filter) && filter instanceof EmitenteFilter) {

			EmitenteFilter filtro = (EmitenteFilter) filter;
			criteria.createAlias("pojo.dadosEmpresa", "dadosEmpresa");

			return CriterionFactory.getInstance(criteria)
				.ilike("dadosEmpresa.razaoSocial", filtro.getRazaoSocial())
				.eq("dadosEmpresa.cnpj", filtro.getCnpj())
				.eq("dadosEmpresa.inscricaoEstadual", filtro.getInscricaoEstadual())
				.getCriteria();
		}

		return criteria;	
	}

	@SuppressWarnings("unchecked")
	public List<Emitente> findContatos() {
		
		String sql = "SELECT emitente.id, dados_empresa.razao_social, dados_empresa.cnpj, contato.nome, contato.email, contato.telefone from contato "
					+"inner join emitente on (contato.emitente_id = emitente.id) "
					+"inner join dados_empresa on (dados_empresa.id = emitente.dados_empresa_id)";
		
		SQLQuery query = HibernateSessionFactory.getSession().createSQLQuery(sql);
		
		List<Object> listObjects =  query.list();
		
		return createListEmitentes(listObjects);
	}
	
	
	private List<Emitente> createListEmitentes(List<Object> listObjects) {
		
		Map<Long, Emitente> mapEmitente = new HashMap<>();
		List<Contato> listaContatos = null;
		Contato contato = null;
		DadosEmpresa dadosEmpresa = null;
		Emitente emitente = null;
		
		for (Object arrayObject : listObjects) {
			
			Object[] object = (Object[]) arrayObject;
			
			int i = 0;
			
			Long indice = ((Number) object[i]).longValue();
			
			if (!mapEmitente.containsKey(indice)) {
				
				emitente = new Emitente();
				emitente.setId(indice);
				
				dadosEmpresa = new DadosEmpresa();
				emitente.setDadosEmpresa(dadosEmpresa);
				emitente.getDadosEmpresa().setRazaoSocial((String) object[++i]);
				emitente.getDadosEmpresa().setCnpj((String) object[++i]);
				
				contato = new Contato();
				contato.setNome((String) object[++i]);
				contato.setEmail((String) object[++i]);
				contato.setTelefone((String) object[++i]);

				listaContatos = new ArrayList<>();
				listaContatos.add(contato);
				emitente.setListaContatos(listaContatos);
				
				mapEmitente.put(emitente.getId(), emitente);
				
			} else {
				
				emitente = mapEmitente.get(indice);
				
				contato = new Contato();
				contato.setNome((String) object[++i]);
				contato.setEmail((String) object[++i]);
				contato.setTelefone((String) object[++i]);

				emitente.getListaContatos().add(contato);
			}
			
		}
		
		return new ArrayList<>(mapEmitente.values());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emitente> getListEmitenteWithContato(List<Long> listIds) {
		
		CriterionFactory criteria = CriterionFactory.getInstance(this.createCriteria(Emitente.class));
		criteria.in("pojo.id", listIds);
		
		return criteria.getCriteria().list();
		
	}

}
