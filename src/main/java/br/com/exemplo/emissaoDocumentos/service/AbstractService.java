package br.com.exemplo.emissaoDocumentos.service;

import java.util.List;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;

public abstract interface AbstractService<T, EXCEPTION extends AplicacaoException> {

    T getById(T entidade) throws RecursoNaoEncontradoException;
    
    List<T> findAll() throws RecursoNaoEncontradoException;
}
