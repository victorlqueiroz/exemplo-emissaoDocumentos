package br.com.exemplo.emissaoDocumentos.futronic;

/**
 *
 */
public final class FutronicConstantes {

    public static final long TIMEOUT_PADRAO = 15000;
    public static final int DOSE = 7;
    //
    public static final int FTR_ANSISDK_MATCH_SCORE_LOW = 81;
    public static final int FTR_ANSISDK_MATCH_SCORE_LOW_MEDIUM = 105;
    public static final int FTR_ANSISDK_MATCH_SCORE_MEDIUM = 132;
    public static final int FTR_ANSISDK_MATCH_SCORE_HIGH_MEDIUM = 158;
    public static final int FTR_ANSISDK_MATCH_SCORE_HIGH = 192;
    public static final int FTR_ANSISDK_MATCH_SCORE_VERY_HIGH = 223;
    //
    public static final int LED_DESLIGADO = 0;
    public static final int LED_LIGADO = 255;
    //
    public static final long FTR_OPTIONS_CHECK_FAKE_REPLICA = 0x00000001;
    public static final long FTR_OPTIONS_DETECT_FAKE_FINGER = FTR_OPTIONS_CHECK_FAKE_REPLICA;
    public static final long FTR_OPTIONS_FAST_FINGER_DETECT_METHOD = 0x00000002;
    public static final long FTR_OPTIONS_RECEIVE_LONG_IMAGE = 0x00000004;
    public static final long FTR_OPTIONS_RECEIVE_FAKE_IMAGE = 0x00000008;
    public static final long FTR_OPTIONS_SCALE_IMAGE = 0x00000010;
    public static final long FTR_OPTIONS_IMPROVE_IMAGE = 0x00000020;
    //
    public static final long FTR_OPTIONS_INVERT_IMAGE = 0x00000040;
    public static final long FTR_OPTIONS_PREVIEW_MODE = 0x00000080;
    public static final long FTR_OPTIONS_IMAGE_FORMAT_MASK = 0x00000700;
    public static final long FTR_OPTIONS_IMAGE_FORMAT_1 = 0x00000100;
    public static final long FTR_OPTIONS_ELIMINATE_BACKGROUND = 0x00000800;
    public static final long FTR_OPTIONS_IMPROVE_BACKGROUND = 0x00001000;
    //
    public static final int FTR_SCANNER_FEATURE_LFD = 1;
    public static final int FTR_SCANNER_FEATURE_DIODES = 2;
    public static final int FTR_SCANNER_FEATURE_GET_IMAGE2 = 3;
    public static final int FTR_SCANNER_FEATURE_SERIAL_NUMBER = 4;
    public static final int FTR_SCANNER_FEATURE_LONG_IMAGE = 5;
    public static final int FTR_SCANNER_FEATURE_IS_CALIBRATED = 6;
    public static final int FTR_SCANNER_FEATURE_IS_LFD_CALIBRATED = 7;
    public static final int FTR_SCANNER_FEATURE_ROLL = 8;
    //
    public static final int FTR_PARAM_IMAGE_WIDTH = 1;
    public static final int FTR_PARAM_IMAGE_HEIGHT = 2;
    public static final int FTR_PARAM_IMAGE_SIZE = 3;
    public static final int FTR_PARAM_CB_FRAME_SOURCE = 4;
    public static final int FTR_PARAM_CB_CONTROL = 5;
    public static final int FTR_PARAM_MAX_MODELS = 10;
    public static final int FTR_PARAM_MAX_TEMPLATE_SIZE = 6;
    public static final int FTR_PARAM_MAX_FAR_REQUESTED = 7;
    public static final int FTR_PARAM_MAX_FARN_REQUESTED = 13;
    public static final int FTR_PARAM_SYS_ERROR_CODE = 8;
    public static final int FTR_PARAM_FAKE_DETECT = 9;
    public static final int FTR_PARAM_FFD_CONTROL = 11;
    public static final int FTR_PARAM_MIOT_CONTROL = 12;
    public static final int FTR_PARAM_VERSION = 14;
    public static final int FTR_PARAM_CHECK_TRIAL = 15;
    //
    private static final long FTR_ERROR_BASE = 0x20000000;
    public static final long FTR_ERROR_NO_ERROR = 0;
    public static final long FTR_ERROR_EMPTY_FRAME = 4306;
    public static final long FTR_ERROR_MOVABLE_FINGER = FTR_ERROR_CODE(0x0001);
    public static final long FTR_ERROR_NO_FRAME = FTR_ERROR_CODE(0x0002);
    public static final long FTR_ERROR_USER_CANCELED = FTR_ERROR_CODE(0x0003);
    public static final long FTR_ERROR_HARDWARE_INCOMPATIBLE = FTR_ERROR_CODE(0x0004);
    public static final long FTR_ERROR_FIRMWARE_INCOMPATIBLE = FTR_ERROR_CODE(0x0005);
    public static final long FTR_ERROR_INVALID_AUTHORIZATION_CODE = FTR_ERROR_CODE(0x0006);
    public static final long FTR_ERROR_ROLL_NOT_STARTED = FTR_ERROR_CODE(0x0007);
    public static final long FTR_ERROR_ROLL_PROGRESS_DATA = FTR_ERROR_CODE(0x0008);
    public static final long FTR_ERROR_ROLL_TIMEOUT = FTR_ERROR_CODE(0x0009);
    public static final long FTR_ERROR_ROLL_ABORTED = FTR_ERROR_CODE(0x000A);
    public static final long FTR_ERROR_ROLL_ALREADY_STARTED = FTR_ERROR_CODE(0x000B);
    public static final long FTR_ERROR_ROLL_PROGRESS_REMOVE_FINGER = FTR_ERROR_CODE(0x000C);
    public static final long FTR_ERROR_ROLL_PROGRESS_PUT_FINGER = FTR_ERROR_CODE(0x000D);
    public static final long FTR_ERROR_ROLL_PROGRESS_POST_PROCESSING = FTR_ERROR_CODE(0x000E);
    public static final long FTR_ERROR_FINGER_IS_PRESENT = FTR_ERROR_CODE(0x000F);
    public static final long FTR_ERROR_NULL_PARAMETER = FTR_ERROR_CODE(0x0010);
    public static final long FTR_ERROR_LIBUSB_ERROR = FTR_ERROR_CODE(0x0011);
    public static final long FTR_ERROR_VERSION_NOT_SUPPORTED = FTR_ERROR_CODE(0x0012);
    public static final long FTR_ERROR_BAD_CALLBACK_FUNCTION = FTR_ERROR_CODE(0x0013);
    public static final long FTR_ERROR_NO_MORE_ITEMS = 259;
    public static final long FTR_ERROR_NOT_ENOUGH_MEMORY = 8;
    public static final long FTR_ERROR_NO_SYSTEM_RESOURCES = 1450;
    public static final long FTR_ERROR_TIMEOUT = 1460;
    public static final long FTR_ERROR_NOT_READY = 21;
    public static final long FTR_ERROR_BAD_CONFIGURATION = 1610;
    public static final long FTR_ERROR_INVALID_PARAMETER = 87;
    public static final long FTR_ERROR_CALL_NOT_IMPLEMENTED = 120;
    public static final long FTR_ERROR_NOT_SUPPORTED = 50;
    public static final long FTR_ERROR_WRITE_PROTECT = 19;
    public static final long FTR_ERROR_MESSAGE_EXCEEDS_MAX_SIZE = 4336;
    //
    private static final long FTR_ANSISDK_ERROR_BASE = 0x30000000;
    public static final long FTR_ANSISDK_ERROR_IMAGE_SIZE_NOT_SUP = FTR_ANSISDK_ERROR_CODE(1);
    public static final long FTR_ANSISDK_ERROR_EXTRACTION_UNSPEC = FTR_ANSISDK_ERROR_CODE(2);
    public static final long FTR_ANSISDK_ERROR_EXTRACTION_BAD_IMP = FTR_ANSISDK_ERROR_CODE(3);
    public static final long FTR_ANSISDK_ERROR_MATCH_NULL = FTR_ANSISDK_ERROR_CODE(4);
    public static final long FTR_ANSISDK_ERROR_MATCH_PARSE_PROBE = FTR_ANSISDK_ERROR_CODE(5);
    public static final long FTR_ANSISDK_ERROR_MATCH_PARSE_GALLERY = FTR_ANSISDK_ERROR_CODE(6);
    public static final long FTR_ANSISDK_ERROR_MORE_DATA = FTR_ANSISDK_ERROR_CODE(7);

    private static long FTR_ERROR_CODE(long x) {
        return FTR_ERROR_BASE | x;
    }

    private static long FTR_ANSISDK_ERROR_CODE(long x) {
        return FTR_ANSISDK_ERROR_BASE | x;
    }

    public static final String getErrorDescription(long error) {
        String stError;
        if (error == FTR_ERROR_NO_ERROR) {
            stError = "OK";
        } else if (error == FTR_ERROR_EMPTY_FRAME) {
            stError = "Sem dedo para capturar";
        } else if (error == FTR_ERROR_MOVABLE_FINGER) {
            stError = "Dedo em movimento";
        } else if (error == FTR_ERROR_MOVABLE_FINGER) {
            stError = "Sem imagem";
        } else if (error == FTR_ERROR_USER_CANCELED) {
            stError = "- User canceled -";
        } else if (error == FTR_ERROR_HARDWARE_INCOMPATIBLE) {
            stError = "- Incompatible hardware -";
        } else if (error == FTR_ERROR_FIRMWARE_INCOMPATIBLE) {
            stError = "- Incompatible firmware -";
        } else if (error == FTR_ERROR_INVALID_AUTHORIZATION_CODE) {
            stError = "- Invalid authorization code -";
        } else if (error == FTR_ANSISDK_ERROR_IMAGE_SIZE_NOT_SUP) {
            stError = "- Image size is not supported -";
        } else if (error == FTR_ANSISDK_ERROR_EXTRACTION_UNSPEC) {
            stError = "- Unspecified extraction error -";
        } else if (error == FTR_ANSISDK_ERROR_EXTRACTION_BAD_IMP) {
            stError = "- Incorrect impression type -";
        } else if (error == FTR_ANSISDK_ERROR_MATCH_NULL || error == FTR_ANSISDK_ERROR_MATCH_PARSE_PROBE || error == FTR_ANSISDK_ERROR_MATCH_PARSE_GALLERY) {
            stError = "- Incorrect parameter -";
        } else {
            stError = "Erro desconhecido " + error;
        }
        return stError;
    }
}
