package br.com.exemplo.emissaoDocumentos.util.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "TimeToShortConverter")
public class TimeToShortConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if(value != null) {
				return horaValida(value);
			}
			return null;
		} catch(NumberFormatException exception) {
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Hora informada não é válida!!!"));
		}
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			// short horaMinut = Short.parseShort(value.toString());
			// return horaMinut < 1000 ? (("0" + String.valueOf(horaMinut/100))) : String.valueOf(horaMinut/100) + ":" + (horaMinut%100 < 10 ? ("0" + String.valueOf(horaMinut%100)) : String.valueOf(horaMinut%100));
 			Short hora = Short.parseShort(value.toString());
			return String.format("%s:%s", String.format("%04d", hora).substring(0, 2), String.format("%04d", hora).substring(2));
		}
		return null;
	}
	
	private Short horaValida(String value) throws NumberFormatException {
		if(!value.isEmpty()) {
			String[] string = value.split(":");
			Short hora = Short.parseShort(string[0]);
			Short minut = Short.parseShort(string[1]);
			if((hora != null && minut != null) && (hora >= 0 && hora <= 23) && (minut >= 0 && minut <= 59)) {
				return (short) (hora * 100 + minut);
			} else {
				throw new NumberFormatException();
			}
		}
		return null;
	}
}
