package blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePlayer {

    private List<Deck.Card> currentHand = new ArrayList<>();
    private boolean busted;
    private boolean endedTurn;
    String name;
    
    private int handValue = 0;
    private int numberOfAces = 0;

    public void introduceThemselves(String name) {
        this.name = name;
    }

    public void addCardToHand(Deck.Card newCard) {
        currentHand.add(newCard);
    }

    public boolean isBusted() {
        return busted;
    }

    public void getBusted() {
        System.out.println(name + " has busted. Like a fucking Ponce.");
        busted = true;
    }

    public int countCardsInHand() {
        return currentHand.size();
    }

    public void unBust() {
        busted = false;
    }

    public void endPlayerTurn() {
        endedTurn = true;
    }

    public void resetPlayerTurn() {
        endedTurn = false;
    }

    public void resetPlayerHand() {
        currentHand.clear();
    }

    public boolean playerHasEndedTheirTurn() {
        return endedTurn;
    }

    public void showHand() {
        if (this.getClass() == Player.class) {
            System.out.println();
            System.out.println(name + " has the cards...");
            currentHand.forEach(System.out::println);
            System.out.println(name + "'s hand has a value of " + getHandValue());
        }
    }

    public int getHandValue() {
        handValue = 0;
        numberOfAces = 0;
        totalNonAcesAndCountAces();
        addAcesToHandValue();
        return handValue;
    }

    private void addAcesToHandValue() {
        for (int i = 0; i < numberOfAces; i++) {
            if (handValue + 10 + numberOfAces <= 21) {
                handValue += 10 + 1;
            } else {
                handValue++;
            }
        }
    }

    private void totalNonAcesAndCountAces() {
        for (Deck.Card card : currentHand) {
            if (card.face != Deck.Face.ACE) {
                handValue += card.getCardValue();
            } else {
                numberOfAces++;
            }
        }
    }
}
