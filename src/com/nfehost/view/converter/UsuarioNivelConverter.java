package com.nfehost.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.nfehost.model.Usuario;
import com.nfehost.model.Usuario.Nivel;
import com.nfehost.util.NullUtil;

@FacesConverter("usuarioNivelConverter")
public class UsuarioNivelConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		try {
			if(NullUtil.isNull(value)) {
				return null;
			}
			return Nivel.valueOf(value.charAt(0));

		} catch (IllegalArgumentException e){
			throw new ConverterException("Não foi possível converter value em " + this.getClass().getName(), e);
        }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (NullUtil.isNull(value))  {
            return null;
		 }
		 if (value instanceof Usuario.Nivel) {
			 Nivel Nivel = (Nivel) value;
			 return String.valueOf(Nivel.getIndex());
		 }
		 else if(value instanceof String) {
			 return value.toString();
		 }
		 else {
			 throw new ConverterException("(" + value.getClass() + ") não é uma instância.");
	    }
	}

}
