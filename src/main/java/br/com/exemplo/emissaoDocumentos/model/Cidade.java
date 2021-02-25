package br.com.exemplo.emissaoDocumentos.model;

import lombok.EqualsAndHashCode;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CIDADE")
@SequenceGenerator(name = "CIDADE_SEQ", sequenceName = "CIDADE_SEQ")
@NamedQueries({ @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c") })
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CIDADE_SEQ")
	private Long id;

	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "NOME")
	private String nome;

	@JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID")
	@ManyToOne(optional = false, cascade = CascadeType.DETACH)
	private Estado estado;
}
