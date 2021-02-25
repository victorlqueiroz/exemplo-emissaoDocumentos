package br.com.exemplo.emissaoDocumentos.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.exemplo.emissaoDocumentos.model.CertidaoNascimento;
import br.com.exemplo.emissaoDocumentos.model.Documento;
import br.com.exemplo.emissaoDocumentos.model.PessoaFisica;
import br.com.exemplo.emissaoDocumentos.relatorio.CertidaoNascimentoREL;

public class CertidaoNascimentoTest {
	public static void main(String[] args) {
		try {
			List<Documento> listaCertidaoNascimento = new ArrayList<Documento>();

			Documento nasc1 = new CertidaoNascimento();
			PessoaFisica p = new PessoaFisica();
			p.setNome("Eclipse");
			nasc1.setPessoa(p);
			nasc1.setNumeroLivro(0254);
			nasc1.setNumeroFolha(032);
			nasc1.setNumeroAta(064);

			listaCertidaoNascimento.add(nasc1);

			CertidaoNascimentoREL relatorio = new CertidaoNascimentoREL();
//			relatorio.imprimir(listaCertidaoNascimento);
			relatorio.gerarRelatorio(listaCertidaoNascimento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
