package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FakeReplicaParameters extends Structure {

    public boolean bCalculated;
    public int nCalculatedSum1;
    public int nCalculatedSumFuzzy;
    public int nCalculatedSumEmpty;
    public int nCalculatedSum2;
    public double dblCalculatedTremor;
    public double dblCalculatedValue;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"bCalculated", "nCalculatedSum1", "nCalculatedSumFuzzy", "nCalculatedSumEmpty", "nCalculatedSum2", "dblCalculatedTremor", "dblCalculatedValue"});
    }

    @Override
    public String toString() {
        return "FakeReplicaParameters{bCalculated=" + bCalculated + ", nCalculatedSum1=" + nCalculatedSum1 + ", nCalculatedSumFuzzy=" + nCalculatedSumFuzzy + ", nCalculatedSumEmpty=" + nCalculatedSumEmpty + ", nCalculatedSum2=" + nCalculatedSum2 + ", dblCalculatedTremor=" + dblCalculatedTremor + ", dblCalculatedValue=" + dblCalculatedValue + "}";
    }
}
