package br.com.exemplo.emissaoDocumentos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CERTIDAO_CASAMENTO")
@PrimaryKeyJoinColumn(name = "ID")
@DiscriminatorValue("CASA")
@Getter
@Setter
public class CertidaoCasamento extends Documento {

	@NotNull
	@Column(name = "DATA_CASAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCasamento;
	
	@OneToMany(mappedBy = "documento")
	private List<Testemunha> listaTestemunha;
}
