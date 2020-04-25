package com.nfehost.service.processor;

import java.util.Date;
import java.util.Map.Entry;

import lombok.Data;

import com.nfehost.model.Arquivo;
import com.nfehost.model.DadosEmpresa;
import com.nfehost.model.Destinatario;
import com.nfehost.model.Emitente;
import com.nfehost.model.Endereco;
import com.nfehost.model.Endereco.UF;
import com.nfehost.model.NotaFiscal;
import com.nfehost.model.NotaFiscal.FinalidadeEmissao;
import com.nfehost.model.NotaFiscal.IdentificadorProcessoEmissao;
import com.nfehost.model.NotaFiscal.TipoEmissao;
import com.nfehost.model.NotaFiscal.TipoOperacao;
import com.nfehost.service.processor.extraction.NFeProc;
import com.nfehost.service.processor.extraction.nfe.infNFe.dest.Dest;
import com.nfehost.service.processor.extraction.nfe.infNFe.dest.enderdest.EnderDest;
import com.nfehost.service.processor.extraction.nfe.infNFe.emit.Emit;
import com.nfehost.service.processor.extraction.nfe.infNFe.emit.enderemit.EnderEmit;
import com.nfehost.service.processor.extraction.nfe.infNFe.ide.IdentificacaoNFe;
import com.nfehost.service.processor.extraction.nfe.infNFe.total.icmstot.ICMSTotal;
import com.nfehost.service.processor.extraction.protnfe.infprot.InfProt;

@Data
public class IntegracaoProcessadoraXmlService<Pojo extends NotaFiscal> {

	public NotaFiscal createNotaFiscal(NFeProc notaFiscalProcessada, Entry<String, String> attachment) {

		IdentificacaoNFe identificacaoNFe = notaFiscalProcessada.getNfe().getInfNFe().getIdentificacaoDaNota();
		ICMSTotal valoresNFe = notaFiscalProcessada.getNfe().getInfNFe().getValoresTotaisDaNota().getIcmsTotal();
		InfProt informacaoProtocolo = notaFiscalProcessada.getProtNFe().getInfProt();
		Emit emit = notaFiscalProcessada.getNfe().getInfNFe().getEmit();
		EnderEmit enderEmit = notaFiscalProcessada.getNfe().getInfNFe().getEmit().getEndereco();
		Dest dest = notaFiscalProcessada.getNfe().getInfNFe().getDest();
		EnderDest enderDest = notaFiscalProcessada.getNfe().getInfNFe().getDest().getEndereco();

		NotaFiscal notaFiscal = new NotaFiscal();

		Emitente emitente = createEmitente(emit);
		Endereco enderecoEmitente = createEnderecoEmiente(enderEmit);
		DadosEmpresa dadosEmpresaEmitente = createDadosEmpresaEmitente(emit);

		Destinatario destinatario = createDestinatrio(dest);
		Endereco enderecoDestinatario = createEnderecoDestinatario(enderDest);
		DadosEmpresa dadosEmpresaDestinatario = createDadosEmpresaDestinatario(dest);

		Arquivo arquivoNotaFiscal = createArquivoNotaFiscal(attachment);

		notaFiscal.setDescricaoNaturezaOperacao(identificacaoNFe.getDescricaoNaturezaOperacao());
		notaFiscal.setSerie(identificacaoNFe.getSerie());
		notaFiscal.setNumero(identificacaoNFe.getNumero());
		notaFiscal.setDataEmissao(identificacaoNFe.getDataEmissao());
		notaFiscal.setDataHoraEntradaOuSaidaProduto(identificacaoNFe.getDataHoraEntradaOuSaidaProduto());
		notaFiscal.setTipoOperacao(TipoOperacao.valueOf(identificacaoNFe.getTipoOperacao()));
		notaFiscal.setTipoEmissao(TipoEmissao.valueOf(identificacaoNFe.getTipoEmissao()));
		notaFiscal.setFinalidadeEmissao(FinalidadeEmissao.valueOf(identificacaoNFe.getFinalidadeEmissao()));
		notaFiscal.setIdentificadorProcessoEmissao(IdentificadorProcessoEmissao.valueOf(identificacaoNFe.getIndentificadorProcessoEmissao()));
		notaFiscal.setValorIcms(valoresNFe.getValorICMS());
		notaFiscal.setValorNotaFiscal(valoresNFe.getValorNotaFiscal());
		notaFiscal.setChaveNfe(informacaoProtocolo.getChaveNFe());
		notaFiscal.setDigestValue(informacaoProtocolo.getDigestValue());

		emitente.setDadosEmpresa(dadosEmpresaEmitente);
		dadosEmpresaEmitente.setEndereco(enderecoEmitente);
		notaFiscal.setEmitente(emitente);

		destinatario.setDadosEmpresa(dadosEmpresaDestinatario);
		dadosEmpresaDestinatario.setEndereco(enderecoDestinatario);
		notaFiscal.setDestinatario(destinatario);

		notaFiscal.setArquivoNotaFiscal(arquivoNotaFiscal);

		return notaFiscal;

	}

	private Emitente createEmitente(Emit emit) {

		Emitente emitente = new Emitente();

		emitente.setNomeFantasia(emit.getNomeFantasia());
		emitente.setInscricaoEstadualSubstitutoTributario(emit.getInscricaoEstadualSubstitutoTributario());
		emitente.setInscricaoMunicipal(emit.getInscricaoMunicipal());
		emitente.setCnae(emit.getCNAE());


		return emitente;
	}

	private DadosEmpresa createDadosEmpresaEmitente(Emit emitDadosEmpresa) {

		DadosEmpresa dadosEmpresa = new DadosEmpresa();

		dadosEmpresa.setRazaoSocial(emitDadosEmpresa.getRazaoSocial());
		dadosEmpresa.setInscricaoEstadual(emitDadosEmpresa.getInscricaoEstadual());
		dadosEmpresa.setCnpj(emitDadosEmpresa.getCnpj());
		dadosEmpresa.setCpf(emitDadosEmpresa.getCpf());

		return dadosEmpresa;

	}

	private Endereco createEnderecoEmiente(EnderEmit enderEmit) {

		Endereco endereco = new  Endereco();

		endereco.setLogradouro(enderEmit.getLogradouro());
		endereco.setNumero(enderEmit.getNumero());
		endereco.setComplemento(enderEmit.getComplemento());
		endereco.setBairro(enderEmit.getBairro());
		endereco.setMunicipio(enderEmit.getMunicipio());
		endereco.setUf(UF.valueOf(enderEmit.getUF()));
		endereco.setCep(enderEmit.getCep());
		endereco.setTelefone(enderEmit.getTelefone());

		return endereco;
	}

	private Destinatario createDestinatrio(Dest dest) {

		Destinatario destinatario = new Destinatario();

		destinatario.setInscricaoSuframa(dest.getInscricaoSuframa());
		destinatario.setEmail(dest.getEmail());

		return destinatario;
	}

	private DadosEmpresa createDadosEmpresaDestinatario(Dest destDadosEmpresa) {

		DadosEmpresa dadosEmpresa = new DadosEmpresa();

		dadosEmpresa.setRazaoSocial(destDadosEmpresa.getRazaoSocial());
		dadosEmpresa.setInscricaoEstadual(destDadosEmpresa.getInscricaoEstadual());
		dadosEmpresa.setCnpj(destDadosEmpresa.getCnpj());
		dadosEmpresa.setCpf(destDadosEmpresa.getCpf());

		return dadosEmpresa;
	}

	private Endereco createEnderecoDestinatario(EnderDest enderDest) {

		Endereco endereco = new  Endereco();

		endereco.setLogradouro(enderDest.getLogradouro());
		endereco.setNumero(enderDest.getNumero());
		endereco.setComplemento(enderDest.getComplemento());
		endereco.setBairro(enderDest.getBairro());
		endereco.setMunicipio(enderDest.getMunicipio());
		endereco.setUf(UF.valueOf(enderDest.getUF()));
		endereco.setCep(enderDest.getCep());
		endereco.setTelefone(enderDest.getTelefone());

		return endereco;
	}

	private Arquivo createArquivoNotaFiscal(Entry<String, String> attachment) {

		Arquivo arquivoNotaFiscal = new Arquivo();

		arquivoNotaFiscal.setNome(attachment.getKey());
		arquivoNotaFiscal.setConteudo(attachment.getValue());
		arquivoNotaFiscal.setDataRegistro(new Date());

		return arquivoNotaFiscal;

	}

}
