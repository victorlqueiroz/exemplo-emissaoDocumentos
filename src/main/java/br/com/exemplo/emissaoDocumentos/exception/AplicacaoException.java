package br.com.exemplo.emissaoDocumentos.exception;

import java.math.BigInteger;
import java.util.Calendar;

/**
 *
 */
public abstract class AplicacaoException extends Exception {

    private Calendar hora = Calendar.getInstance();
    private String versao = null;
    private String programa = null;
    private ContextoExcecao contexto = null;
    private BigInteger codigoChamado = null;
    private SeveridadeException severidade = SeveridadeException.ERROR;

    public AplicacaoException() {
        super();
    }

    public AplicacaoException(Throwable cause) {
        this(null, cause);
    }

    public AplicacaoException(String message) {
        this(message, null);
    }

    public AplicacaoException(String message, Throwable cause) {
        super(message == null && cause != null ? cause.getMessage() : message);

        if (cause != null) {
            if (getClass().isAssignableFrom(cause.getClass())) {
                initCause(cause.getCause());
            } else {
                initCause(cause);
            }
            setStackTrace(cause.getStackTrace());
        }

        if (cause instanceof AplicacaoException) {
            this.contexto = ((AplicacaoException) cause).getContexto();
        } else {
            this.contexto = new ContextoExcecao();
        }
    }

    public BigInteger getCodigoChamado() {
        return codigoChamado;
    }

    public void setCodigoChamado(BigInteger codigoChamado) {
        this.codigoChamado = codigoChamado;
    }

    public Calendar getHora() {
        return hora;
    }

    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public ContextoExcecao getContexto() {
        return contexto;
    }

    public void setContexto(ContextoExcecao contexto) {
        this.contexto = contexto;
    }

    public SeveridadeException getSeveridade() {
        return severidade;
    }

    public void setSeveridade(SeveridadeException severidade) {
        this.severidade = severidade;
    }

    public boolean isNaoEcontradoException() {
        return isInstanceOf(RecursoNaoEncontradoException.class);
    }

    public boolean isJaExisteException() {
        return isInstanceOf(RecursoJaExisteException.class);
    }

    public boolean isNaoAfetadoException() {
        return isInstanceOf(RecursoNaoAfetadoException.class);
    }

    public <T> T getRecursoExistente() {
        return getCause(RecursoJaExisteException.class).getRecursoExistente();
    }

    public boolean isInfo() {
        return SeveridadeException.INFO.equals(getSeveridade());
    }

    public boolean isWarn() {
        return SeveridadeException.WARN.equals(getSeveridade());
    }

    public boolean isError() {
        return SeveridadeException.ERROR.equals(getSeveridade());
    }

    public boolean isDatabaseException() {
        return isInstanceOf(BancoDeDadosException.class);
    }

    public BancoDeDadosException getDatabaseException() {
        return getCause(BancoDeDadosException.class);
    }

    public <T extends Exception> boolean isInstanceOf(Class<T> exceptionClass) {
        return exceptionClass.isInstance(getCause());
    }

    public <T extends Exception> T getCause(Class<T> exceptionClass) {
        return (T) getCause();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        return getClass() == obj.getClass() && hashCode() == obj.hashCode();
    }

    public StackTraceElement getLastElement() {
        return getStackTrace()[getStackTrace().length - 1];
    }

    public StackTraceElement getFirstElement() {
        return getStackTrace()[0];
    }

    public String getClassName() {
        return getClass().getName();
    }

    public String getMethodName() {
        try {
            return getLastElement().getMethodName();
        } catch (Exception ex) {
            return "";
        }
    }

    public int getLineNumber() {
        try {
            return getLastElement().getLineNumber();
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        return getClass().hashCode() + getLineNumber() + getCauseHashCode();
    }

    public int getCauseHashCode() {
        return getCause() == null ? 0 : getCauseClassName().hashCode() + getCauseLineNumber();
    }

    public int getCauseLineNumber() {
        try {
            return getCause() == null ? 0 : getFirstElement().getLineNumber();
        } catch (Exception ex) {
            return 0;
        }
    }

    public String getCauseClassName() {
        try {
            return getCause() == null ? null : getFirstElement().getClassName();
        } catch (Exception ex) {
            return "";
        }
    }

    public int getHashCode() {
        return hashCode();
    }
}
