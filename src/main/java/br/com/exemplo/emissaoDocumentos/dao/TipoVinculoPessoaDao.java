package br.com.exemplo.emissaoDocumentos.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.TipoVinculoPessoa;

@Repository("tipoVinculoPessoaDao")
public class TipoVinculoPessoaDao extends GenericDao<TipoVinculoPessoa, AplicacaoException> implements Serializable {

}
