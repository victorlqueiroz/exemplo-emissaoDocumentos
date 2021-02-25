package br.com.exemplo.emissaoDocumentos.service;

import java.util.List;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.Pessoa;
import br.com.exemplo.emissaoDocumentos.model.TipoVinculoPessoa;

public interface PessoaService extends AbstractService<Pessoa, AplicacaoException> {
	
	List<TipoVinculoPessoa> findAllTipoVinculoPessoa();
}
