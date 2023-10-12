import blackjack.GameActions;
import blackjack.PlayerInputs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

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
        InputStream yesFromTerminal = new ByteArrayInputStream("yes".getBytes());
        System.setIn(yesFromTerminal);
        assertEquals(PlayerInputs.takeGameResolution(), GameActions.GameAction.RESTART_GAME);

        InputStream noFromTerminal = new ByteArrayInputStream("no".getBytes());
        System.setIn(noFromTerminal);
        assertEquals(PlayerInputs.takeGameResolution(), GameActions.GameAction.END_GAME);
    }

    @Test
    public void testEnsureInputIsANumber() {
        assertEquals(123, ensureInputIsNumber("123"));
        assertEquals(-123, ensureInputIsNumber("-123"));

        System.setIn(new ByteArrayInputStream("no".getBytes()));
        assertEquals(0,
                ensureInputIsNumber("If you write Banana, it's not a number. Banana does not work."));

        System.setIn(new ByteArrayInputStream("1234".getBytes()));
        assertEquals(1234,
                ensureInputIsNumber("You. Are. FIRED."));
    }
}
