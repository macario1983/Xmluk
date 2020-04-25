package com.nfehost.view;

import java.util.List;

import javax.faces.application.FacesMessage;

import lombok.Data;

import org.primefaces.model.LazyDataModel;

import com.nfehost.exception.NfeHostException;
import com.nfehost.model.Persistent;
import com.nfehost.model.filter.FilterNfeHost;
import com.nfehost.service.Service;
import com.nfehost.util.FaceHttpParameterUtil;
import com.nfehost.util.NullUtil;
import com.nfehost.util.StringUtil;
import com.nfehost.view.helper.FormHelper;

@Data
public class ManagerBean<Pojo extends Persistent, Services extends Service<Pojo>, Helper extends FormHelper<Pojo>, Filter extends FilterNfeHost> {

	// Dados constantes
	private static final String PARAMETER_MODO = "modo";
	private static final String PARAMETER_ID = "id";

	protected List<Pojo> pojos;
	protected List<Pojo> filteredPojos; // para resultado do filtro inserido no datatable
	protected LazyDataModel<Pojo> lazyModel;
	private Helper helper;
	private Services service;
	private Filter filter;
	private Pojo pojo;
	private LoginBean loginBean;

	/**
	 * Limpa a lista de pojo para recuperar nova listagem
	 */
	public void buscar() {
		this.limpar();
	}

	/**
	 * Carrega o pojo, se idPojo != null
	 */
	public void load() {
		if (!FaceHttpParameterUtil.getFacesContext().isPostback()) {
			if (!NullUtil.isNull(this.getIdPojo())) {
				this.getPojo().setId(this.getIdPojo());
				this.setPojo(this.getService().findByPojo(this.getPojo()));
			} else {
				this.setPojo(this.getService().cleanPojo(this.getPojo()));
				this.getService().fillDefaults(this.getPojo());
			}
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private Helper cleanHelper(Helper helper) {
		try {
			return (Helper) helper.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save() {
		try {
			this.getService().save(this.getPojo());
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO, "INFO.SAVE.SUCESS");
			this.limpar();
			// return "list?faces-redirect=true";
		} catch (NfeHostException e) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(e.getMessages());
			// Mantem na mesma pagina
			return;
		}
		FaceHttpParameterUtil.redirect("list.xhtml");
	}

	public void cancelar() {
		this.limpar();
		FaceHttpParameterUtil.redirect("list.xhtml");
	}

	public List<Pojo> getPojos() {
		if (NullUtil.isNull(this.pojos)) {
			this.pojos = (List<Pojo>) this.service.findByFilter(this.getFilter());
		}
		return this.pojos;
	}

	public LazyDataModel<Pojo> getLazyModel() {
		if (NullUtil.isNull(this.lazyModel)) {
			this.lazyModel = new LazyPojoDataModel<Pojo, Service<Pojo>, FilterNfeHost>(this.getService(), this.getFilter());
		}
		return this.lazyModel;
	}

	public void remove() {
		try {
			if (NullUtil.isNull(this.getPojo().getId())) {
				this.getPojo().setId(this.getIdPojo());
			}
			this.getService().remove(this.getPojo());
			FaceHttpParameterUtil.addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO, "INFO.REMOVE.SUCESS");
		} catch (NfeHostException e) {
			FaceHttpParameterUtil.addMessageFromBundleInFlash(e.getMessages());
		}
	}

	/**
	 * para habilitar ou desabilitar campos ou botões
	 * 
	 * @return true, se o modo é somente leitura.
	 */
	public Boolean isModoCreate() {
		return this.getModo().equals(Modo.CREATE);
	}

	/**
	 * limpa ou reinstacia os atributos do MB.
	 */
	private void limpar() {
		if (!NullUtil.isNull(this.getPojo())) {
			this.setPojo(this.getService().cleanPojo(this.getPojo()));
			this.getHelper().fillDefaults();
			this.getService().fillDefaults(this.getPojo());
		}
		this.setPojos(null);
		this.setLazyModel(null);
	}

	/**
	 * limpa o filtro
	 */
	public void resetFiltro() {
	}

	protected Long getIdPojo() {
		String idPojo = FaceHttpParameterUtil.getParameter(PARAMETER_ID);
		if (StringUtil.isStringNullOrEmpty(idPojo)) {
			return null;
		}
		return Long.valueOf(idPojo);
	}

	protected Modo getModo() {
		String modo = FaceHttpParameterUtil.getParameter(PARAMETER_MODO);
		if (StringUtil.isStringNullOrEmpty(modo)) {
			return Modo.READ;
		}
		return Modo.valueOf(modo.toUpperCase());
	}

	public enum Modo {
		CREATE, UPDATE, READ, DELETE;
	}

	public String getViewLog() {
		return "viewlog.xhtml";
	}

}
