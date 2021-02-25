package br.com.exemplo.emissaoDocumentos.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.Usuario;

@Repository("usuarioDao")
public class UsuarioDao extends GenericDao<Usuario, AplicacaoException>
		implements Serializable {

	private static final long serialVersionUID = 1L;

	public Usuario getUsuarioPorNumeroJCE(String numeroJCE) {
		try {
			Query q = manager.createQuery("FROM Usuario u "
					+ "						WHERE u.numeroJCE = :numeroJCE");
			q.setParameter("numeroJCE", numeroJCE);
			Usuario usuario = (Usuario) q.getSingleResult();
			return usuario;
		} catch (Exception ex) {
			return null;
		}
	}

	public List<Usuario> listaUsuarioPorNome(Usuario usuario)
			throws RecursoNaoEncontradoException {
		try {
			Query q = manager.createQuery("FROM Usuario u "
					+ "						WHERE u.primeiroNome = ?"
					+ "							AND u.segundoNome = ?");
			q.setParameter(1, usuario.getPrimeiroNome());
			q.setParameter(2, usuario.getSegundoNome());
			List<Usuario> lista = (List<Usuario>) q.getResultList();
			if (lista == null) {
				return null;
			}
			return lista;
		} catch (Exception ex) {
			throw new RecursoNaoEncontradoException(ex);
		}
	}
}
