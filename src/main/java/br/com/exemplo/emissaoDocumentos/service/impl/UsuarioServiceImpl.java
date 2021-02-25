package br.com.exemplo.emissaoDocumentos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.emissaoDocumentos.dao.UsuarioDao;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoAfetadoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.futronic.FutronicHelper;
import br.com.exemplo.emissaoDocumentos.model.Usuario;
import br.com.exemplo.emissaoDocumentos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void save(Usuario usuario) throws RecursoNaoAfetadoException {
		try {
			usuarioDao.save(usuario);
		} catch (RecursoNaoAfetadoException ex) {
			throw ex;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Usuario usuario) throws RecursoNaoAfetadoException {
		try {
			usuarioDao.update(usuario);
		} catch (RecursoNaoAfetadoException ex) {
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() throws RecursoNaoEncontradoException {
		List<Usuario> list = new ArrayList<Usuario>();
		try {
			list = usuarioDao.findAll();
			return list;
		} catch (RecursoNaoEncontradoException ex) {
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario getById(Usuario entidade)
			throws RecursoNaoEncontradoException {
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioDao.getById(entidade.getId());
			return usuario;
		} catch (RecursoNaoEncontradoException ex) {
			throw ex;
		}
	}

	@Override
	public Usuario getUsuarioPorNumeroJCE(String numeroJCE)
			throws RecursoNaoEncontradoException {
		return usuarioDao.getUsuarioPorNumeroJCE(numeroJCE);
	}

	@Override
	public List<Usuario> listaUsuarioPorNome(Usuario usuario)
			throws RecursoNaoEncontradoException {
		return usuarioDao.listaUsuarioPorNome(usuario);
	}

	@Override
	public boolean loginSpringSecurity(Usuario usuario, byte[] loginBiometria,
			FutronicHelper fh) {
		if (loginBiometria != null && usuario.getDigital() != null) {
			byte[] dig1 = fh.geraTemplate(usuario.getDigital());
			byte[] dig2 = fh.geraTemplate(loginBiometria);
			return fh.comparar(dig1, dig2);
		}
		return false;
	}
}
