package br.com.exemplo.emissaoDocumentos.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.emissaoDocumentos.dao.DocumentoDao;
import br.com.exemplo.emissaoDocumentos.dao.ValorDocumentoDao;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.Documento;
import br.com.exemplo.emissaoDocumentos.service.DocumentoService;

@Service(value = "documentoService")
public class DocumentoServiceImpl implements DocumentoService, Serializable {

	@Autowired
	DocumentoDao documentoDao;
	@Autowired
	ValorDocumentoDao valorDocumentoDao;

	@Override
	public Documento getById(Documento entidade)
			throws RecursoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Documento> findAll() throws RecursoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getValorDocumento(Documento documento)
			throws RecursoNaoEncontradoException {
		
		return valorDocumentoDao.getValorDocumento(documento.getTipoDocumento(documento));
	}
}
