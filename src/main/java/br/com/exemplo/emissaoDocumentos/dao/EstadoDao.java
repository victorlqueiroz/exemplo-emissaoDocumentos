package br.com.exemplo.emissaoDocumentos.dao;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.Estado;

@Repository("estadoDao")
public class EstadoDao extends GenericDao<Estado, AplicacaoException> {

}
