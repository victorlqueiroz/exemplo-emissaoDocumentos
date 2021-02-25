package br.com.exemplo.emissaoDocumentos.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.exemplo.emissaoDocumentos.util.DataUtils;

/**
 *
 */
@FacesConverter(value = "DataConverter")
public class DataConverter implements Converter {

    @SuppressWarnings("deprecation")
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value != null) {
                if (value.split(" ").length > 1) {
                    Date dataHora = validarData(value, "dd/MM/yyyy HH:mm");
                    if (dataHora.getHours() == 0 && dataHora.getMinutes() == 0) {
                        throw new Exception("Data invalida!!!");
                    }
                    return dataHora;
                } else {
                    return validarData(value, "dd/MM/yyyy");
                }
            }
            return null;
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro converter", e.getMessage()));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        return DataUtils.DateHoraToString((Date) value);
    }

    private Date validarData(String value, String pattern) throws ParseException {
        if (value == null || value.isEmpty()) {
            return null;
        }
        SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
        formatDate.setLenient(false);
        return formatDate.parse(value);
    }
}
