package calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

public class CalculatorTests {

    Calculate calculator = new Calculate();

    @Test
    public void testPerformOperationsAddition() {
        assertEquals(3.0,
                calculator.performOperation("+", 1, 2), 0.0);
    }

    @Test
    public void testPerformOperationsSubtract() {
        assertEquals(-1.0,
                calculator.performOperation("-", 1, 2), 0.0);
    }

    @Test
    public void testPerformOperationsMultiply() {
        assertEquals(2.0,
                calculator.performOperation("*", 1, 2), 0.0);
    }

    @Test
    public void testPerformOperationsDivide() {
        assertEquals(.5, calculator.performOperation("/", 1, 2), 0.0);
    }

    @Test
    public void testPerformOperationsRoot() {
        assertEquals(2, calculator.performOperation("rt", 256, 3), 0.0);
    }

    @Test
    public void testPerformOperationsExponent() {
        calculator.performOperation("^", 2, 2);
        assertEquals(4, calculator.performOperation("^", 2, 2), 0.0);
    }

    @Test
    public void testPerformOperationsDefault() {
        try {
            calculator.performOperation("asf", 200, 222222);
            fail();
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testParseEquation() {
        calculator.parseEquation("1 ^ 3");
        assertEquals(2, calculator.numbers.size());
        assertEquals(1, calculator.operators.size());
        assertEquals(1, calculator.numbers.get(0), 0.0);
        assertEquals(3, calculator.numbers.get(1), 0.0);
        assertEquals("^", calculator.operators.get(0));

        calculator.parseEquation("2 + 7 + 2");
        assertEquals(3, calculator.numbers.size());
        assertEquals(2, calculator.operators.size());
    }

}
