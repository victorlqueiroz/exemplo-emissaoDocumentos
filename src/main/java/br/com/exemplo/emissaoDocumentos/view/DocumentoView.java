package br.com.exemplo.emissaoDocumentos.view;

import br.com.exemplo.emissaoDocumentos.enumerator.OperadoraCartaoCreditoEnum;
import br.com.exemplo.emissaoDocumentos.enumerator.TipoDocumentoEnum;
import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.model.*;
import br.com.exemplo.emissaoDocumentos.relatorio.CertidaoNascimentoREL;
import br.com.exemplo.emissaoDocumentos.service.DocumentoService;
import br.com.exemplo.emissaoDocumentos.service.PessoaService;
import br.com.exemplo.emissaoDocumentos.util.DataTablePF;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
public class DocumentoView extends AbstractView {

    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private PessoaService pessoaService;

    final static int PAI = 1;
    final static int MAE = 2;
    final static int FILHO = 3;
    final static int CONJUGE = 4;

    private DataTablePF<Documento> tableDocumento;
    private List<Documento> selectMultiple;
    private Documento documento;
    private TipoDocumentoEnum tipoDocumento;
    private Pessoa pessoa;
    private CartaoCredito cartaoCredito;
    private BigDecimal valorTotal;
    private List<Pessoa> listaPessoaVinculada;
    private byte[] arquivoPdf;
    private boolean exibirDialog = true;

    @PostConstruct
    public void init() {
        tableDocumento = new DataTablePF<Documento>();
        tableDocumento.setSelectedItem(null);
        tableDocumento.setMutiplySelectedList(new ArrayList<Documento>());
        documento = new CertidaoCasamento();
        // documento.setPessoa(new PessoaFisica());
        pessoa = new PessoaFisica();
        cartaoCredito = new CartaoCredito();
        cartaoCredito
                .setOperadoraCartaoCredito(OperadoraCartaoCreditoEnum.VISA);
        valorTotal = BigDecimal.ZERO;
        listaPessoaVinculada = new ArrayList<Pessoa>();
        tipoDocumento = TipoDocumentoEnum.CASA;
    }

    public void onChangeTipoDocumento(TipoDocumentoEnum tipoDocumento) {
        listaPessoaVinculada = new ArrayList<Pessoa>();
        criarInstanciaDocumentoPorTipo(tipoDocumento);
        carregarListaPessoasPorTipo(tipoDocumento);
    }

    private void criarInstanciaDocumentoPorTipo(TipoDocumentoEnum tipoDocumento) {
        switch (tipoDocumento) {
            case CASA:
                documento = new CertidaoCasamento();
                break;
            case NASC:
                documento = new CertidaoNascimento();
                break;
            case DIVO:
                documento = new CertidaoDivorcio();
                break;
            default:
                break;
        }
    }

    /**
     * Carrega a lista de pessoas possíveis de acordo com o tipo de documento a
     * ser emitido.
     *
     * @param tipoDocumento
     */
    private void carregarListaPessoasPorTipo(TipoDocumentoEnum tipoDocumento) {
        Usuario usuario = getSession().getUsuario();
        PessoaFisica pessoaFisica = (PessoaFisica) usuario.getPessoa();
        int tipoVinculo = getTipoVinculoPessoa(tipoDocumento);
        for (PessoaVinculoPessoa p : pessoaFisica.getPessoaVinculoPessoas()) {
            if (p.getTipoVinculoPessoa().getId() == tipoVinculo
                    || tipoVinculo == 0) {
                listaPessoaVinculada.add(p.getPessoaVinculo());
            }
        }
    }

    /**
     * Retorna o tipo de vinculo de acordo com o tipo de documento a ser
     * emitido. Para Certidão de Óbito, poderá ser emitido para todas as pessoas
     * que se pusse vínvulo.
     * <p>
     * TODO: Refatorar para que a definição do tipo seja recuperada pelo próprio
     * banco de dados sem o uso de constante.
     *
     * @param tipoDocumento
     * @return tipoVinculo
     */
    private int getTipoVinculoPessoa(TipoDocumentoEnum tipoDocumento) {
        switch (tipoDocumento) {
            case CASA:
                return CONJUGE;
            case NASC:
                return FILHO;
            case DIVO:
                return CONJUGE;
            default:
                return 0;
        }
    }

    public List<TipoVinculoPessoa> findAllTipoVinculoPessoa() {
        return pessoaService.findAllTipoVinculoPessoa();
    }

    public void inserirDocumentoLista() {
        if (verificarDadosDocumentoInformado()) {
            try {
                documento.setValor(documentoService
                        .getValorDocumento(documento));
                valorTotal = valorTotal.add(documento.getValor().multiply(
                        new BigDecimal(documento.getQuantidade().intValue())));
            } catch (RecursoNaoEncontradoException e) {
                FacesContext.getCurrentInstance().addMessage(
                        getMessage("msg.erro.inserir"),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                getMessage("msg.erro.inserir"), ""));
            }
            documento.setPessoa(pessoa);
            if (!tableDocumento.getListItem().contains(documento)) {
                tableDocumento.getListItem().add(documento);
            } else {
                Documento doc = tableDocumento.getListItem().get(tableDocumento.getListItem().indexOf(documento));
                doc.setQuantidade(doc.getQuantidade() + 1);
            }

            setExibirDialog(false);
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    getMessage("msg.erro.dadosObrigatorios"),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            getMessage("msg.erro.dadosObrigatorios"), ""));
        }
    }

    private boolean verificarDadosDocumentoInformado() {
        return pessoa != null && documento.getQuantidade() != null;
    }

    public void novoDocumento() {
        documento = new CertidaoCasamento();
        // documento.setPessoa(new PessoaFisica());
        pessoa = new PessoaFisica();
        tipoDocumento = TipoDocumentoEnum.CASA;
        onChangeTipoDocumento(tipoDocumento);
    }

    public void excluirDocumento(Documento documento) {
        valorTotal = valorTotal.subtract(documento.getValor().multiply(
                new BigDecimal(documento.getQuantidade().intValue())));
        tableDocumento.getListItem().remove(documento);
    }

    public void nextPagamento() {
        if (tableDocumento.getListItem().size() > 0) {
            redirecionar("pagamento/pagamento.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    getMessage("documento.emptyMessage"),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            getMessage("documento.emptyMessage"), ""));
        }
    }

    public void backDocumento() {
        redirecionar("documento/lista_documento.xhtml");
    }

    public void nextImpressao() throws IOException {
        if (pagamentoConfirmado()) {
            List<Documento> listaCertidaoNascimento = new ArrayList<Documento>();
            listaCertidaoNascimento.addAll(tableDocumento.getListItem());

            for (Documento documento : listaCertidaoNascimento) {
                documento.setNumeroLivro(0254);
                documento.setNumeroFolha(032);
                documento.setNumeroAta(064);
            }

            CertidaoNascimentoREL relatorio = new CertidaoNascimentoREL();

            try {
                arquivoPdf = relatorio.gerarRelatorio(listaCertidaoNascimento);
            } catch (JRException e) {
                FacesContext.getCurrentInstance().addMessage(
                        getMessage("msg.erro.imprimir"),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                getMessage("msg.erro.imprimir"), ""));
            }
            redirecionar("documento/finalizarAtendimento.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    getMessage("msg.erro.pagamento"),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            getMessage("msg.erro.pagamento"), ""));
        }
    }

    private boolean pagamentoConfirmado() {
        CartaoCredito cartao = new CartaoCredito();
        cartao.setOperadoraCartaoCredito(OperadoraCartaoCreditoEnum.VISA);
        cartao.setProprietario("JOAO");
        cartao.setNumero("1111222233334444");
        cartao.setCodigoValidacao(123);
        cartao.setMes("01");
        cartao.setAno("2020");

        return cartaoCredito.equals(cartao);
    }

    public void reiniciar() {
        redirecionar("documento/lista_documento.xhtml");
        init();
    }

    //    public void logout() {
    //        init();
    //        SecurityContextHolder.clearContext();
    //        getSession().setUsuario(null);
    //        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    //        redirecionar("login.xhtml");
    //    }

    public StreamedContent arquivoPdf() {
        if (arquivoPdf == null) {
            return null;
        }

        return new DefaultStreamedContent(new ByteArrayInputStream(arquivoPdf), "application/pdf");
    }

    public DataTablePF<Documento> getTableDocumento() {
        return tableDocumento;
    }

    public void setTableDocumento(DataTablePF<Documento> tableDocumento) {
        this.tableDocumento = tableDocumento;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public TipoDocumentoEnum[] getTipoDocumentoEnum() {
        return tipoDocumento.values();
    }

    public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoDocumentoEnum getTipoDocumento() {
        return tipoDocumento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Documento> getSelectMultiple() {
        return selectMultiple;
    }

    public void setSelectMultiple(List<Documento> selectMultiple) {
        this.selectMultiple = selectMultiple;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartao) {
        this.cartaoCredito = cartao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Pessoa> getListaPessoaVinculada() {
        return listaPessoaVinculada;
    }

    public void setListaPessoaVinculada(List<Pessoa> listaPessoaVinculada) {
        this.listaPessoaVinculada = listaPessoaVinculada;
    }

    public byte[] getArquivoPdf() {
        return arquivoPdf;
    }

    public void setArquivoPdf(byte[] arquivoPdf) {
        this.arquivoPdf = arquivoPdf;
    }

    public boolean isExibirDialog() {
        return exibirDialog;
    }

    public void setExibirDialog(boolean exibirDialog) {
        this.exibirDialog = exibirDialog;
    }
}
