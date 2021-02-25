package br.com.exemplo.emissaoDocumentos.exception;

/**
 *
 */
public class RecursoNaoAfetadoException extends Exception {

    private static final long serialVersionUID = 1L;

    public RecursoNaoAfetadoException() {
        super();
    }

    public RecursoNaoAfetadoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecursoNaoAfetadoException(String message) {
        super(message);
    }

    public RecursoNaoAfetadoException(Throwable cause) {
        super(cause);
    }

}
