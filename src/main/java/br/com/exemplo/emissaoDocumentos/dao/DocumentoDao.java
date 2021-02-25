package br.com.exemplo.emissaoDocumentos.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.Documento;

@Repository("documentoDao")
public class DocumentoDao extends GenericDao<Documento, AplicacaoException> implements Serializable {

}
