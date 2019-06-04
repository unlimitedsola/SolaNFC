import love.sola.nfc.api.getCard
import love.sola.nfc.api.mifare.classic.functions.unlockedDump
import love.sola.nfc.util.toHexString

/**
 * @author Sola
 */
val card = getCard()!!
println("uid = ${card.getUID().toHexString()}")
card.unlock()
val dump = card.unlockedDump()
println("dump = $dump")
