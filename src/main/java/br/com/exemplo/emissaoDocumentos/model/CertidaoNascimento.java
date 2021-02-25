package br.com.exemplo.emissaoDocumentos.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "CERTIDAO_NASCIMENTO")
@PrimaryKeyJoinColumn(name = "ID")
@DiscriminatorValue("NASC")
@Getter
@Setter
public class CertidaoNascimento extends Documento {

	@NotNull
	@JoinColumn(name = "ID_LOCAL_NASCIMENTO", referencedColumnName = "ID")
	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco localNascimento;
	
	@NotNull
	@Column(name = "DATA_NASCIMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@NotNull
	@Type(type="true_false")  	
	@Column(name = "COM_VIDA")
	private Boolean comVida;
	
	@NotNull
	@Size(max = 15)
	private String declarante;
}
