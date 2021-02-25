package br.com.exemplo.emissaoDocumentos.service;

import java.util.List;

import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.Cidade;
import br.com.exemplo.emissaoDocumentos.model.Estado;

public interface UtilitarioService {
	
	public List<Estado> listarEstado() throws RecursoNaoEncontradoException;
	public List<Cidade> listarCidade() throws RecursoNaoEncontradoException;
	public List<Cidade> listaCidadePorEstado(Estado estado) throws RecursoNaoEncontradoException;
}
