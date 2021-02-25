package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ENDERECO")
@SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "ENDERECO_SEQ")
@Getter
@Setter
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ENDERECO_SEQ")
	private Long id;

	@Size(max = 9)
	@Column(name = "CEP")
	private String cep;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "LOGRADOURO")
	private String logradouro;

	@Size(max = 10)
	@Column(name = "NUMERO")
	private String numero;

	@Size(max = 100)
	@Column(name = "COMPLEMENTO")
	private String complemento;

	@Size(max = 50)
	@Column(name = "BAIRRO")
	private String bairro;

	@JoinColumn(name = "ID_CIDADE", referencedColumnName = "ID")
	@ManyToOne(optional = false, cascade = CascadeType.DETACH)
	private Cidade cidade;
}
