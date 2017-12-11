import love.sola.nfc.api.getCard
import org.jetbrains.kotlin.daemon.common.toHexString

/**
 * @author Sola
 */
var card = getCard()!!
println("uid = ${card.getUID().toHexString()}")