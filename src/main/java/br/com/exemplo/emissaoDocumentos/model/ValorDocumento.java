package br.com.exemplo.emissaoDocumentos.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.exemplo.emissaoDocumentos.enumerator.TipoDocumentoEnum;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VALOR_DOCUMENTO")
@SequenceGenerator(name = "VALOR_DOCUMENTO_SEQ", sequenceName = "VALOR_DOCUMENTO_SEQ")
@NamedQueries({ @NamedQuery(name = "ValorDocumento.findAll", query = "SELECT t FROM ValorDocumento t") })
@Getter
@Setter
public class ValorDocumento {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VALOR_DOCUMENTO_SEQ")
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_DOCUMENTO")
	private TipoDocumentoEnum tipoDocumento;

	@NotNull
	@Column(name = "VALOR")
	private BigDecimal valor;
}
