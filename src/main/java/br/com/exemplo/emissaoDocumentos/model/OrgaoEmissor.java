package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ORGAO_EMISSOR")
@SequenceGenerator(name = "ORGAO_EMISSOR_SEQ", sequenceName = "ORGAO_EMISSOR_SEQ")
@Getter
@Setter
public class OrgaoEmissor {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORGAO_EMISSOR_SEQ")
	private Long id;

	@NotNull
	@JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID")
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "NOME")
	private String nome;
}
