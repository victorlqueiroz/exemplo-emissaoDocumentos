package br.com.exemplo.emissaoDocumentos.dao.exception;

public class DAOException extends Exception {
	
    private static final long serialVersionUID = 1L;

    public DAOException() {
        super("DAOException");
    }

    public DAOException(Exception exception) {
        super(exception);
    }
}
