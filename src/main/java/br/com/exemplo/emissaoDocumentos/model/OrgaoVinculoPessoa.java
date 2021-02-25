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
@Table(name = "ORGAO_VINCULO_PESSOA")
@SequenceGenerator(name = "ORGAO_VINCULO_PESSOA_SEQ", sequenceName = "ORGAO_VINCULO_PESSOA_SEQ")
@Getter
@Setter
public class OrgaoVinculoPessoa {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORGAO_VINCULO_PESSOA_SEQ")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_orgao_emissor")
	private OrgaoEmissor orgaoEmissor;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@Any(metaColumn = @Column(name = "TIPO"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(value = "PFIS", targetEntity = PessoaFisica.class) })
	private PessoaFisica pessoaFisica;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_VINCULO_ORGAO", referencedColumnName = "ID")
	private TipoVinculoOrgao tipoVinculoOrgao;
}
