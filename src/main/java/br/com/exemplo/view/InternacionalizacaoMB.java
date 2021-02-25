package br.com.exemplo.view;

import java.util.Locale;

import javax.faces.context.FacesContext;

public class InternacionalizacaoMB {

	private String linguagem = "";
	private String pais = "";

	public String mudarIdioma() {
		if (!"".equals(pais)) {
			this.mudarLocalidade(new Locale(linguagem, pais));
		} else {
			this.mudarLocalidade(new Locale(linguagem));
		}
		return null;
	}

	private void mudarLocalidade(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
