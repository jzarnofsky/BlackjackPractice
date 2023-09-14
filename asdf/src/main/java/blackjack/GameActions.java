package blackjack;

import java.util.ArrayList;
import java.util.List;

public class GameActions {

    static Dealer dealer = new Dealer(new Deck());

    private GameActions() { }

    public static void initialDeal(List<Player> gameParticipants) {
        System.out.println("\n --==A NEW ROUND BEGINS==-- \n");
        for (Player player: gameParticipants) {
            dealer.dealCard(player);
            dealer.dealCard(player);
            System.out.println(player.name + "'s starting hand is: " + player.getHandValue());
        }
        dealer.dealCard(dealer);
        dealer.reserveCard();
    }

    public static void initialBet(List<Player> gameParticipants, List<Integer> playerBets) {
        for (int i = 0; i < gameParticipants.size(); i++) {
            bet(gameParticipants.get(i), playerBets.get(i));
        }
    }

    public static void dealersLogic() {
        dealer.revealCard();
        while (dealer.getHandValue() < 17) {
            hit(dealer);
        }
        if (!dealer.isBusted()) {
            stand(dealer);
        }
    }

    public static void hit(BasePlayer hittingPlayer) {
        dealer.dealCard(hittingPlayer);
        System.out.println(hittingPlayer.name + "'s new hand value is: " + hittingPlayer.getHandValue());
        if (hittingPlayer.getHandValue() > 21) {
            hittingPlayer.getBusted();
        }
    }

    public static void stand(BasePlayer player) {
        player.endPlayerTurn();
    }

    public static void doubleDown(Player player) {
        bet(player, dealer.getPlayerBet(player));
        hit(player);
        player.endPlayerTurn();
    }

    public static void split(Player splittingPlayer) {
        //hand1, hand2
        //player makes a bet for second hand
        //Dealer needs to deal to player's new hands
        //Hit needs to know which hand to hit to
        //Hands will bust independently
    }

    public static void bet(Player bettingPlayer, int betAmount) {
        bettingPlayer.placeBet(betAmount);
        System.out.println(bettingPlayer.name + " bet: " + betAmount);
        try {
            dealer.collectBets(bettingPlayer, dealer.getPlayerBet(bettingPlayer) + betAmount);
        } catch (NullPointerException e) {
            dealer.collectBets(bettingPlayer, betAmount);
        }
    }

    private static int payWinner(Player winningPlayer) {
        int amountToBePaid = dealer.getPlayerBet(winningPlayer) * 2;
        winningPlayer.addFundsToPlayer(amountToBePaid);
        return amountToBePaid;
    }

    private static void refundPlayerBecauseOfTie(Player tiedPlayer) {
        int amountToBePaid = dealer.getPlayerBet(tiedPlayer);
        tiedPlayer.addFundsToPlayer(amountToBePaid);
    }

    public static void playerWon(Player winningPlayer) {
        int amountToBePaid = payWinner(winningPlayer);
        System.out.println(winningPlayer.name + " beat the dealer's hand and has been paid " + amountToBePaid);
    }

    public static void playerTied(Player tiedPlayer) {
        refundPlayerBecauseOfTie(tiedPlayer);
        System.out.println(tiedPlayer.name + " tied the dealer. Their funds will be returned! Unless you're at Aeden's table...");
    }

    public static void resetPlayerValues(List<Player> players) {
        List<BasePlayer> allPlayers = new ArrayList<>(players);
        allPlayers.add(dealer);
        for (BasePlayer basePlayer: allPlayers) {
            resetPlayerVariables(basePlayer);
        }
        dealer.clearPlayerBets();
    }

    private static void resetPlayerVariables(BasePlayer player) {
        player.unBust();
        player.resetPlayerTurn();
        player.resetPlayerHand();
    }

    public static boolean checkIfPlayerCanContinue(Player player, int minimumBet) {
        return player.getFunds() >= minimumBet;
    }

    public enum PlayerAction {
        HIT, STAND, DOUBLE_DOWN
    }

    public enum GameAction {
        RESTART_GAME, END_GAME
    }
}
