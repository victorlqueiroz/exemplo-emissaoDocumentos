package br.com.exemplo.emissaoDocumentos.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Perfil implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
}
