package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CERTIDAO_DIVORCIO")
@PrimaryKeyJoinColumn(name = "ID")
@DiscriminatorValue("DIVO")
@Getter
@Setter
public class CertidaoDivorcio extends Documento {

	@NotNull
	@JoinColumn(name = "ID_ORGAO_SENTENCA", referencedColumnName = "ID")
	@ManyToOne(cascade = CascadeType.ALL)
	private OrgaoEmissor orgaoEmissor;
	
	@NotNull
	@Column(name = "NUMERO_SENTENCA")
	private Long numeroSentenca;
	
	@NotNull
	@Column(name = "DATA_SENTENCA")
	private Date dataSentenca;
	
	@NotNull
	@Size(max = 80)
	@Column(name = "MOTIVO_DIVORCIO")
	private String motivoDivorcio;
}
