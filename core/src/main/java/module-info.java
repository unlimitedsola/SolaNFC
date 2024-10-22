module love.sola.nfc {
    exports love.sola.nfc;

    exports love.sola.nfc.api.mifare.classic;
    exports love.sola.nfc.api.mifare.classic.constants;
    exports love.sola.nfc.api.mifare.classic.data;
    exports love.sola.nfc.api.mifare.classic.functions;

    requires love.sola.nfc.kts;
    requires kotlin.scripting.common;

    requires kotlin.stdlib;
    requires java.smartcardio;
}
