package br.com.exemplo.emissaoDocumentos.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.Pessoa;

@Repository("pessoaDao")
public class PessoaDao extends GenericDao<Pessoa, AplicacaoException> implements Serializable {

}
