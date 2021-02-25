package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TIPO_VINCULO_ORGAO")
@SequenceGenerator(name = "TIPO_VINCULO_ORGAO_SEQ", sequenceName = "TIPO_VINCULO_ORGAO_SEQ")
@NamedQueries({ @NamedQuery(name = "TipoVinculoOrgao.findAll", query = "SELECT t FROM TipoVinculoOrgao t") })
@Getter
@Setter
public class TipoVinculoOrgao {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TIPO_VINCULO_ORGAO_SEQ")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 25)
	@Column(name = "DESCRICAO")
	private String descricao;
}
