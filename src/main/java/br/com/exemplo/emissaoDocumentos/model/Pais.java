package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PAIS")
@SequenceGenerator(name = "PAIS_SEQ", sequenceName = "PAIS_SEQ")
@Getter
@Setter
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PAIS_SEQ")
	private Long id;

	@NotNull
	@Size(min = 1, max = 3)
	@Column(name = "SIGLA")
	private String sigla;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "NOME")
	private String nome;
}
