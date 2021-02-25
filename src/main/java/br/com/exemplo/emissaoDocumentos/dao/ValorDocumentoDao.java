package br.com.exemplo.emissaoDocumentos.dao;

import java.math.BigDecimal;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.exemplo.emissaoDocumentos.enumerator.TipoDocumentoEnum;
import br.com.exemplo.emissaoDocumentos.exception.AplicacaoException;
import br.com.exemplo.emissaoDocumentos.model.ValorDocumento;

@Repository("valorDocumentoDao")
public class ValorDocumentoDao extends GenericDao<ValorDocumento, AplicacaoException> {

	public BigDecimal getValorDocumento(TipoDocumentoEnum tipoDocumento) {
		try {
			Query q = manager.createQuery("SELECT v.valor FROM ValorDocumento v "
					+ "						WHERE v.tipoDocumento = :tipoDocumento");
			q.setParameter("tipoDocumento", tipoDocumento);
			return (BigDecimal) q.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}
}
