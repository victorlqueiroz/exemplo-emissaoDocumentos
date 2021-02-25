package br.com.exemplo.emissaoDocumentos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import br.com.exemplo.emissaoDocumentos.model.Cidade;
import br.com.exemplo.emissaoDocumentos.util.converter.SelectItemsBaseConverter;

@Component
public class CidadeConverter extends SelectItemsBaseConverter {

	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (((Cidade) value).getId() == null) {
			return "";
		} 
        return ((Cidade) value).getId().toString();
    }
}
