package br.com.exemplo.emissaoDocumentos.model;

import br.com.exemplo.emissaoDocumentos.enumerator.OperadoraCartaoCreditoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"operadoraCartaoCredito", "proprietario", "numero", "codigoValidacao", "mes", "ano"})
public class CartaoCredito {

    private OperadoraCartaoCreditoEnum operadoraCartaoCredito;
    private String proprietario;
    private String numero;
    private Integer codigoValidacao;
    private String mes;
    private String ano;

    public OperadoraCartaoCreditoEnum[] getListaOperadoraCartaoCredito() {
        return OperadoraCartaoCreditoEnum.values();
    }
}
