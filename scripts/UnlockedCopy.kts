import love.sola.nfc.api.getCard
import love.sola.nfc.api.mifare.classic.functions.unlockedDump
import love.sola.nfc.api.mifare.classic.functions.unlockedFormat
import love.sola.nfc.api.waitDisconnect
import love.sola.nfc.util.toHexString

/**
 * @author Sola
 */
var card = getCard()!!
println("uid = ${card.getUID().toHexString()}")
card.unlock()
val dump = card.unlockedDump()
println("dump = $dump")
waitDisconnect()

println("insert target")
card = getCard()!!
card.unlock()
card.unlockedFormat(dump)
println("disconnect target")
waitDisconnect()
