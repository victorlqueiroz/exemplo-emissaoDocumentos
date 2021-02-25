package br.com.exemplo.emissaoDocumentos.enumerator;

public enum TipoDocumentoEnum {

	CASA("Acta de Matrimonio"),
	NASC("Acta de Nascimiento"),
	OBIT("Acta de Defunción"),
	DIVO("Acta de Divorcio");
	
	private String descricao;
	
	private TipoDocumentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
