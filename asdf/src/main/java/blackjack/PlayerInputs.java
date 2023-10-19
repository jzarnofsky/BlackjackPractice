package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerInputs {

    public static int takePlayerBet(Player bettingPlayer) {
        System.out.println(bettingPlayer.name + " should input a number for a bet now. Don't go broke.");
        int desiredBet = ensureInputIsNumber(getInputFromTerminal());
        int attempts = 0;
        while (checkAgainstMinimumBet(desiredBet)
                || checkAgainstPlayerFunds(desiredBet, bettingPlayer)) {
            if (attempts > 2) {
                System.out.println("I guess you're betting everything since you can't follow basic instructions.");
                return bettingPlayer.getFunds();
            }
            desiredBet = ensureInputIsNumber(getInputFromTerminal());
            attempts++;
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

    public static int getPlayerReBuyIn(Player player) {
        System.out.println("How much would " + player.name + " like to buy back in with? If you don't type a number, you're out.");
        while (true) {
            int playerInput = ensureInputIsNumber(getInputFromTerminal());
            if (playerInput == 0) {
                return playerInput;
            }
            if (playerInput + player.getFunds() > BlackJack.MINIMUM_BET) {
                System.out.println("Making good choices, are we?");
                return playerInput;
            } else {
                getPlayerReBuyInElseLoopOccurred = true;
                System.out.println("You need to have more than the minimum bet after buying in. Try again.");
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

    protected static int ensureInputIsNumber(String inputFromTerminal) {
        try {
            return Integer.parseInt(inputFromTerminal);
        } catch (NumberFormatException e) {
            try {
                System.out.println("Hey dumbass. It should be a number. Try again.");
                return Integer.parseInt(getInputFromTerminal());
            } catch (NumberFormatException ee) {
                System.out.println("I'm just gonna say you said \"0\" instead.");
                return 0;
            }
        }
    }

    static boolean getPlayerReBuyInElseLoopOccurred = false;
    protected static boolean didGetPlayerReBuyInElseLoopOccur() {
        return getPlayerReBuyInElseLoopOccurred;
    }
}
