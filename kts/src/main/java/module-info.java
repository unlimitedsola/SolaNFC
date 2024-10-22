module love.sola.nfc.kts {
    exports love.sola.nfc.kts;

    requires kotlin.stdlib;
    requires kotlin.scripting.common;
    requires kotlin.scripting.jvm;
    requires kotlin.scripting.jvm.host;

    // ReflectionUtil in kotlin-script-embeddable uses Unsafe
    requires jdk.unsupported;
}
