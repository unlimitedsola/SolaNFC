import love.sola.nfc.api.getCard
import love.sola.nfc.api.mifare.classic.functions.isBlank
import love.sola.nfc.api.waitDisconnect
import love.sola.nfc.cli.progress.BooleanConsoleProgressHandler
import love.sola.nfc.util.toHexString

while (true) {
    try {
        val card = getCard()!!
        println("uid = ${card.getUID().toHexString()}")
        println("type = ${card.type}")
        println("isBlank = ${card.isBlank(BooleanConsoleProgressHandler("Checking"))}")
    } catch (e: Exception) {
        println("error: ${e.message}")
    }
    waitDisconnect()
}
