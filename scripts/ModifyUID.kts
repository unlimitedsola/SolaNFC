import love.sola.nfc.api.getCard
import love.sola.nfc.api.mifare.classic.data.Block
import love.sola.nfc.api.waitDisconnect
import love.sola.nfc.util.toHexString

val uid = getUIDFromUserInput()
println("Place the card now.")
var card = getCard()!!
println("Card detected, origin UID:${card.getUID().toHexString()}")
println("Modifying UID block...")
println("(if you get stuck here, please remove the card from your card reader.)")
card.unlock()
card.writeBlock(0, uid)
waitDisconnect()
println("Done. Please re-place the card.")
card = getCard()!!
print("Verifying UID...")
if (card.getUID().contentEquals(uid.data().copyOf(4))) {
    println("Success.")
} else {
    println("Failed. the card's UID:${card.getUID().toHexString()} is not what expected.")
}

fun getUIDFromUserInput(): Block {
    var uid: Block?
    while (true) {
        println("Type full UID block in hex (e.g. 3e0aa820bc080400018d56978c86d51d)")
        print("UID Block:")
        uid = Block(readLine()!!.replace(" ", ""))
        if (!uid.isValidUID) {
            println("uid = $uid is not a valid UID!")
        } else {
            println("uid = $uid")
            return uid
        }
    }
}
