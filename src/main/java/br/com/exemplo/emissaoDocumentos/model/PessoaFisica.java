package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PESSOA_FISICA")
@PrimaryKeyJoinColumn(name = "ID")
@DiscriminatorValue("PFIS")
@Getter
@Setter
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Column(name = "DT_NASCIMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "SEXO")
	private String sexo;

	@NotNull
	@Column(name = "DOCUMENTO_IDENTIFICACAO")
	private String documentoIdentificacao;

	@Size(min = 2, max = 3)
	@Column(name = "TIPO_SANGUINEO")
	private String tipoSanguineo;

	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "pessoa")
	private List<Documento> listaDocumento;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	private List<PessoaVinculoPessoa> pessoaVinculoPessoas;
}
