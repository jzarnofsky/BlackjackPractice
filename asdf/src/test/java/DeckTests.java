import blackjack.Deck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTests {

    @Test
    public void testCardValue() {
        Deck.Card ace = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.ACE);
        Deck.Card two = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.TWO);
        Deck.Card three = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.THREE);
        Deck.Card four = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.FOUR);
        Deck.Card five = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.FIVE);
        Deck.Card six = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.SIX);
        Deck.Card seven = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.SEVEN);
        Deck.Card eight = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.EIGHT);
        Deck.Card nine = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.NINE);
        Deck.Card ten = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.TEN);
        Deck.Card jack = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.JACK);
        Deck.Card king = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.KING);
        Deck.Card queen = new Deck.Card(Deck.Suit.CLUBS, Deck.Face.QUEEN);

        assertEquals("Is the card value correct?", 1, ace.getCardValue());
        assertEquals("Is the card value correct?", 2, two.getCardValue());
        assertEquals("Is the card value correct?", 3, three.getCardValue());
        assertEquals("Is the card value correct?", 4, four.getCardValue());
        assertEquals("Is the card value correct?", 5, five.getCardValue());
        assertEquals("Is the card value correct?", 6, six.getCardValue());
        assertEquals("Is the card value correct?", 7, seven.getCardValue());
        assertEquals("Is the card value correct?", 8, eight.getCardValue());
        assertEquals("Is the card value correct?", 9, nine.getCardValue());
        assertEquals("Is the card value correct?", 10, ten.getCardValue());
        assertEquals("Is the card value correct?", 10, jack.getCardValue());
        assertEquals("Is the card value correct?", 10, king.getCardValue());
        assertEquals("Is the card value correct?", 10, queen.getCardValue());
    }
}
