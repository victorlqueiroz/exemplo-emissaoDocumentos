package br.com.exemplo.emissaoDocumentos.enumerator;

public enum OperadoraCartaoCreditoEnum {
	
	VISA("Visa"),
	MASTER("MasterCard"),
	AMERICAN("American Express");
	
	private String descricao;
	
	private OperadoraCartaoCreditoEnum(String descricao) {
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
