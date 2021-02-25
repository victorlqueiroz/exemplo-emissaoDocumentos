package br.com.exemplo.emissaoDocumentos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

import br.com.exemplo.emissaoDocumentos.enumerator.TipoDocumentoEnum;

@Entity
@Table(name = "DOCUMENTO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "DOCUMENTO_SEQ", sequenceName = "DOCUMENTO_SEQ")
@NamedQueries({ @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d") })
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DOCUMENTO_SEQ")
	private Long id;
	
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@Any(metaColumn = @Column(name = "TIPO"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(value = "PFIS", targetEntity = PessoaFisica.class) })
	private Pessoa pessoa;
	
	@NotNull
	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmissao;

	@NotNull
	@Column(name = "NUMERO_LIVRO")
	private Integer numeroLivro;

	@NotNull
	@Column(name = "NUMERO_FOLHA")
	private Integer numeroFolha;

	@NotNull
	@Column(name = "NUMERO_ATA")
	private Integer numeroAta;
	
	@Column(name = "CODIGO_BARRA")
	private Long codigoBarra;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORGAO_EMISSOR", referencedColumnName = "ID")
	private OrgaoEmissor orgaoEmissor;
	
	@Transient
	private BigDecimal valor;
	
	@Transient
	private Integer quantidade = 1;
	
	public TipoDocumentoEnum getTipoDocumento(Object obj) {
		if (obj instanceof CertidaoCasamento) {
			return TipoDocumentoEnum.CASA;
		} else if (obj instanceof CertidaoNascimento) {
			return TipoDocumentoEnum.NASC;
		} else if (obj instanceof CertidaoDivorcio) {
			return TipoDocumentoEnum.DIVO;
		}
		return null;
	}
}
