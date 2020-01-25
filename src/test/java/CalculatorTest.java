import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void givenInput10_whenCalculatorCalculates_thenWeExpectOutput140() {
        // Given (Setup)
        int expected = 140;
        int x = 10;

        // When (Run the thing that you want to test)
        int y = calculator.calculateY(x);

        // Then (Asserting what you want to be true, is actually true)
        assertEquals(expected, y);
    }

    @Test
    public void givenInput5_whenCalculatorCalculates_thenWeExpectOutput45() {
        // Given (Setup)
        int expected = 45;
        int x = 5;

        // When (Run the thing that you want to test)
        int y = calculator.calculateY(x);

        // Then (Asserting what you want to be true, is actually true)
        assertEquals(expected, y);
    }

    @Test
    public void givenInputNegative10_whenCalculatorCalculates_thenWeExpectOutput60() {
        // Given (Setup)
        int expected = 60;
        int x = -10;

        // When (Run the thing that you want to test)
        int y = calculator.calculateY(x);

        // Then (Asserting what you want to be true, is actually true)
        assertEquals(expected, y);
    }



}