package br.com.exemplo.emissaoDocumentos.exception;

/**
 *
 */
public class RecursoNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public RecursoNaoEncontradoException() {
        super("Recurso não econtrado !");
    }

    public RecursoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecursoNaoEncontradoException(String message) {
        super(message);
    }

    public RecursoNaoEncontradoException(Throwable cause) {
        super(cause);
    }
}
