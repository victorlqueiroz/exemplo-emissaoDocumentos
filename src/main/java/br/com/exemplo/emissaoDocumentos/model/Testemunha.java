package br.com.exemplo.emissaoDocumentos.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
@Table(name = "TESTEMUNHA")
@SequenceGenerator(name = "TESTEMUNHA_SEQ", sequenceName = "TESTEMUNHA_SEQ")
@NamedQueries({ @NamedQuery(name = "Testemunha.findAll", query = "SELECT t FROM Testemunha t") })
@Getter
@Setter
public class Testemunha implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TESTEMUNHA_SEQ")
	private Long id;

	@JoinColumn(name = "ID_DOCUMENTO", referencedColumnName = "ID")
	@OneToOne(optional = false, cascade = CascadeType.DETACH)
	@Any(metaColumn = @Column(name = "TIPO"), fetch = FetchType.EAGER)
	@AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(value = "CASA", targetEntity = CertidaoCasamento.class) })
	private Documento documento;

	@NotNull
	@Size(max = 50)
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@Column(name = "NUMERO_DOCUMENTO")
	private Long numeroDocumento;
}
