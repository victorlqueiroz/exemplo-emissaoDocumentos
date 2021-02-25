package br.com.exemplo.emissaoDocumentos.util;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 */
public class DataUtils implements Serializable {

    private static final long serialVersionUID = -1695588552972696922L;

    private DataUtils() {
    }

    public static Date convertInteiroDate(Integer data, String formato) {
        SimpleDateFormat sFormato = new SimpleDateFormat(formato);
        try {
            return sFormato.parse(data.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * * Retorna somente a data
     *
     * @param data
     * @return
     */
    public static Date retornSomenteData(Date data) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = formato.format(data.getTime());
            SimpleDateFormat formatoDate = new SimpleDateFormat("dd/MM/yyyy");
            return formatoDate.parse(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converte Calendar para String no formato dd/MM/yyyyy
     *
     * @param data
     * @return
     */
    public static String calendarToDateString(Calendar data) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(data.getTime());
        } else {
            return "";
        }
    }

    /**
     * Converte Calendar para String no formato dd/MM/yyyy HH:mm:ss
     *
     * @param data
     * @return
     */
    public static String calendarToDateHoraString(Calendar data) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return formato.format(data.getTime());
        } else {
            return "";
        }
    }

    /**
     * Converte DateTime para String no formato dd/MM/yyyy HH:mm:ss
     *
     * @param data
     * @return
     */
    public static String DateHoraToString(Date data) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return formato.format(data.getTime());
        } else {
            return "";
        }
    }

    /**
     * Converte DateTime para String no formato dd/MM/yyyy HH:mm:ss
     *
     * @param data
     * @param format
     * @return
     */
    public static String DateHoraToString(Date data, String format) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat(format);
            return formato.format(data.getTime());
        } else {
            return "";
        }
    }

    /**
     * Converte Date para String no formato dd/MM/yyyy
     *
     * @param data
     * @return
     */
    public static String DateToString(Date data) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            return formato.format(data.getTime());
        } else {
            return "";
        }
    }

    /**
     * Converte String para Date no formato especificado
     *
     * @param data
     * @param formato
     * @return
     */
    public static Date stringToDate(String data, String formato) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat formatoDate = new SimpleDateFormat(formato);
            return formatoDate.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converte Calendar para String no formato especificado
     *
     * @param data
     * @param formatoData
     * @return
     */
    public static String dataPersonalizado(Calendar data, String formatoData) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat(formatoData);
            return formato.format(data.getTime());
        } else {
            return "";
        }
    }

    public static Calendar formataDate(Calendar data, String formato) {
        if (data == null) {
            data = Calendar.getInstance();
        }
        Calendar calendario = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat(formato);
        try {
            String dataFormatStr = dateFormat.format(data.getTime());
            Date novaData = dateFormat.parse(dataFormatStr);
            calendario.setTime(novaData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendario;
    }

    public static Calendar stringparaCalendar(String data) {
        Calendar calendario = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date novaData = dateFormat.parse(data);
            calendario.setTime(novaData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendario;
    }

    /**
     * Converter Tipo Date para Calendar
     *
     * @param data Data Para conver�ao
     * @return
     */
    public static Calendar DateToCalendar(Date data) {
        Calendar convt = Calendar.getInstance();
        convt.setTime(data);
        return convt;
    }

    public static double dataDiffMinutos(Calendar date) {
        Calendar dtHora = new GregorianCalendar();
        return (dtHora.getTimeInMillis() - date.getTimeInMillis()) / 60.0 / 1000.0;
    }

    public static String obterMesEAno(Date data) {
        if (data != null) {
            SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
            return formato.format(data);
        } else {
            return "";
        }
    }

    /**
     * Retorna a primeira ou �ltimo dia do m�s informado
     *
     * @param data Data que deseja retornar os dias
     * @param formato Formato da data. Ex: dd/MM/yyyy
     * @param UltimoDia [false = Primeiro dia], [true = Ultimo dia]
     * @return Date
     */
    public static String dataPrimeiroUltimoDiaMes(Date data, String formato, boolean ultimoDia) {
        return dataPrimeiroUltimoDiaMes(data, formato, ultimoDia, 1);
    }

    /**
     * Retorna a primeira ou ultimo dia do m�s informado, podendo tambem
     * adicionar ou subtrair o mes
     *
     * @param data Data que deseja retornar os dias
     * @param formato Formato da data. Ex: dd/MM/yyyy
     * @param UltimoDia [false = Primeiro dia], [true = Ultimo dia]
     * @param addSubMes {Opcional} Subtrair mes Ex: 1 ou -1 subtrair Ex:
     * 15/07/2012 = 01/06/2012
     * @return Date
     */
    public static String dataPrimeiroUltimoDiaMes(Date data, String formato, boolean UltimoDia, int addSubMes) {
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.setTime(data);
        int dia = (UltimoDia ? calendario.getActualMaximum(Calendar.DAY_OF_MONTH) : calendario.getActualMinimum(Calendar.DAY_OF_MONTH));
        int mes = (calendario.get(Calendar.MONDAY) + addSubMes);
        int ano = calendario.get(Calendar.YEAR);
        SimpleDateFormat formatoDate = new SimpleDateFormat(formato);
        Date dataProcessada = null;
        try {
            dataProcessada = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes + "/" + ano);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatoDate.format(dataProcessada);
    }

    /**
     * Subtrai ou soma a data informada em dias
     *
     * @param data Data que deseja retornar os dias
     * @param dias Quantidade de dias que deseja Subtrair ou Somar Ex: [-10] ou
     * [10]
     * @param formato Formato da data. Ex: dd/MM/yyyy
     * @return String
     */
    public static String processarSubtracaoSuma(Date data, Integer dias, String formato) {
        Calendar calendario = GregorianCalendar.getInstance();
        calendario.setTime(data);
        calendario.add(Calendar.DAY_OF_MONTH, dias);
        SimpleDateFormat formatoDate = new SimpleDateFormat(formato);
        return formatoDate.format(calendario.getTime());
    }

    /**
     * Converte String em Hora no formato java.SQL.TIME
     *
     * @param hora
     * @param formato
     * @return {@link Time}
     */
    public static Time doConvHora(String hora, String formato) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formato);
            Time time = new Time(format.parse(hora).getTime());
            return time;
        } catch (ParseException e) {
            return null;
        }
    }
}
