package br.com.exemplo.emissaoDocumentos.view;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class PessoaView extends AbstractView{

	/**
	 *
	 */
	private static final long	serialVersionUID	= 5867504273243078182L;

	@PostConstruct
	public void init(){
	}

	public static long getSerialversionuid(){
		return serialVersionUID;
	}
}
