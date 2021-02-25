package br.com.exemplo.emissaoDocumentos.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class LoggerUtils {

    private static final Map<String, Logger> LOGGERS = Collections.synchronizedMap(new HashMap<String, Logger>());

    private static String getClassName() {
        return Thread.currentThread().getStackTrace()[4].getClassName();
    }

    private static Logger getLogger() {
        String className = getClassName();
        Logger logger = LOGGERS.get(className);
        if (logger == null) {
            logger = LoggerFactory.getLogger(className);
            LOGGERS.put(className, logger);
        }
        return logger;
    }

    public static void info(String format, Object... args) {
        if (getLogger().isInfoEnabled()) {
            getLogger().info(format, args);
        }
    }

    public static void debug(String format, Object... args) {
        if (getLogger().isDebugEnabled()) {
            getLogger().debug(format, args);
        }
    }

    public static void warn(String format, Object... args) {
        if (getLogger().isWarnEnabled()) {
            getLogger().warn(format, args);
        }
    }

    public static void error(String format, Object... args) {
        if (getLogger().isErrorEnabled()) {
            getLogger().error(format, args);
        }
    }
}
