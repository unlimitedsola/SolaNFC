module love.sola.nfc {
    exports love.sola.nfc;

    exports love.sola.nfc.api.mifare.classic;
    exports love.sola.nfc.api.mifare.classic.constants;
    exports love.sola.nfc.api.mifare.classic.data;
    exports love.sola.nfc.api.mifare.classic.functions;

    exports love.sola.nfc.kts;

    requires kotlin.stdlib;
    requires java.smartcardio;
    requires java.scripting;
    requires kotlin.scripting.common;
    requires kotlin.scripting.jvm;
    requires kotlin.scripting.jvm.host;
}
