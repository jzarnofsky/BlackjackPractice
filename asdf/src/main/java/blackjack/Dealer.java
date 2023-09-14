package blackjack;

import java.util.HashMap;
import java.util.Map;

public class Dealer extends BasePlayer {

    private final Deck dealerDeck;
    private Deck.Card hiddenCard;
    private final Map<Player, Integer> playerBetMap = new HashMap<>();

    public Dealer(Deck dealerDeck) {
        name = "Dealer";
        this.dealerDeck = dealerDeck;
    }

    public void clearPlayerBets() {
        playerBetMap.clear();
    }

    public void dealCard(BasePlayer player) {
        Deck.Card dealtCard = tryToDealCard(player);
        System.out.println(player.name + " was dealt the card: " +
                dealtCard.face.toString() + " of " + dealtCard.suit.toString());
    }

    public void reserveCard() {
        hiddenCard = tryToDealCard(this);
        System.out.println(this.name + " sets a card aside");
    }

    public void revealCard() {
        System.out.println(this.name + "'s reserved card was " + hiddenCard.toString());
        System.out.println(this.name + "'s starting hand value is " + this.getHandValue());
    }

    private Deck.Card tryToDealCard(BasePlayer player) {
        Deck.Card dealtCard;
        try {
            dealtCard = dealerDeck.drawTopCard();
            player.addCardToHand(dealtCard);
        } catch (IndexOutOfBoundsException e) {
            reShuffleDeck();
            dealtCard = dealerDeck.drawTopCard();
            player.addCardToHand(dealtCard);
        }
        return dealtCard;
    }

    public void reShuffleDeck() {
        dealerDeck.shuffleFullDeck();
    }

    public void collectBets(Player bettingPlayer, int betAmount) {
        playerBetMap.put(bettingPlayer, betAmount);
    }

    public int getPlayerBet(Player winningPlayer) {
        return playerBetMap.get(winningPlayer);
    }
}
