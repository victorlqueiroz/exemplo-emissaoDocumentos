package br.com.exemplo.emissaoDocumentos.futronic;

import br.com.exemplo.emissaoDocumentos.futronic.api.LibAnsiSDK;
import br.com.exemplo.emissaoDocumentos.futronic.api.LibScanApi;
import br.com.exemplo.emissaoDocumentos.futronic.config.DeviceInfo;
import br.com.exemplo.emissaoDocumentos.futronic.config.FutronicImagemSize;
import br.com.exemplo.emissaoDocumentos.futronic.config.VersionInfo;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

/**
 *
 */
public class FutronicHelper {

    private static FutronicHelper _INSTANCE;
    private LibScanApi libScan;
    private LibAnsiSDK libAnsi;
    private boolean libOk;
    private Pointer sensor;
    private int dose;
    private int scoreMinimo;
    private long timeout;
    private FutronicImagemSize iSize;
    private static final long inicio = System.currentTimeMillis();
    private boolean aberto;
    private boolean dedoVivo;
    private static final Logger logger = Logger.getLogger(FutronicHelper.class);

    private FutronicHelper() {
    }

    @Override
    public String toString() {
        return "Futronic{windows=" + Platform.isWindows() + ", linux=" + Platform.isLinux() + "}";
    }

    public void setDedoVivo(boolean dedoVivo) {
        this.dedoVivo = dedoVivo;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setScoreMinimo(int scoreMinimo) {
        this.scoreMinimo = scoreMinimo;
    }

    public static FutronicHelper getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new FutronicHelper();
            _INSTANCE.setup();
        }
        return _INSTANCE;
    }

    private void setup() {
        File libFile;
        setTimeout(FutronicConstantes.TIMEOUT_PADRAO);
        setDose(FutronicConstantes.DOSE);
        setScoreMinimo(FutronicConstantes.FTR_ANSISDK_MATCH_SCORE_HIGH_MEDIUM);
        setDedoVivo(dedoVivo);
        if (Platform.isWindows()) {
            logger.info("Carregando libScan ....");
            String dir = "win_x86/";
            if (Platform.is64Bit()) {
                dir = "win_x64/";
            }
            libFile = instalarLib(dir, "ftrScanAPI.dll");
            if (libFile == null) {
                logger.error("erro no install da libScan");
                return;
            }
            libScan = (LibScanApi) Native.loadLibrary(libFile.getAbsolutePath(), LibScanApi.class);
            logger.info("Carregando libAnsi ....");
            libFile = instalarLib(dir, "ftrAnsiSdk.dll");
            if (libFile == null) {
                logger.error("erro no install da libAnsiSdk");
                return;
            }
            libAnsi = (LibAnsiSDK) Native.loadLibrary(libFile.getAbsolutePath(), LibAnsiSDK.class);
            libOk = true;
        } else if (Platform.isLinux()) {
            logger.info("Carregando libScan ....");
            String dir = "linux_x86/";
            if (Platform.is64Bit()) {
                dir = "linux_x64/";
            }
            libFile = instalarLib(dir, "libScanAPI.so");
            if (libFile == null) {
                logger.error("erro no install da libScan");
                return;
            }
            libScan = (LibScanApi) Native.loadLibrary(libFile.getAbsolutePath(), LibScanApi.class);
            logger.info("Carregando libAnsi ....");
            libFile = instalarLib(dir, "libftrAnsiSDK.so");
            if (libFile == null) {
                logger.error("erro no install da libAnsiSdk");
                return;
            }
            libAnsi = (LibAnsiSDK) Native.loadLibrary(libFile.getAbsolutePath(), LibAnsiSDK.class);
            libOk = true;
        } else {
            logger.warn("Sistema ainda nao suportado");
        }
    }

    public Pointer getSensor() {
        return sensor;
    }

    public boolean abrir() {
        sensor = libScan.ftrScanOpenDevice();
        aberto = (sensor != null);
        if (aberto && dedoVivo) {
            libScan.ftrScanSetOptions(sensor, FutronicConstantes.FTR_OPTIONS_DETECT_FAKE_FINGER, FutronicConstantes.FTR_OPTIONS_DETECT_FAKE_FINGER);
        }
        return aberto;
    }

    public byte[] getImagem() {
        byte[] saida = null;
        if (!aberto) {
            logger.warn("chamar o abrir antes");
            return saida;
        }
        iSize = new FutronicImagemSize();
        if (!libScan.ftrScanGetImageSize(sensor, iSize)) {
            logger.error("Falha ao solicitar tamanho da imagem");
        } else {
            logger.debug("Posicione o dedo no sensor...");
            ThreadSensor t = new ThreadSensor();
            t.start();
            try {
                t.join(timeout);
            } catch (InterruptedException iEx) {
            }
            if (t.isAlive()) {
                t.running = false;
                logger.warn("Dedo nao colocado no sensor!");
            } else {
                logger.debug("Capturando imagem...");
                saida = new byte[iSize.nImageSize];
                if (!libScan.ftrScanGetImage2(sensor, dose, saida)) {
                    saida = null;
                    logger.debug("Falha ao capturar imagem");
                }
            }
        }
        return saida;
    }

    public FutronicImagemSize getiSize() {
        return iSize;
    }

    public byte[] geraTemplate(byte[] imagemSensor) {
        if (!aberto) {
            throw new RuntimeException("chamar o abrir antes");
        }
        int[] retorno = new int[4];
        byte[] minuciaFinger = new byte[libAnsi.ftrAnsiSdkGetMaxTemplateSize()];
        libAnsi.ftrAnsiSdkCreateTemplateFromBuffer(sensor, 0, imagemSensor, iSize.nWidth, iSize.nHeight, minuciaFinger, retorno);
        minuciaFinger = Arrays.copyOfRange(minuciaFinger, 0, retorno[0]);
        return minuciaFinger;
    }

    public boolean comparar(byte[] minucia) {
        byte[] imagem = getImagem();
        if (imagem == null) {
            logger.warn("Sem dedo no finger para validar");
            return false;
        }
        byte[] minuciaFinger = geraTemplate(imagem);
        return comparar(minuciaFinger, minucia);
    }

    public boolean comparar(byte[] minucia1, byte[] minucia2) {
        boolean saida = false;
        int[] ret = {Float.floatToIntBits(0)};
        if (libAnsi.ftrAnsiSdkMatchTemplates(minucia1, minucia2, ret)) {
            float retFloat = Float.intBitsToFloat(ret[0]);
            logger.debug("Score: " + retFloat);
            saida = (retFloat >= scoreMinimo);
        }
        return saida;
    }

    public float compararScore(byte[] minucia1, byte[] minucia2) {
        float retFloat = 0;
        int[] ret = {Float.floatToIntBits(0)};
        if (libAnsi.ftrAnsiSdkMatchTemplates(minucia1, minucia2, ret)) {
            retFloat = Float.intBitsToFloat(ret[0]);
        }
        return retFloat;
    }

    public void fechar() {
        if (sensor != null && aberto) {
            libScan.ftrScanCloseDevice(sensor);
            aberto = false;
        }
    }

    public void esperaDedoFora() {
        logger.debug("Retire o dedo no sensor...");
        ThreadSensor t = new ThreadSensor();
        t.dedoNoSensor = false;
        t.start();
        try {
            t.join(timeout);
        } catch (InterruptedException iEx) {
        }
        if (t.isAlive()) {
            t.running = false;
            logger.warn("Dedo nao retirado no sensor!");
        }
    }

    class ThreadSensor extends Thread {

        boolean running = true;
        boolean dedoNoSensor = true;

        @Override
        public void run() {
            boolean dedo;
            while (running) {
                if (dedoNoSensor) {
                    libScan.ftrScanSetDiodesStatus(sensor, FutronicConstantes.LED_LIGADO, FutronicConstantes.LED_DESLIGADO);
                } else {
                    libScan.ftrScanSetDiodesStatus(sensor, FutronicConstantes.LED_DESLIGADO, FutronicConstantes.LED_LIGADO);
                }
                dedo = isDedoNoFinger();
                if (dedoNoSensor && dedo) {
                    break;
                }
                if (!dedoNoSensor && !dedo) {
                    break;
                }
            }
        }
    }

    public boolean isDedoNoFinger() {
        return libScan.ftrScanIsFingerPresent(sensor, null);
    }

    private File instalarLib(String dir, String lib) {
        File libFile = null;
        try {
            URL libJar = this.getClass().getResource("/" + dir + lib);
            libFile = new File(FileUtils.getTempDirectory(), inicio + "_" + dir + lib);
            libFile.deleteOnExit();
            if (libFile.exists()) {
                FileUtils.deleteQuietly(libFile);
            }
            FileUtils.copyURLToFile(libJar, libFile);
        } catch (IOException ioEx) {
            logger.warn("Erro ao gerar temp da lib", ioEx);
        }
        return libFile;
    }

    public boolean isLibOk() {
        return libOk;
    }

    public BufferedImage getImagem(byte[] imagem) {
        BufferedImage saida = null;
        if (iSize != null) {
            saida = new BufferedImage(iSize.nWidth, iSize.nHeight, BufferedImage.TYPE_BYTE_GRAY);
            DataBuffer db1 = saida.getRaster().getDataBuffer();
            for (int i = 0; i < db1.getSize(); i++) {
                db1.setElem(i, imagem[i]);
            }
        }
        return saida;
    }

    public String getSerial() {
        if (!aberto) {
            abrir();
        }
        byte[] serial = new byte[8];
        String saida = null;
        if (libScan.ftrScanGetSerialNumber(sensor, serial)) {
            if (Arrays.binarySearch(serial, (byte) -1) == -1) {
                saida = new String(serial);
            }
        }
        return saida;
    }

    public LibScanApi getLibScan() {
        return libScan;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isFeaturePresent(int nScannerFeature) {
        boolean[] saida = new boolean[1];
        libScan.ftrScanIsScannerFeaturePresent(sensor, nScannerFeature, saida);
        return saida[0];
    }

    public VersionInfo getVersion() {
        VersionInfo version = new VersionInfo();
        libScan.ftrScanGetVersion(sensor, version);
        return version;
    }

    public void getOptions(long... flags) {
        libScan.ftrScanGetOptions(sensor, flags);
    }

    public long getLastError() {
        return libScan.ftrScanGetLastError();
    }

    public String getLastErrorText() {
        return FutronicConstantes.getErrorDescription(libScan.ftrScanGetLastError());
    }

    public boolean isDedoVivo() {
        return dedoVivo;
    }

    public DeviceInfo getDeviceInfo() {
        DeviceInfo info = new DeviceInfo();
        libScan.ftrScanGetDeviceInfo(sensor, info);
        return info;
    }

    public int getScoreMinimo() {
        return scoreMinimo;
    }
}
