import blackjack.GameActions;
import blackjack.Player;
import blackjack.PlayerInputs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class PlayerInputTests extends PlayerInputs {

     InputStream originalSystemIn;

    @Before
    public void runBeforeTest() {
        if (originalSystemIn == null) {
            originalSystemIn = System.in;
        }
    }

    @After
    public void runAfterTest() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testTakeGameResolution() {
        prepTerminal("yes");
        assertEquals(PlayerInputs.takeGameResolution(), GameActions.GameAction.RESTART_GAME);

        prepTerminal("no");
        assertEquals(PlayerInputs.takeGameResolution(), GameActions.GameAction.END_GAME);

        prepTerminal("asdfasdfasdf");
        try {
            PlayerInputs.takeGameResolution();
            fail();
        } catch (NullPointerException e) {
            //test Pass scenario
        }
    }

    @Test
    public void testEnsureInputIsANumber() {
        assertEquals(123, ensureInputIsNumber("123"));
        assertEquals(-123, ensureInputIsNumber("-123"));

        prepTerminal("no");
        assertEquals(0,
                ensureInputIsNumber("If you write Banana, it's not a number. Banana does not work."));

        prepTerminal("1234");
        assertEquals(1234,
                ensureInputIsNumber("You. Are. FIRED."));
    }

    @Test
    public void testGetPlayerReBuyIn() {
        Player testPlayer = new Player(0);

        prepTerminal("1");
        getPlayerReBuyIn(testPlayer);
        assertTrue(didGetPlayerReBuyInElseLoopOccur());

        prepTerminal("0");
        assertEquals(0, getPlayerReBuyIn(testPlayer));

        prepTerminal("40");
        assertEquals(40, getPlayerReBuyIn(testPlayer));
    }

    @Test
    public void testTakePlayerAction() {
        prepTerminal("HIT");
        assertEquals(GameActions.PlayerAction.HIT, PlayerInputs.takePlayerAction());

        prepTerminal("stand");
        assertEquals(GameActions.PlayerAction.STAND, PlayerInputs.takePlayerAction());

        prepTerminal("DOUBLE");
        assertEquals(GameActions.PlayerAction.DOUBLE_DOWN, PlayerInputs.takePlayerAction());

        prepTerminal("asdfasdfasdf");
        try {
            PlayerInputs.takePlayerAction();
            fail();
        } catch (NullPointerException e) {
            //test Pass scenario
        }
    }

    @Test
    public void testTakePlayerBet() {
        prepTerminal("0");
        assertEquals(100, takePlayerBet(new Player(100)));

        prepTerminal("1000");
        assertEquals(100, takePlayerBet(new Player(100)));

        prepTerminal("50");
        assertEquals(50, takePlayerBet(new Player(100)));
    }

    private void prepTerminal(String terminalEntry) {
        InputStream noFromTerminal = new ByteArrayInputStream(terminalEntry.getBytes());
        System.setIn(noFromTerminal);
    }
}
