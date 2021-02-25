package br.com.exemplo.emissaoDocumentos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import br.com.exemplo.emissaoDocumentos.model.Pessoa;
import br.com.exemplo.emissaoDocumentos.util.converter.SelectItemsBaseConverter;

@Component
public class PessoaConverter extends SelectItemsBaseConverter {

	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (((Pessoa) value).getId() == null) {
			return "";
		} 
        return ((Pessoa) value).getId().toString();
    }
}
