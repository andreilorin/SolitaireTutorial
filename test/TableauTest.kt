import org.junit.Assert
import org.junit.Test

class TableauTest {
    @Test
    fun addCards() {

        val tableauPile = Tableau(mutableListOf(Card(12, spades)))
        val cards = mutableListOf(Card(11, hearts))

        tableauPile.addCards(cards)

        Assert.assertEquals(2, tableauPile.cards.size)
    }

    @org.junit.Test
    fun removeCards() {
        val tableauPile = Tableau(mutableListOf(Card(4, clubs), Card(3, diamonds), Card(4, spades)))

        tableauPile.removeCards(1)

        Assert.assertEquals(mutableListOf(Card(4, clubs, true)), tableauPile.cards)
    }
}
