package br.com.exemplo.emissaoDocumentos.service;

import java.util.List;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoAfetadoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.futronic.FutronicHelper;
import br.com.exemplo.emissaoDocumentos.model.Usuario;

public interface UsuarioService extends
		AbstractService<Usuario, AplicacaoException> {

	void save(Usuario usuario) throws RecursoNaoAfetadoException;

	void update(Usuario usuario) throws RecursoNaoAfetadoException;

	Usuario getUsuarioPorNumeroJCE(String numeroJCE)
			throws RecursoNaoEncontradoException;

	List<Usuario> listaUsuarioPorNome(Usuario usuario)
			throws RecursoNaoEncontradoException;

	boolean loginSpringSecurity(Usuario usuario, byte[] loginBiometria,
			FutronicHelper fh);
}
