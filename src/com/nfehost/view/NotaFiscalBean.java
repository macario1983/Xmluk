package com.nfehost.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.primefaces.event.FileUploadEvent;

import com.nfehost.model.NotaFiscal;
import com.nfehost.model.filter.NotaFiscalFilter;
import com.nfehost.service.NotaFiscalService;
import com.nfehost.util.FileUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;
import com.nfehost.view.helper.NotaFiscalHelper;

@EqualsAndHashCode(callSuper = false)
@Data
public class NotaFiscalBean<Pojo extends NotaFiscal, Services extends NotaFiscalService<Pojo>, Helper extends NotaFiscalHelper<Pojo>, Filter extends NotaFiscalFilter>
		extends ManagerBean<Pojo, Services, Helper, Filter> {

	private Map<String, String> map;
	private List<String> listFilename;

	public void uploadFile(FileUploadEvent event) {

		if (NullUtil.isEmptyMap(this.map)) {
			this.map = new HashMap<>();			
		}

		if (NullUtil.isEmpty(this.listFilename)) {
			this.listFilename = new ArrayList<>();
		}

		try {
			String source = FileUtil.takeOffBOM(event.getFile().getInputstream());
			String filename = event.getFile().getFileName();
			source = StringUtil.normalize(source);
			this.getListFilename().add(filename);
			this.getMap().put(filename, source);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void process() {

		if (!NullUtil.isEmptyMap(this.getMap())) {
			for (Entry<String, String> attachment : this.getMap().entrySet()) {
				this.getService().validateXmlFile(attachment);
			}
			this.getMap().clear();
			this.getListFilename().clear();
		}
	}
	
	
	public String getFileFooter() {
		return NullUtil.isEmptyMap(this.getMap()) ? "Não há arquivo(s) selecionado(s)" : this.getMap().size() + " arquivos selecionados";
	}
}
