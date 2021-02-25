package br.com.exemplo.emissaoDocumentos.service;

import java.math.BigDecimal;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.Documento;

public interface DocumentoService extends AbstractService<Documento, AplicacaoException> {
		
	public BigDecimal getValorDocumento(Documento documento) throws RecursoNaoEncontradoException;
}
