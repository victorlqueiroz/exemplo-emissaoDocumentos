package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "SEQUENCE_PESSOA", sequenceName = "PESSOA_SEQ")
@Getter
@Setter
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE_PESSOA")
	private Long id;

	@NotNull
	@Column(name = "DT_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegistro;

	@Column(name = "DT_EXCLUSAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExclusao;
}
