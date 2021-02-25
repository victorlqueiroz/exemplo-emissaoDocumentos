package br.com.exemplo.emissaoDocumentos.exception;

/**
 *
 */
public class RecursoJaExisteException extends Exception {

    private static final long serialVersionUID = 1L;
    private Object recurso = null;

    public RecursoJaExisteException() {
        super("Recurso já existe !");
    }

    public RecursoJaExisteException(Object recurso) {
        super();
        this.recurso = recurso;
    }

    public <T> T getRecursoExistente() {
        return (T) recurso;
    }
}
