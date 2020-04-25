package com.nfehost.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.nfehost.dao.AnexoDAO;
import com.nfehost.dao.HibernateSessionFactory;
import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;

public class AnexoDAOImpl implements AnexoDAO {

	Session session = null;
	
	@Override
	public Emitente hasEmitente(Emitente emitente) {
		
		try {

			openTransaction();
			Criteria criteria = this.session.createCriteria(Emitente.class)
					
					.createAlias("dadosEmpresa", "dadosEmpresa")
					.add(Restrictions.eq("dadosEmpresa.cnpj", emitente.getDadosEmpresa().getCnpj()))
					.add(Restrictions.eq("dadosEmpresa.cpf", emitente.getDadosEmpresa().getCpf()));
			
			return (Emitente) criteria.uniqueResult();		
			
		} finally {
		
			closeTransaction();
		}
		
	}

	@Override
	public Destinatario hasDestinatario(Destinatario destinatario) {

		try {

			openTransaction();
			Criteria criteria = this.session.createCriteria(Destinatario.class)
					
					.createAlias("dadosEmpresa", "dadosEmpresa")
					.add(Restrictions.eq("dadosEmpresa.cnpj", destinatario.getDadosEmpresa().getCnpj()))
					.add(Restrictions.eq("dadosEmpresa.cpf", destinatario.getDadosEmpresa().getCpf()));
			
			return (Destinatario) criteria.uniqueResult();		

		} finally {
			
			closeTransaction();
		}
	}

	@Override
	public boolean hasNotaFiscal(NotaFiscal notaFiscal) {

		try {
			
			openTransaction();
			Criteria criteria = this.session.createCriteria(NotaFiscal.class)
					
					.createAlias("emitente", "emitente")
					.createAlias("destinatario", "destintario")
					
					.add(Restrictions.eq("numero", notaFiscal.getNumero()))
					.add(Restrictions.eq("tipoEmissao", notaFiscal.getTipoEmissao()))
					.add(Restrictions.eq("emitente.id", notaFiscal.getEmitente().getId()))
					.add(Restrictions.eq("destinatario.id", notaFiscal.getDestinatario().getId()));
			
			return criteria.list().size() > 0;
	
		} finally {
			
			closeTransaction();
		}
		
	}

	@Override
	public void save(NotaFiscal notaFiscal) {
		
		try {
			
			openTransaction();
			this.session.save(notaFiscal);
			this.session.getTransaction().commit();
			
		} 	 catch (RuntimeException e) {
			
			this.session.getTransaction().rollback();
			
			
		} finally {
			
			closeTransaction();
		}
		
	}

	private void openTransaction() {
		this.session = HibernateSessionFactory.getSession();
		this.session.beginTransaction();
	}

		
	private void closeTransaction() {

		if (this.session.isOpen()) {
			this.session.close();
		}			

	}

}
