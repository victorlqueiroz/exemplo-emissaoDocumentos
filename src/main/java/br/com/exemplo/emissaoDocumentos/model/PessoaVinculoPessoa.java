package br.com.exemplo.emissaoDocumentos.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
@Table(name = "PESSOA_VINCULO_PESSOA")
@SequenceGenerator(name = "PESSOA_VINCULO_PESSOA_SEQ", sequenceName = "PESSOA_VINCULO_PESSOA_SEQ")
@Getter
@Setter
public class PessoaVinculoPessoa {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PESSOA_VINCULO_PESSOA_SEQ")
	private Long id;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne
	@Any(metaColumn = @Column(name = "TIPO"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(value = "PFIS", targetEntity = PessoaFisica.class) })
	private Pessoa pessoa;

	@JoinColumn(name = "ID_PESSOA_VINCULO", referencedColumnName = "ID")
	@ManyToOne
	@Any(metaColumn = @Column(name = "TIPO"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(value = "PFIS", targetEntity = PessoaFisica.class) })
	private Pessoa pessoaVinculo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_TIPO_VINCULO_PESSOA", referencedColumnName = "ID")
	private TipoVinculoPessoa tipoVinculoPessoa;
}
