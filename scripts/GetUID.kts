import love.sola.nfc.api.getCard
import love.sola.nfc.api.waitDisconnect
import org.jetbrains.kotlin.daemon.common.toHexString

/**
 * @author Sola
 */
while (true) {
    try {
        val card = getCard()!!
        println("uid = ${card.getUID().toHexString()}")
        println("type = ${card.type}")
    } catch (e: Exception) {
        println("error: ${e.message}")
    }
    waitDisconnect()
}
