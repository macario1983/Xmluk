package com.nfehost.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.nfehost.model.Emitente;
import com.nfehost.util.NullUtil;

@FacesConverter("emitenteConverter")
public class EmitenteConverter<Pojo extends Emitente> implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		try {
			if (NullUtil.isNull(value)) {
				return null;
			}
			Emitente pojo = new Emitente();
			pojo.setId(Long.valueOf(value));
			return pojo;

		} catch (IllegalArgumentException e) {
			throw new ConverterException("Não foi possível converter value em " + this.getClass().getName(), e);
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (NullUtil.isNull(value)) {
			return null;
		}

		if (value instanceof Emitente) {
			Emitente pojo = (Emitente) value;
			return pojo.getId().toString();
		} else if (value instanceof String) {
			return null;
		} else {
			throw new ConverterException("(" + value.getClass() + ") não é uma instância.");
		}
	}

}
