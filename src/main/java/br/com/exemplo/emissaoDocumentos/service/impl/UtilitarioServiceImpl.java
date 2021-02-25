package br.com.exemplo.emissaoDocumentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo.emissaoDocumentos.dao.CidadeDao;
import br.com.exemplo.emissaoDocumentos.dao.EstadoDao;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.Cidade;
import br.com.exemplo.emissaoDocumentos.model.Estado;
import br.com.exemplo.emissaoDocumentos.service.UtilitarioService;

@Service(value = "utilitarioService")
public class UtilitarioServiceImpl implements UtilitarioService {

	@Autowired
	private CidadeDao cidadeDao;
	@Autowired
	private EstadoDao estadoDao;

	@Override
	public List<Estado> listarEstado() throws RecursoNaoEncontradoException {
		return estadoDao.findAll();
	}

	@Override
	public List<Cidade> listarCidade() throws RecursoNaoEncontradoException {
		return cidadeDao.findAll();
	}

	@Override
	public List<Cidade> listaCidadePorEstado(Estado estado)
			throws RecursoNaoEncontradoException {
		return cidadeDao.listarCidadePorEstado(estado);
	}

}
