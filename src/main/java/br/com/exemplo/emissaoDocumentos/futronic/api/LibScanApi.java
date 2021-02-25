package br.com.exemplo.emissaoDocumentos.futronic.api;

import br.com.exemplo.emissaoDocumentos.futronic.config.DeviceInfo;
import br.com.exemplo.emissaoDocumentos.futronic.config.FrameParameters;
import br.com.exemplo.emissaoDocumentos.futronic.config.FutronicImagemSize;
import br.com.exemplo.emissaoDocumentos.futronic.config.LFDParameters;
import br.com.exemplo.emissaoDocumentos.futronic.config.VersionInfo;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

/**
 *
 */
public interface LibScanApi extends Library {

    Pointer ftrScanOpenDevice();

    boolean ftrScanGetImage2(Pointer sensor, int nDose, byte[] imagemSaida);

    boolean ftrScanGetImageSize(Pointer sensor, FutronicImagemSize tamanho);

    void ftrScanCloseDevice(Pointer sensor);

    boolean ftrScanIsFingerPresent(Pointer sensor, FrameParameters params);

    boolean ftrScanSetDiodesStatus(Pointer sensor, int byGreenDiodeStatus, int byRedDiodeStatus);

    boolean ftrScanGetSerialNumber(Pointer sensor, byte[] serial);

    boolean ftrScanSetOptions(Pointer sensor, long dwMask, long dwFlags);

    boolean ftrScanGetDeviceInfo(Pointer sensor, DeviceInfo info);

    boolean ftrScanGetOptions(Pointer sensor, long[] lpdwFlags);

    boolean ftrScanGetProperty(Pointer ftrHandle, int nProperty, byte[] pPropertyData);

    boolean ftrScanSetProperty(Pointer sensor, int nProperty, byte[] pPropertyData);

    boolean ftrScanGetLFDParameters(LFDParameters pLFDParameters);

    boolean ftrScanIsScannerFeaturePresent(Pointer ftrHandle, int nScannerFeature, boolean[] pIsPresent);

    boolean ftrScanGetVersion(Pointer ftrHandle, VersionInfo pVersionInfo);

    long ftrScanGetLastError();
}
