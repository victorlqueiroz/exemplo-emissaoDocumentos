package br.com.exemplo.emissaoDocumentos.model.comum;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	@Id
	@NotNull
	@Column(name = "ID")
	private Long id;


}
