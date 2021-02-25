package br.com.exemplo.emissaoDocumentos.util;

import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class FuncoesSystem {

    public static String fmtText(String v, String f) {
        String retorno = "";
        String texto;
        int i = 0;
        int j = 0;
        while ((texto = f.substring(i)).length() > 0 && j < v.length()) {
            if (!texto.substring(0, 1).equals("#")) {
                retorno += texto.substring(0, 1);
            } else {
                retorno += v.substring(j, j + 1);
                j++;
            }
            i++;
        }

        return retorno;
    }

    public FuncoesSystem() {
    }

    public static String cryptMD5(String texto) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(texto.getBytes());
            String senha = "";
            for (byte a : md.digest()) {
                if ((0xff & a) < 0x10) {
                    senha += "0" + Integer.toHexString(0xFF & a);
                } else {
                    senha += Integer.toHexString(0xFF & a);
                }
            }
            return senha;
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }

    public static Date getDateFromString(String data, String pattern) throws ParseException, Exception {
        Pattern p = Pattern.compile("^(([0-2]\\d|[3][0-1])[/|-|.]([0]\\d|[1][0-2])[/|-|.][2][0]\\d{2})$|^(([0-2]\\d|[3][0-1])[/|-|.]([0]\\d|[1][0-2])[/|-|.][2][0]\\d{2}\\s([0-1]\\d|[2][0-3])\\:[0-5]\\d\\:[0-5]\\d)$");
        Matcher m = p.matcher(data);
        if (!m.find()) {
            throw new Exception("Data informada não é válida");
        }
        return new SimpleDateFormat(pattern).parse(data);
    }

    public static boolean isKeyPressedEnterOrTab(KeyEvent event) {
        return event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_TAB;
    }

    public String criptografar(String texto) {

        String alfabeto = " <abcdefghijklmnopqrstuvwxyzçéáíúóãõABCDEFGHIJKLMNOPQRSTUVWXYZÇÁÉÓÍÚÃÕ1234567890.;:?,º]}§[{ª!@#$%&*()_+-=\\/|\'\">";

        char[] t = texto.toCharArray();

        String palavra = "";

        for (int i = 0; i < t.length; i++) {

            int posicao = alfabeto.indexOf(t[i]) + 5;

            if (alfabeto.length() <= posicao) {

                posicao = posicao - alfabeto.length();

            }//Criando um método de Criptografia  

            palavra = palavra + alfabeto.charAt(posicao);

        }

        return palavra;

    }
}
