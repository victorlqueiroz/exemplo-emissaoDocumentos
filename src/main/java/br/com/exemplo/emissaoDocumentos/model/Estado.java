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
@Table(name = "ESTADO")
@SequenceGenerator(name = "ESTADO_SEQ", sequenceName = "ESTADO_SEQ")
@Getter
@Setter
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ESTADO_SEQ")
	private Long id;

	@NotNull
	@Size(min = 1, max = 2)
	@Column(name = "UF")
	private String uf;

	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "NOME")
	private String nome;

	@JoinColumn(name = "ID_PAIS", referencedColumnName = "ID")
	@ManyToOne(optional = false, cascade = CascadeType.DETACH)
	private Pais pais;
}
