class GameModel {

    val deck = Deck()

    val wastePile: MutableList<Card> = mutableListOf()

    val foundationPiles = arrayOf(Foundation(clubs), Foundation(diamonds),
            Foundation(hearts), Foundation(spades))

    val tableauPiles = Array(7, { Tableau()})

    fun resetGame() {
        wastePile.clear()
        foundationPiles.forEach { it.reset() }
        deck.reset()

        tableauPiles.forEachIndexed { i, tableau ->
            val cardsInPile: MutableList<Card> = Array(i + 1, { deck.drawCard()}).toMutableList()
            tableauPiles[i] = Tableau(cardsInPile)
        }
    }

    fun onDeckTap() {
        if (deck.cardsDeck.size > 0) {
            val card = deck.drawCard()
            card.isFaceUp = true
            wastePile.add(card)
        } else {
            deck.cardsDeck = wastePile.toMutableList()
            wastePile.clear()
        }
    }

    fun onWasteTap() {
        if (wastePile.size > 0) {
            val card = wastePile.last()
            if (playCard(card)) {
                wastePile.remove(card)
            }
        }
    }

    fun onFoundationTap(foundationIndex: Int) {
        val foundationPile = foundationPiles[foundationIndex]
        if (foundationPile.cards.size > 0) {
            val card = foundationPile.cards.last()
            if (playCard(card)) {
                foundationPile.removeCard(card)
            }
        }
    }

    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        val tableauPile = tableauPiles[tableauIndex]
        if (tableauPile.cards.size > 0) {
            val cards = tableauPile.cards.subList(cardIndex, tableauPile.cards.lastIndex + 1)
            if(playCards(cards)) {
                tableauPile.removeCards(cardIndex)
            }
        }
    }

    private fun  playCards(cards: MutableList<Card>): Boolean {
        if (cards.size == 1) {
            return playCard(cards.first())
        } else {
            tableauPiles.forEach {
                if (it.addCards(cards)) {
                    return true
                }
            }
        }
        return false
    }

    private fun  playCard(card: Card): Boolean {
        foundationPiles.forEach {
            if (it.addCard(card)) {
                return true
            }
        }
        tableauPiles.forEach {
            if (it.addCards(mutableListOf(card))) {
                return true
            }
        }
        return false
    }
}
