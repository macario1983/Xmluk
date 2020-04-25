package com.nfehost.view.helper.impl;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import lombok.Data;

import com.nfehost.model.Contato;
import com.nfehost.model.Emitente;
import com.nfehost.model.NotaFiscal;
import com.nfehost.service.ContatoService;
import com.nfehost.service.EmitenteService;
import com.nfehost.service.NotaFiscalService;
import com.nfehost.view.helper.EmailHelper;

@Data
public class EmailHelperImpl implements EmailHelper {
	
	private List<Contato> listContatos;
	private List<NotaFiscal> listNotaFiscais;
	private List<Emitente> listEmitenteWithContato;
	private List<Emitente> listEmitentes;
	
	private EmitenteService<Emitente> emitenteService;
	private ContatoService<Contato> contatoService;
	private NotaFiscalService<NotaFiscal> notaFiscalService;

	public List<Emitente> getListEmitentes() {
		this.listEmitentes = (List<Emitente>) this.getEmitenteService().findAll();
		return this.listEmitentes;
	}
	
	public List<Emitente> getListEmitentesWithContato() {
		List<Long> listIds = this.getContatoService().getListEmitenteWithContato();
		return (List<Emitente>) this.getEmitenteService().getListEmitenteWithContato(listIds);			
	}

	public List<NotaFiscal> getListNotasFiscaisFiltered(Emitente emitenteNota) {
		return this.getNotaFiscalService().getListNotasFiscaisByEmitente(emitenteNota);
	}
	
	public List<Contato> getListContatosFiltered(Emitente emitenteContato) {
		return this.getContatoService().getListContatoByEmitente(emitenteContato);
	}

	@Override
	public List<Path> getSourceNotasFiscais(Set<Long> setNotasFiscais) {
		return this.getNotaFiscalService().getSourceNotasFiscais(setNotasFiscais);
	}
}
