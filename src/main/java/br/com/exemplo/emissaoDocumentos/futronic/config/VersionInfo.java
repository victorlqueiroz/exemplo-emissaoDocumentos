package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class VersionInfo extends Structure {

    public long dwVersionInfoSize;
    public Version APIVersion;
    public Version HardwareVersion;
    public Version FirmwareVersion;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"dwVersionInfoSize", "APIVersion", "HardwareVersion", "FirmwareVersion"});
    }

    @Override
    public String toString() {
        return "VersionInfo{" + "dwVersionInfoSize=" + dwVersionInfoSize + ", APIVersion=" + APIVersion + ", HardwareVersion=" + HardwareVersion + ", FirmwareVersion=" + FirmwareVersion + '}';
    }
}
