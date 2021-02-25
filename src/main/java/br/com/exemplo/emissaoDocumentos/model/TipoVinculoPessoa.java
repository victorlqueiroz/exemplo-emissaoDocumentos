package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
@Table(name = "TIPO_VINCULO_PESSOA")
@SequenceGenerator(name = "TIPO_VINCULO_PESSOA_SEQ", sequenceName = "TIPO_VINCULO_PESSOA_SEQ")
@NamedQueries({ @NamedQuery(name = "TipoVinculoPessoa.findAll", query = "SELECT t FROM TipoVinculoPessoa t") })
@Getter
@Setter
public class TipoVinculoPessoa implements Serializable {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TIPO_VINCULO_PESSOA_SEQ")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 25)
	@Column(name = "DESCRICAO")
	private String descricao;
}
