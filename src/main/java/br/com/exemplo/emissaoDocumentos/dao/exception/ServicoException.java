package br.com.exemplo.emissaoDocumentos.dao.exception;

public class ServicoException extends Exception {
	
    private static final long serialVersionUID = 1L;

    public ServicoException() {
        super("ServicoException");
    }

    public ServicoException(Exception exception) {
        super(exception);
    }
}
