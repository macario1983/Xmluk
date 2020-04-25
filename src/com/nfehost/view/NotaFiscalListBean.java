package com.nfehost.view;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.NotaFiscal;
import com.nfehost.model.filter.NotaFiscalFilter;
import com.nfehost.service.NotaFiscalService;
import com.nfehost.util.NullUtil;
import com.nfehost.view.helper.NotaFiscalHelper;

@EqualsAndHashCode(callSuper = false)
@Data
public class NotaFiscalListBean<Pojo extends NotaFiscal, Services extends NotaFiscalService<Pojo>, Helper extends NotaFiscalHelper<Pojo>, Filter extends NotaFiscalFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

	private NotaFiscal notaFiscal;
	private List<NotaFiscal> listNotasFiscais;
	
	private Date dataEmissaoNotaInicial;
	private Date dataEmissaoNotaFinal;
	
	public List<NotaFiscal> getListNotasFiscaisByEmitente() {
		if (this.filterIsNotEmpty()) {
			this.setListNotasFiscais(this.getService().getListNotasFiscaisByEmitente(this.getFilter()));
			return this.getListNotasFiscais();			
		}
		return null;
	}
	
	public void findDadosNotaFiscal(Long id) {
		this.setNotaFiscal(this.getService().getNotaFiscal(id));
	}
	
	public String getXmlSource() {
		return notaFiscal.getArquivoNotaFiscal().getConteudo();
	}

	private boolean filterIsNotEmpty() {
		
		return !(NullUtil.isNull(this.getFilter().getEmitente()) &&
			     NullUtil.isNull(this.getFilter().getNumeroNota()) &&
			     NullUtil.isNull(this.getFilter().getDataEmissaoNotaInicial()) &&
				 NullUtil.isNull(this.getFilter().getDataEmissaoNotaFinal()));
	}
	
	public void generatePdf(Long id) {
		this.findDadosNotaFiscal(id);
		this.getService().openJasperReportPdf(this.getNotaFiscal());
	}
}
