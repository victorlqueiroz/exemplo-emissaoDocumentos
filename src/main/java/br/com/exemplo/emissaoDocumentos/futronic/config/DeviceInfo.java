package br.com.exemplo.emissaoDocumentos.futronic.config;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DeviceInfo extends Structure {

    public long dwStructSize;
    public byte byDeviceCompatibility;
    public short wPixelSizeX;
    public short wPixelSizeY;

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[]{"dwStructSize", "byDeviceCompatibility", "wPixelSizeX", "wPixelSizeY"});
    }

    @Override
    public String toString() {
        return "DeviceInfo{dwStructSize=" + dwStructSize + ", byDeviceCompatibility=" + byDeviceCompatibility + ", wPixelSizeX=" + wPixelSizeX + ", wPixelSizeY=" + wPixelSizeY + "}";
    }
}
