package br.com.exemplo.emissaoDocumentos.view.controleAcesso;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.exemplo.emissaoDocumentos.model.Usuario;

@Component
@Scope("session")
public class UserSession implements Serializable {

    private Usuario usuario;

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario user) {
	this.usuario = user;
    }

    public boolean isLoggedIn() {
	return usuario != null;
    }
}
