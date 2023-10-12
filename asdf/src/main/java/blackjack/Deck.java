package blackjack;

import java.util.*;

public class Deck {

    private final List<Card> listOfRemainingCards;

    public Deck() {
        listOfRemainingCards = createNewDeckList();
        shuffleRemainingCards();
    }

    public int remainingCardCount() {
        return listOfRemainingCards.size();
    }

    public Card drawTopCard() throws IndexOutOfBoundsException {
        Card returnThisCard = listOfRemainingCards.get(0);
        listOfRemainingCards.remove(0);
        return returnThisCard;
    }

    public void shuffleFullDeck() {
        listOfRemainingCards.clear();
        fillListOfCards(listOfRemainingCards);
        shuffleRemainingCards();
    }

    public void shuffleRemainingCards() {
        Collections.shuffle(listOfRemainingCards);
    }

    public Card drawRandomCard() throws IndexOutOfBoundsException {
        int randomNumber = new Random().nextInt(listOfRemainingCards.size() - 1);
        Card returnThisCard = listOfRemainingCards.get(randomNumber);
        listOfRemainingCards.remove(randomNumber);
        return returnThisCard;
    }

    private List<Card> createNewDeckList() {
        List<Card> cardList = new ArrayList<>();
        fillListOfCards(cardList);
        return cardList;
    }

    private void fillListOfCards(List<Card> cardList) {
        for (Suit cardSuit: Suit.values()) {
            for (Face cardFace: Face.values()) {
                Card card = new Card(cardSuit, cardFace);
                cardList.add(card);
            }
        }
    }

    public static class Card {
        Suit suit;
        Face face;

        @Override
        public String toString() {
            return face.toString() + " of " + suit.toString();
        }

        public Card(Suit suit, Face face) {
            this.suit = suit;
            this.face = face;
        }

        public int getCardValue() {
            return switch (face) {
                case TWO -> 2;
                case THREE -> 3;
                case FOUR -> 4;
                case FIVE -> 5;
                case SIX -> 6;
                case SEVEN -> 7;
                case EIGHT -> 8;
                case NINE -> 9;
                case TEN, KING, JACK, QUEEN -> 10;
                default -> 1; //Ace's value is determined in BasePlayer as it needs to know the current hand value
            };
        }
    }

    public enum Suit {
        HEARTS, CLUBS, SPADES, DIAMONDS
    }

    public enum Face {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING, ACE
    }
}
