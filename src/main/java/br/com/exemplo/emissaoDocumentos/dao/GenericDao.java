package br.com.exemplo.emissaoDocumentos.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoAfetadoException;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.util.LoggerUtils;

/**
 *
 * @param <T>
 */
public abstract class GenericDao<T, EXCEPTION extends AplicacaoException> {

	@PersistenceContext
	protected EntityManager manager;

	public void beforeInsert(T entidade) throws EXCEPTION {
	}

	public void beforeUpdate(T entidade) throws EXCEPTION {
	}

	public void afterInsert(T entidade) throws Exception {
	}

	public void afterUpdate(T entidade) throws Exception {
	}

	public void save(T objeto) throws RecursoNaoAfetadoException {
		try {
			manager.persist(objeto);
		} catch (Exception ex) {
			LoggerUtils.error("Erro ao salvar : ", ex.getMessage());
			throw new RecursoNaoAfetadoException(ex);
		}
	}

	public void update(T objeto) throws RecursoNaoAfetadoException {
		try {
			manager.merge(objeto);
		} catch (Exception ex) {
			throw new RecursoNaoAfetadoException(ex);
		}
	}

	public void delete(Object id) throws RecursoNaoAfetadoException {
		try {
			T objeto = getById(id);
			manager.remove(objeto);
		} catch (Exception ex) {
			throw new RecursoNaoAfetadoException(ex);
		}
	}

	public T getById(Object id) throws RecursoNaoEncontradoException {
		T result = (T) manager.find(getTypeClass(), id);
		if (result == null) {
			throw new RecursoNaoEncontradoException();
		}
		return result;
	}

	public List<T> findAll() throws RecursoNaoEncontradoException {
		List<T> resultList = manager.createQuery(("From " + getTypeClass().getName())).getResultList();
		if (resultList == null || resultList.size() < 1) {
			throw new RecursoNaoEncontradoException();
		}
		return resultList;
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}
}
