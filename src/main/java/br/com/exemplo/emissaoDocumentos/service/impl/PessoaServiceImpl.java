package br.com.exemplo.emissaoDocumentos.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.emissaoDocumentos.dao.PessoaDao;
import br.com.exemplo.emissaoDocumentos.dao.TipoVinculoPessoaDao;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.Pessoa;
import br.com.exemplo.emissaoDocumentos.model.TipoVinculoPessoa;
import br.com.exemplo.emissaoDocumentos.service.PessoaService;

@Service(value = "pessoaService")
public class PessoaServiceImpl implements PessoaService, Serializable {

	@Autowired
	TipoVinculoPessoaDao tipoVinculoPessoaDao;
	@Autowired
	PessoaDao pessoaDao;

	@Transactional(readOnly = true)
	public List<TipoVinculoPessoa> findAllTipoVinculoPessoa() {
		try {
			return tipoVinculoPessoaDao.findAll();
		} catch (RecursoNaoEncontradoException e) {
			return null;
		}
	}

	@Override
	public Pessoa getById(Pessoa entidade) throws RecursoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> findAll() throws RecursoNaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}
}
