package com.nfehost.service.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.nfehost.service.processor.extraction.NFeProc;
import com.nfehost.service.processor.extraction.nfe.infNFe.cobr.dup.Dup;
import com.nfehost.service.processor.extraction.nfe.infNFe.dest.Dest;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.Det;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.icms.Icms;
import com.nfehost.service.processor.extraction.nfe.infNFe.det.imposto.pis.Pis;
import com.nfehost.service.processor.extraction.nfe.infNFe.emit.Emit;
import com.nfehost.service.processor.extraction.nfe.infNFe.ide.IdentificacaoNFe;
import com.nfehost.service.processor.extraction.nfe.infNFe.total.icmstot.ICMSTotal;
import com.nfehost.service.processor.extraction.nfe.infNFe.transp.Transp;
import com.nfehost.service.processor.extraction.nfe.infNFe.transp.vol.Vol;
import com.nfehost.service.processor.extraction.protnfe.infprot.InfProt;
import com.nfehost.util.DateUtil;
import com.nfehost.util.NFeUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;

public class Report {
	
	public byte[] pdfBytes(NFeProc nFeProc) {

		String reportFolder = "/Report/";
	    String archiveName = getReportFileName(nFeProc);
	    reportFolder = reportFolder.concat(archiveName).concat(".jrxml");
	    
	    String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(reportFolder);
	    
		try {

			Map<String, Object> parameters = new HashMap<>();
			this.parametersDuplicata(parameters, nFeProc);
			this.parametersISSQNTotais(parameters, nFeProc);
			this.parametersVolumeTransportado(parameters, nFeProc);
			this.parametersDadosTransportadora(parameters, nFeProc);
			this.parametersIdentificadoresNFe(parameters, nFeProc);
			this.parametersValoresTotais(parameters, nFeProc);
			this.parametersDestinatario(parameters, nFeProc);
			this.parametersEmitente(parameters, nFeProc);

			List<NFeItens> nFeItensList = generateListItens(nFeProc);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(nFeItensList);
			JasperReport pathjrmxl = JasperCompileManager.compileReport(realPath);

			pathjrmxl = JasperCompileManager.compileReport(realPath);
			JasperPrint printReport = JasperFillManager.fillReport(pathjrmxl, parameters, dataSource);

			return JasperExportManager.exportReportToPdf(printReport);

		} catch (JRException e) {
			e.printStackTrace();
		}

		return null;

	}	
	
	private void parametersDuplicata(Map<String, Object> parameters, NFeProc nFeProc) {
		
		List<Dup> listDup = null;
		
		if (!NullUtil.isNull(nFeProc.getNfe().getInfNFe().getCobr()) && 
		   (!NullUtil.isNull(nFeProc.getNfe().getInfNFe().getCobr().getListDuplicata()))) {
			 
			listDup = nFeProc.getNfe().getInfNFe().getCobr().getListDuplicata();
			
			int dupIndex = 1;
			
			for (Dup dup : listDup) {
				
				parameters.put("numDuplicata00" + dupIndex, dup.getNumero());
				parameters.put("venctDuplicata00" + dupIndex, dup.getDataVencimento());
				parameters.put("vlrDuplicata00" + dupIndex, dup.getValor());
				dupIndex++;
			}

		}
	}
	
	private void parametersISSQNTotais(Map<String, Object> parameters, NFeProc nFeProc) {
		
		if (!NullUtil.isNull(nFeProc.getNfe().getInfNFe().getListDet().get(0).getImposto().getIssqn())) {
			parameters.put("baseCalculoISSQN", nFeProc.getNfe().getInfNFe().getListDet().get(0).getImposto().getIssqn().getValorBaseCalculo());
			parameters.put("inscmunISSQN", null);
			parameters.put("valorISSQN", nFeProc.getNfe().getInfNFe().getListDet().get(0).getImposto().getIssqn().getValorIssqn());
			parameters.put("valorServISSQN", null);
		}

	}
	
	private void parametersVolumeTransportado(Map<String, Object> parameters, NFeProc nFeProc) {
		
		List<Vol> listVol = null;
		
		if (!NullUtil.isEmpty(nFeProc.getNfe().getInfNFe().getTransp().getListVolume())) {
			
			listVol = nFeProc.getNfe().getInfNFe().getTransp().getListVolume();
			
			int volIndex = 2;
			String volAdd = "";
			
			for (Vol vol : listVol) {
				
				if (volIndex == 5) {
					break;
				}
				
				parameters.put("especieTransp" + volAdd, vol.getEspecie());
				parameters.put("marcaTransp" + volAdd, vol.getMarca());
				parameters.put("numeracaoTransp" + volAdd, vol.getNumeracaoVolume());
				parameters.put("pesoBrutoTransp" + volAdd, vol.getPesoBruto());
				parameters.put("pesoLiquidoTransp" + volAdd, vol.getPesoLiquido());
				parameters.put("quantTransp" + volAdd, vol.getVolume());
				
				volAdd = volAdd.concat("00" + volIndex);
				volIndex++;			
			}		
		}
		
	}
	
	private void parametersDadosTransportadora(Map<String, Object> parameters, NFeProc nFeProc) {
		
		Transp transp = nFeProc.getNfe().getInfNFe().getTransp();

		if (!NullUtil.isNull(transp)) {
			parameters.put("freteTransp", transp.getModoFrete() == 0 ? 1 : 2);
		}

		if (!NullUtil.isNull(transp.getTransporta())) {
			
			parameters.put("cnpjTransp", transp.getTransporta().getCnpj());
			parameters.put("inscestTransp", transp.getTransporta().getInscricaoEstadual());
			parameters.put("municipioTransp", transp.getTransporta().getMunicipio());
			parameters.put("razaoTransp", transp.getTransporta().getRazaoSocial());
			parameters.put("ufTransp", transp.getTransporta().getUf());

		}

		if (!NullUtil.isNull(transp.getVeiculoTransporte())) {
			
			parameters.put("codANTTTransp", transp.getVeiculoTransporte().getRegistroANTT());
			parameters.put("placaTransp", transp.getVeiculoTransporte().getPlaca());
			parameters.put("ufVeiculoTransp", transp.getVeiculoTransporte().getUF());

		}

	}
	
	private void parametersIdentificadoresNFe(Map<String, Object> parameters, NFeProc nFeProc) {
		
		IdentificacaoNFe identificacaoNFe = nFeProc.getNfe().getInfNFe().getIdentificacaoDaNota();
		InfProt infProt = nFeProc.getProtNFe().getInfProt();
		
		String id = infProt.getChaveNFe();
		String protocolo = infProt.getNumeroProtocolo().concat(" ");
		protocolo = protocolo.concat(DateUtil.formatDateHour(infProt.getDataHoraRecebimento()));
		
		parameters.put("codigoBarra", id);
		parameters.put("chaveNfe", NFeUtil.formatChave(id));
		parameters.put("dataEmissao", identificacaoNFe.getDataEmissao());
		parameters.put("dataEntrada", identificacaoNFe.getDataHoraEntradaOuSaidaProduto());
		parameters.put("dataSaida", identificacaoNFe.getDataHoraEntradaOuSaidaProduto());
		parameters.put("horaSaida", identificacaoNFe.getHoraSaidaEntrada());
		parameters.put("natopNf", identificacaoNFe.getDescricaoNaturezaOperacao());
		parameters.put("numeroNf", identificacaoNFe.getNumero());
		parameters.put("protocoloDataRecibo", protocolo);
		parameters.put("serieNf", identificacaoNFe.getSerie());
		parameters.put("tpNf", identificacaoNFe.getTipoOperacao());
		parameters.put("tpAmb", identificacaoNFe.getTipoAmbiente());
		
	}
	
	private void parametersValoresTotais(Map<String, Object> parameters, NFeProc nFeProc) {

		ICMSTotal icmsTotal = nFeProc.getNfe().getInfNFe().getValoresTotaisDaNota().getIcmsTotal();
		
		parameters.put("baseCalculo", icmsTotal.getValorBaseCalculo());
		parameters.put("baseCalculoST", icmsTotal.getValorBaseCalculoST());
		parameters.put("valorDesconto", icmsTotal.getValorDesconto());
		parameters.put("valorFrete", icmsTotal.getValorFrete());
		parameters.put("valorICMS", icmsTotal.getValorICMS());
		parameters.put("valorICMSST", icmsTotal.getValorICMSST());
		parameters.put("valorIPI", icmsTotal.getValorIPI());
		parameters.put("valorNota", icmsTotal.getValorNotaFiscal());
		parameters.put("valorOutro", icmsTotal.getValorOutro());
		parameters.put("valorProd", icmsTotal.getValorProduto());
		parameters.put("valorSeguro", icmsTotal.getValorSeguro());
		
	}

	private void parametersDestinatario(Map<String, Object> parameters, NFeProc nFeProc) {
		
		Dest dest = nFeProc.getNfe().getInfNFe().getDest();
		String endereco = StringUtil.formatarEndereco(dest.getEndereco());

		parameters.put("bairroDest", dest.getEndereco().getBairro());
		parameters.put("cepDest", dest.getEndereco().getCep());
		parameters.put("cnpjDest", dest.getCnpj());
		parameters.put("enderecoDest", endereco);
		parameters.put("foneDest", dest.getEndereco().getTelefone());
		parameters.put("inscestDest", dest.getInscricaoEstadual());
		parameters.put("municipioDest", dest.getEndereco().getMunicipio());
		parameters.put("razaoDest", dest.getRazaoSocial());
		parameters.put("ufDest", dest.getEndereco().getUF());

	}
	
	private void parametersEmitente(Map<String, Object> parameters, NFeProc nFeProc) {
		
		Emit emit = nFeProc.getNfe().getInfNFe().getEmit();
		String endereco = StringUtil.formatarEndereco(emit.getEndereco());

		parameters.put("bairroEmitente", emit.getEndereco().getBairro());
		parameters.put("cepEmitente", emit.getEndereco().getCep());
		parameters.put("cnpjEmitente", emit.getCnpj());
		parameters.put("enderecoEmitente", endereco);
		parameters.put("foneEmitente", emit.getEndereco().getTelefone());
		parameters.put("inscestEmitente", emit.getInscricaoEstadual());
		parameters.put("inscestEmitenteSt", emit.getInscricaoEstadualSubstitutoTributario());
		parameters.put("municipioEmitente", emit.getEndereco().getMunicipio());
		parameters.put("razaoEmitente", emit.getRazaoSocial());
		parameters.put("ufEmitente", emit.getEndereco().getUF());
	
	}
	
	private List<NFeItens> generateListItens(NFeProc nFeProc) {
		
		List<Det> listDest = nFeProc.getNfe().getInfNFe().getListDet();
		List<NFeItens> listNFeItens = new ArrayList<>(listDest.size());
		
		for (Det det : listDest) {
			
			NFeItens nFeItens = new NFeItens();
			nFeItens.setCodigo(det.getProd().getCodigo());
			nFeItens.setNome(det.getProd().getNome());
			nFeItens.setNcm(det.getProd().getNcm());
			nFeItens.setCST(processCST(det.getImposto().getPis()));
			nFeItens.setCFOP(det.getProd().getCodigoDeOperacaoPrestacao());
			nFeItens.setUnidade(det.getProd().getUnidadeComercial());
			nFeItens.setQuantidade(det.getProd().getQuantidadeComercial());
			nFeItens.setValorUnitario(det.getProd().getValorUnitario());
			nFeItens.setValorDesconto(det.getProd().getValorDesconto());
			nFeItens.setValorTotal(det.getProd().getValorTotal());
			nFeItens.setValorIcms(processValorIcms(det.getImposto().getIcms()));
			//nFeItens.setValorIpi(processValorIpi());
			nFeItens.setPorcentagemIcms(processPorcentagemIcms(det.getImposto().getIcms()));
			//nFeItens.setPorcentagemIpi(processPorcentagemIpi());
			
			listNFeItens.add(nFeItens);
			
		}
		
		return listNFeItens;		
	}
	
	private Integer processCST(Pis pis) {
		
		if (!NullUtil.isNull(pis.getPisAliq())) {
			return pis.getPisAliq().getCst();
		}
		
		if (!NullUtil.isNull(pis.getPisNt())) {
			return pis.getPisNt().getCst();
		}
		
		if (!NullUtil.isNull(pis.getPisOutr())) {
			return pis.getPisOutr().getCst();
		}
		
		if (!NullUtil.isNull(pis.getPisQtde())) {
			return pis.getPisQtde().getCst();
		}
		
		return null;
	}

	private Double processPorcentagemIcms(Icms icms) {

		if (!NullUtil.isNull(icms.getIcms00())) {
			return icms.getIcms00().getPercentualIcms();
		}

		if (!NullUtil.isNull(icms.getIcms10())) {
			return icms.getIcms10().getPercentualIcms();
		}

		if (!NullUtil.isNull(icms.getIcms20())) {
			return icms.getIcms20().getPercentualIcms();
		}
		
		if (!NullUtil.isNull(icms.getIcms30())) {
			return null;
		}

		if (!NullUtil.isNull(icms.getIcms40())) {
			return null;
		}

		if (!NullUtil.isNull(icms.getIcms51())) {
			return icms.getIcms51().getPercentualIcms();
		}

		if (!NullUtil.isNull(icms.getIcms60())) {
			return null;
		}

		if (!NullUtil.isNull(icms.getIcms70())) {
			return icms.getIcms70().getPercentualIcms();
		}

		if (!NullUtil.isNull(icms.getIcms90())) {
			return icms.getIcms90().getPercentualIcms();
		}

		return null;
	}

	private BigDecimal processValorIcms(Icms icms) {

		if (!NullUtil.isNull(icms.getIcms00())) {
			return icms.getIcms00().getValorIcms();
		}

		if (!NullUtil.isNull(icms.getIcms10())) {
			return icms.getIcms10().getValorIcms();
		}

		if (!NullUtil.isNull(icms.getIcms20())) {
			return icms.getIcms20().getValorIcms();
		}
		
		if (!NullUtil.isNull(icms.getIcms30())) {
			return null;
		}

		if (!NullUtil.isNull(icms.getIcms40())) {
			return icms.getIcms40().getValorIcms();
		}

		if (!NullUtil.isNull(icms.getIcms51())) {
			return icms.getIcms51().getValorIcms();
		}

		if (!NullUtil.isNull(icms.getIcms60())) {
			return null;
		}

		if (!NullUtil.isNull(icms.getIcms70())) {
			return icms.getIcms70().getValorIcms();
		}

		if (!NullUtil.isNull(icms.getIcms90())) {
			return icms.getIcms90().getValorIcms();
		}

		return null;
	}
	
	private String getReportFileName(NFeProc nFeProc) {
		return getOrientationDocument(nFeProc) == 1 ? "dretrato" : "dpaisagem";
	}

	private int getOrientationDocument(NFeProc nFeProc) {
		return nFeProc.getNfe().getInfNFe().getIdentificacaoDaNota().getTipoImpressao();
	}
	
}
