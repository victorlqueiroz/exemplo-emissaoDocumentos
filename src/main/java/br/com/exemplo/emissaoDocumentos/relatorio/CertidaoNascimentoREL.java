package br.com.exemplo.emissaoDocumentos.relatorio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import br.com.exemplo.emissaoDocumentos.model.Documento;

public class CertidaoNascimentoREL {
	private String path; // Caminho base

	private String pathToReportPackage; // Caminho para o package onde estão
										// armazenados os relatorios Jarper

	// Recupera os caminhos para que a classe possa encontrar os relatórios
	public CertidaoNascimentoREL() {
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/exemplo/emissaoDocumentos/jasper/";
		System.out.println(path);
	}

	// Imprime/gera uma lista de Clientes
	public void imprimir(List<Documento> listaCertidaoNascimento)
			throws Exception {
		JasperReport report = JasperCompileManager.compileReport(this
				.getPathToReportPackage() + "CertidaoNascimento.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(listaCertidaoNascimento));

		JasperExportManager.exportReportToPdfFile(print,
				"C:/Certidao_de_Nascimento.pdf");
	}

	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}

	public String getPath() {
		return this.path;
	}

	public byte[] gerarRelatorio(List<Documento> listaCertidaoNascimento)
			throws JRException, IOException {

		FacesContext face = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) face
				.getExternalContext().getRequest();

		String anoRegistro = "2015";
		String localNascimento = "Maternidade Ela";
		String dataEmissaoExtenso = "Quatro de Setembro de Dois Mil e Quinze (04/09/2015)";
		String diaEmissaoExtenso = "Quatro (04)";
		String mesEmissaoExtenso = "Setembro";
		String anoEmissaoExtenso = "Dois Mil e Quinze (2015)";
		String dataNascimentoExtenso = "Três de Setembro de Dois Mil e Quinze (03/09/2015)";
		String sexo = "Masculino";
		String pai = "JOAO DO NASCIMENTO";
		String identidadePai = "542789-001";
		String mae = "MARIA CLARA DO NASCIMENTO";
		Map parametros = new HashMap<String, String>();

		parametros.put("anoRegistro", anoRegistro);
		parametros.put("localNascimento", localNascimento);
		parametros.put("dataEmissaoExtenso", dataEmissaoExtenso);
		parametros.put("diaEmissaoExtenso", diaEmissaoExtenso);
		parametros.put("mesEmissaoExtenso", mesEmissaoExtenso);
		parametros.put("anoEmissaoExtenso", anoEmissaoExtenso);
		parametros.put("dataNascimentoExtenso", dataNascimentoExtenso);
		parametros.put("sexo", sexo);
		parametros.put("pai", pai);
		parametros.put("identidadePai", identidadePai);
		parametros.put("mae", mae);
		parametros.put("realPath", request.getRealPath(""));

		String path = request.getRealPath("/jasper/CertidaoNascimento.jasper");
		FileInputStream arquivo = null;
		try {
			arquivo = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// JasperReport jasperReport = (JasperReport)
		// JRLoader.loadObject(arquivo);
		// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
		// parametros, new JRBeanCollectionDataSource(
		// listaCertidaoNascimento));
		// JasperPrintManager.printReport(jasperPrint, false);
		// JasperViewer.viewReport(jasperPrint, false);

		// /////////PDF
		// FileInputStream fin =
		// Utilitarios.carregarJasper("BorderoGODUA.jasper");
		// HashMap parameters = new HashMap();

		JasperPrint jasperPrint = JasperFillManager.fillReport(arquivo,
				parametros, new JRBeanCollectionDataSource(
						listaCertidaoNascimento));
        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
        exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
        exporter.exportReport();
        return pdfReport.toByteArray();
	}
}
