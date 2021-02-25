package br.com.exemplo.emissaoDocumentos.futronic.api;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 *
 */
public interface LibAnsiSDK extends Library {

    void ftrAnsiSdkCreateTemplate(Pointer sensor, int byFingerPosition, byte[] imagemSaida, byte[] minucia,
            int[] tamanhoMinucia);

    void ftrAnsiSdkCreateTemplateFromBuffer(Pointer sensor, int byFingerPosition, byte[] imagemSensor, int nWidth,
            int nHeight, byte[] minucia, int[] retorno);

    boolean ftrAnsiSdkCaptureImage(Pointer sensor, byte[] buffer);

    int ftrAnsiSdkGetMaxTemplateSize();

    boolean ftrAnsiSdkMatchTemplates(byte[] minucia1, byte[] minucia2, int[] retorno);
}
