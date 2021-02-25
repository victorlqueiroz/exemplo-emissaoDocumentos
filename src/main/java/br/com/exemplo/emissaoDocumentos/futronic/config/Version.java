package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Version extends Structure {

    public short wMajorVersionHi;
    public short wMajorVersionLo;
    public short wMinorVersionHi;
    public short wMinorVersionLo;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"wMajorVersionHi", "wMajorVersionLo", "wMinorVersionHi", "wMinorVersionLo"});
    }

    @Override
    public String toString() {
        return "Version{wMajorVersionHi=" + wMajorVersionHi + ", wMajorVersionLo=" + wMajorVersionLo + ", wMinorVersionHi=" + wMinorVersionHi + ", wMinorVersionLo=" + wMinorVersionLo + "}";
    }
}
