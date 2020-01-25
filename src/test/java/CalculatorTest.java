import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

class CalculatorTest {
    private static final int SAMPLE_ADDITION_OUTPUT = 0;
    private static final int SAMPLE_MULTIPLICATION_OUTPUT = 0;
    private static final String SAMPLE_EXCEPTION_MESSAGE = "Sample Exception Message";

    private AdditionService additionService;
    private MultiplicationService multiplicationService;
    private SubtractionService subtractionService;

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        this.additionService = mock(AdditionService.class);
        this.multiplicationService = mock(MultiplicationService.class);
        this.subtractionService = mock(SubtractionService.class);
        this.calculator = new Calculator(additionService, multiplicationService, subtractionService);
    }

    @Test
    public void givenAnInput_whenCalculatorCalculates_thenWeExpectAnOutput() {
        // Given (Setup)
        int expected = 140;
        int x = 10;
        when(additionService.add(anyInt(), anyInt())).thenReturn(SAMPLE_ADDITION_OUTPUT);
        when(multiplicationService.multiply(anyInt(), anyInt())).thenReturn(SAMPLE_MULTIPLICATION_OUTPUT);
        when(subtractionService.subtract(anyInt(), anyInt())).thenReturn(expected);

        // When (Run the thing that you want to test)
        int y = calculator.calculateY(x);

        // Then (Asserting what you want to be true, is actually true)
        assertEquals(expected, y);

        // Verify
        verify(additionService, times(1)).add(anyInt(), anyInt());
        verify(multiplicationService, times(1)).multiply(anyInt(), anyInt());
        verify(subtractionService, times(1)).subtract(anyInt(), anyInt());
    }

    @Test
    public void givenAnInput_whenMultiplicationServiceThrowsAnException_thenCalculatorBubblesThatExceptionUp() {
        // Given (Setup)
        int x = 10;
        when(additionService.add(anyInt(), anyInt())).thenReturn(SAMPLE_ADDITION_OUTPUT);
        when(multiplicationService.multiply(anyInt(), anyInt())).thenThrow(new RuntimeException(SAMPLE_EXCEPTION_MESSAGE));

        try {
            // When (Run the thing that you want to test)
            calculator.calculateY(x);
            fail("Should have failed");
        } catch (Exception e) {
            // Then (Asserting what you want to be true, is actually true)
            assertEquals(SAMPLE_EXCEPTION_MESSAGE, e.getMessage());
        }

        // Verify
        verify(additionService, times(1)).add(anyInt(), anyInt());
        verify(multiplicationService, times(1)).multiply(anyInt(), anyInt());
        verifyZeroInteractions(subtractionService);
    }

    @Test
    public void givenANegativeInput_whenCalculatorCalculates_thenWeExpectNegativeEquationResults() {
        // Given (Setup)
        int expected = 10;
        int x = -5;
        when(subtractionService.subtract(anyInt(), anyInt())).thenReturn(expected);

        // When (Run the thing that you want to test)
        int y = calculator.calculateY(x);

        // Then (Asserting what you want to be true, is actually true)
        assertEquals(expected, y);

        // Verify
        verifyZeroInteractions(additionService);
        verifyZeroInteractions(multiplicationService);
        verify(subtractionService, times(1)).subtract(anyInt(), anyInt());
    }

}