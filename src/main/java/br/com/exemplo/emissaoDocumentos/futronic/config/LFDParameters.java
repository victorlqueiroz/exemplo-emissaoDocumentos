package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class LFDParameters extends Structure {

    public int nLMin;
    public int nLMax;
    public int nCMin;
    public int nCMax;
    public int nEEMin;
    public int nEEMax;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"nLMin", "nLMax", "nCMin", "nCMax", "nEEMin", "nEEMax"});
    }

    @Override
    public String toString() {
        return "LFDParameters{nLMin=" + nLMin + ", nLMax=" + nLMax + ", nCMin=" + nCMin + ", nCMax=" + nCMax + ", nEEMin=" + nEEMin + ", nEEMax=" + nEEMax + "}";
    }
}
