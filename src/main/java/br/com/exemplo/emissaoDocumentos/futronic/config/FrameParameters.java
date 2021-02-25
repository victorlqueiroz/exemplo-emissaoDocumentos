package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FrameParameters extends Structure {

    public int nContrastOnDose2;
    public int nContrastOnDose4;
    public int nDose;
    public int nBrightnessOnDose1;
    public int nBrightnessOnDose2;
    public int nBrightnessOnDose3;
    public int nBrightnessOnDose4;
    public FakeReplicaParameters FakeReplicaParams;
    public byte[] Reserved = new byte[64];	// 64

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"nContrastOnDose2", "nContrastOnDose4", "nDose", "nBrightnessOnDose1", "nBrightnessOnDose2", "nBrightnessOnDose3", "nBrightnessOnDose4", "FakeReplicaParams", "Reserved"});
    }

    @Override
    public String toString() {
        return "FrameParameters{nContrastOnDose2=" + nContrastOnDose2 + ", nContrastOnDose4=" + nContrastOnDose4 + ", nDose=" + nDose + ", nBrightnessOnDose1=" + nBrightnessOnDose1 + ", nBrightnessOnDose2=" + nBrightnessOnDose2 + ", nBrightnessOnDose3=" + nBrightnessOnDose3 + ", nBrightnessOnDose4=" + nBrightnessOnDose4 + ", FakeReplicaParams=" + FakeReplicaParams + ", Reserved=" + Reserved + "}";
    }
}
