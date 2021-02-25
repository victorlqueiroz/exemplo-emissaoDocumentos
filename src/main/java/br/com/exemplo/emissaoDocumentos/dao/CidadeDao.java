package br.com.exemplo.emissaoDocumentos.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.Cidade;
import br.com.exemplo.emissaoDocumentos.model.Estado;

@Repository("cidadeDao")
public class CidadeDao extends GenericDao<Cidade, AplicacaoException> {

	public List<Cidade> listarCidadePorEstado(Estado estado) {
		return null;
	}
}
