package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FutronicImagemSize extends Structure {

    public int nWidth;
    public int nHeight;
    public int nImageSize;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"nWidth", "nHeight", "nImageSize"});
    }
}
