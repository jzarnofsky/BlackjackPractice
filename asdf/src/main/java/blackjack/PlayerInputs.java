package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ConcurrentModificationException;

public class PlayerInputs {

    public static void main(String[] asdf) {
        int n = 1300;
        for (int i = 0; i < 25; i++) {
            n *= 1.02;
        }
        System.out.println(n*2);
        System.out.println((n*2)/13);
    }
    //numerical amount for bets
    //Action choice for cards

    public static int takePlayerBet(Player bettingPlayer) {
        System.out.println(bettingPlayer.name + " should input a number for a bet now. Don't go broke.");
        int desiredBet = ensureInputIsNumber(getInputFromTerminal());
        while (checkAgainstMinimumBet(desiredBet) || checkAgainstPlayerFunds(desiredBet, bettingPlayer)) {
            desiredBet = ensureInputIsNumber(getInputFromTerminal());
            //TODO: Make it so player can't try forever
        }
        System.out.println(bettingPlayer.name + " has chosen to bet: " + desiredBet);
        return desiredBet;
    }

    public static GameActions.PlayerAction takePlayerAction() {
        System.out.println("To Hit, type 'hit'. To Stand, type 'stand'.\nIF you can double down, you may also type 'double'.");
        while (true) {
            switch (getInputFromTerminal().toLowerCase()) {
                case "hit":
                    return GameActions.PlayerAction.HIT;
                case "stand":
                    return GameActions.PlayerAction.STAND;
                case "double":
                    return GameActions.PlayerAction.DOUBLE_DOWN;
                default:
                    System.out.println("You did something stupid, enter what I asked for");
                    break;
            }
        }
    }

    public static GameActions.GameAction takeGameResolution() {
        System.out.println("Would you like to keep playing? Type Yes or No");
        while (true) {
            switch (getInputFromTerminal().toLowerCase()) {
                case "yes":
                    return GameActions.GameAction.RESTART_GAME;
                case "no":
                    return GameActions.GameAction.END_GAME;
                default:
                    System.out.println("You did something stupid, enter what I asked for");
                    break;
            }
        }
    }

    public static int getPlayerReBuyIn(Player player, int minimumBet) {
        System.out.println("How much would " + player.name + " like to buy back in with? If they're out, leave blank.");
        while (true) {
            String playerInput = getInputFromTerminal();
            try {
                Integer.parseInt(playerInput);
            } catch (NumberFormatException e) {
                System.out.println("Too big bitch. Try again? The table won't accept more than " + BlackJack.TABLE_MAX + " anyway.");
                continue;
            }
            if (playerInput.equals("")) {
                return 0;
            }
            try {
                int reBuyInAmount = Integer.parseInt(playerInput);
                if (reBuyInAmount + player.getFunds() > minimumBet) {
                    System.out.println("Making good choices, are we?");
                    return reBuyInAmount;
                } else {
                    System.out.println("You need to have more than the minimum bet after buying in. Try again.");
                }
            } catch (NumberFormatException | ConcurrentModificationException e) {
                System.out.println("That wasn't a number? I guess you're out, bruh.");
                return 0;
            }
        }
    }

    private static boolean checkAgainstMinimumBet(int desiredBet) {
        if (desiredBet < BlackJack.MINIMUM_BET) {
            System.out.println("The table has a minimum bet of " + BlackJack.MINIMUM_BET + ". Do try and bet more next time");
            return true;
        }
        return false;
    }

    private static boolean checkAgainstPlayerFunds(int desiredBet, Player bettingPlayer) {
        if (desiredBet > bettingPlayer.getFunds()) {
            System.out.println("Did you graduate preschool? You can't bet more than you have. Try again");
            return true;
        }
        return false;
    }

    private static String getInputFromTerminal() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Could not find what you're looking for. Whoops?");
            return "";
        }
    }

    private static int ensureInputIsNumber(String inputFromTerminal) {
        try {
            return Integer.parseInt(inputFromTerminal);
        } catch (NumberFormatException e) {
            System.out.println("Hey dumbass. It should be a number. If you fuck this up, you're betting nothing.");
            try {
                return Integer.parseInt(inputFromTerminal);
            } catch (NumberFormatException e2) {
                return 0;
            }
        }
    }
}
