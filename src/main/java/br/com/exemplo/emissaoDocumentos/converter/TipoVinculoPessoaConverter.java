package br.com.exemplo.emissaoDocumentos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import br.com.exemplo.emissaoDocumentos.model.TipoVinculoPessoa;
import br.com.exemplo.emissaoDocumentos.util.converter.SelectItemsBaseConverter;

@Component
public class TipoVinculoPessoaConverter extends SelectItemsBaseConverter {

	@Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (((TipoVinculoPessoa) value).getId() == null) {
			return "";
		} 
        return ((TipoVinculoPessoa) value).getId().toString();
    }
}
