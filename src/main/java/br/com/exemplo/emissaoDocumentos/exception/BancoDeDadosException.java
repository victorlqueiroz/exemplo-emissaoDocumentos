package br.com.exemplo.emissaoDocumentos.exception;

/**
 *
 */
public class BancoDeDadosException extends Exception {

    public BancoDeDadosException(Throwable cause) {
        super(cause);
    }

    public BancoDeDadosException(String message, Throwable cause) {
        super(message, cause);
    }

    public boolean isDuplicateKeyException() {
        return getCause() != null && "DuplicateKeyException".equalsIgnoreCase(getCause().getClass().getSimpleName());
    }
}
