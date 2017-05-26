class Tableau(var cards: MutableList<Card> = mutableListOf()) {
    init {
        if (cards.size > 0)
            cards.last().isFaceUp = true
    }

    fun addCards(newCards: MutableList<Card>): Boolean {
        if (cards.size > 0) {
            if (newCards.first().value == cards.last().value - 1 &&
                    suitCheck(newCards.first(), cards.last())) {
                cards.addAll(newCards)
                return true
            }
        } else if (newCards.first().value == 12) {
            cards.addAll(newCards)
            return true
        }
        return false
    }

    fun removeCards(tappedIndex: Int) {
        for (i in tappedIndex..cards.lastIndex) {
            cards.removeAt(i)
        }
        if (cards.size > 0) {
            cards.last().isFaceUp = true
        }
    }

    private fun suitCheck(colorFirst: Card, colorSecond: Card): Boolean {
        if ((redSuits.contains(colorFirst.suit) && blackSuits.contains(colorSecond.suit)) ||
                (blackSuits.contains(colorFirst.suit) && redSuits.contains(colorSecond.suit))) {
            return true
        }
        return false
    }
}