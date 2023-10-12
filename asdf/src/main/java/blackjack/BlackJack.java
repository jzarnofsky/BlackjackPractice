package blackjack;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {

    public static final int MINIMUM_BET = 20;
    public static final int TABLE_MAX = 20000;

    public static void main(String[] args) {
        Player one = new Player(100);
        one.introduceThemselves("Aye Den");

        Player two = new Player(300);
        two.introduceThemselves("Kah Een");

        List<Player> gameParticipants = new ArrayList<>();
        gameParticipants.add(one);
//        gameParticipants.add(two);

        boolean replayGame = true;

        while (replayGame) {
            GameActions.initialDeal(gameParticipants);

            for (Player player : gameParticipants) {
                boolean playerHasntBet = true;
                while (!player.isBusted() && !player.playerHasEndedTheirTurn()) {
                    player.showHand();
                    System.out.println(player.name + " has " + player.getFunds());
                    if (playerHasntBet) {
                        GameActions.bet(player, PlayerInputs.takePlayerBet(player));
                        playerHasntBet = false;
                    }
                    switch (PlayerInputs.takePlayerAction()) {
                        case HIT -> GameActions.hit(player);
                        case STAND -> GameActions.stand(player);
                        case DOUBLE_DOWN -> {
                            if (player.countCardsInHand() == 2 && player.getFunds() >= GameActions.dealer.getPlayerBet(player)) {
                                GameActions.doubleDown(player);
                            } else {
                                System.out.println("Number of cards in hand is " + player.countCardsInHand());
                                System.out.println("Player funds is " + player.getFunds());
                                System.out.println("Dealer shows this as the bet so far: " + GameActions.dealer.getPlayerBet(player));

                                System.out.println("You can't double down. Either you've already hit or you don't have enough to bet");
                            }
                        }
                        //ToDo split
                    }
                }
            }

            GameActions.dealersLogic();

            for (Player player : gameParticipants) {
                if (!player.isBusted()) {
                    if (GameActions.dealer.isBusted()) {
                        GameActions.playerWon(player);
                    } else {
                        if (player.getHandValue() > GameActions.dealer.getHandValue()) {
                            GameActions.playerWon(player);
                        } else if (player.getHandValue() == GameActions.dealer.getHandValue()) {
                            GameActions.playerTied(player);
                        } else {
                            System.out.println(player.name + " fucking lost. It's time to take out a loan.");
                        }
                    }
                }
            }

            switch (PlayerInputs.takeGameResolution()) {
                case END_GAME:
                    replayGame = false;
                    System.out.println("Get fucked.");
                    break;
                case RESTART_GAME:
                    for (Player player: gameParticipants) {
                        if (!GameActions.checkIfPlayerCanContinue(player, MINIMUM_BET)) {
                            System.out.println(player.name + " doesn't have enough to keep playing.");
                            int reBuyInAmount = PlayerInputs.getPlayerReBuyIn(player);
                            if (reBuyInAmount < MINIMUM_BET) {
                                gameParticipants.remove(player);
                            } else if (reBuyInAmount > TABLE_MAX) {
                                System.out.println(player.name + ", you can't come to the table with more than " + TABLE_MAX + ".");
                                System.out.println("So that's how much you have now!");
                                player.addFundsToPlayer(TABLE_MAX);
                            } else {
                                player.addFundsToPlayer(reBuyInAmount);
                            }
                        }
                    }
                    GameActions.resetPlayerValues(gameParticipants);
                    break;
                default:
                    //can remain empty for now
            }
        }
    }
}
