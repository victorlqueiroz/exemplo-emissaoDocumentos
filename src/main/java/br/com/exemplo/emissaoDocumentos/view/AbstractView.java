package br.com.exemplo.emissaoDocumentos.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.exemplo.emissaoDocumentos.view.controleAcesso.UserSession;

public abstract class AbstractView implements Serializable {

	@Autowired
	private UserSession session;

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	public String getCurrentTimeZone() {
		return TimeZone.getDefault().getID();
	}

	public void addMessageINFO(String msg) {
		FacesContext.getCurrentInstance().addMessage(msg,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void addMessageINFO(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(summary,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	public void addMessageERROR(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(summary,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
	}

	public void addMessageERROR(String msg) {
		FacesContext.getCurrentInstance().addMessage(msg,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public void addMessageWARN(String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(summary,
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
	}

	public void addMessageWARN(String msg) {
		FacesContext.getCurrentInstance().addMessage(msg,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// TODO: colocar nome do arquivo de mensagens variavel para ser setado de
	// acordo com o Locale
	public String getMessage(String key) {
		final String MENSAGENS = "mensagens.mensagens";
		Locale locale = new Locale("es");
		ResourceBundle text = ResourceBundle.getBundle(MENSAGENS, locale);
		return text.getString(key);
	}

	protected void redirecionar(String url) {
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							FacesContext.getCurrentInstance()
									.getExternalContext()
									.getRequestContextPath()
									+ "/pages/" + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
