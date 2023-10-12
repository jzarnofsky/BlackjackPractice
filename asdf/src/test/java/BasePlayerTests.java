import blackjack.BasePlayer;
import blackjack.Deck;
import blackjack.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasePlayerTests {

    @Test
    public void testGetHandValueNoAces() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.TWO));
        assertEquals("Is this test passing?", 7, player.getHandValue());
    }

    @Test
    public void testGetHandValueOneAceBelowTwentyOne() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.ACE));
        assertEquals("Is this test passing?", 16, player.getHandValue());
    }

    @Test
    public void testGetHandValueOneAcesAboveTwentyOne() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.KING));
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.ACE));
        assertEquals("Is this test passing?", 16, player.getHandValue());
    }

    @Test
    public void testGetHandValueTwoAcesBelowTwentyOne() {
        BasePlayer player = new Player(100);
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.FIVE));
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.ACE));
        player.addCardToHand(new Deck.Card(Deck.Suit.CLUBS, Deck.Face.ACE));
        assertEquals("Is this test passing?", 17, player.getHandValue());
    }
}
