import java.util.*

class Deck {

    val cards = Array(52, { Card(it % 13, getSuit(it)) })

    var cardsDeck: MutableList<Card> = cards.toMutableList()

    fun drawCard(): Card = cardsDeck.removeAt(0)

    fun reset() {
        cardsDeck = cards.toMutableList()
        Collections.shuffle(cardsDeck)
    }

    private fun getSuit(i: Int) = when (i / 13) {
        0 -> "clubs"
        1 -> "diamonds"
        2 -> "hearts"
        else -> "spades"
    }
}