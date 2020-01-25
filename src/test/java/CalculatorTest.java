import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

class CalculatorTest {
    private static final int SAMPLE_ADDITION_OUTPUT = 1;
    private static final int SAMPLE_MULTIPLICATION_OUTPUT = 2;
    private static final int SAMPLE_SUBTRACTION_OUTPUT = 3;
    private static final int SAMPLE_SUB_MULTI_OUTPUT = 4;
    private static final String SAMPLE_EXCEPTION_MESSAGE = "Sample Exception Message";

    private AdditionService additionService;
    private MultiplicationService multiplicationService;
    private SubtractionService subtractionService;
    private SubMultiService subMultiService;

    private Calculator calculator;

    private enum MockId {
        ADDITION_SERVICE,
        MULTIPLICATION_SERVICE,
        SUBTRACTION_SERVICE,
        SUB_MULTI_SERVICE,
        ALL
    }

    @BeforeEach
    void setUp() {
        this.additionService = mock(AdditionService.class);
        this.multiplicationService = mock(MultiplicationService.class);
        this.subtractionService = mock(SubtractionService.class);
        this.subMultiService = mock(SubMultiService.class);
        this.calculator = new Calculator(additionService, multiplicationService, subtractionService, subMultiService);
    }

    @Nested
    class CalculateY {

        @Test
        public void givenAnInput_whenCalculatorCalculates_thenWeExpectAnOutput() {
            // Given (Setup)
            int expected = SAMPLE_SUB_MULTI_OUTPUT;
            int x = 10;

            mockUpUntil(MockId.ALL);

            // When (Run the thing that you want to test)
            int y = calculator.calculateY(x);

            // Then (Asserting what you want to be true, is actually true)
            assertEquals(expected, y);

            // Verify
            verifyUpUntilAnd(MockId.ALL);
        }

        @Test
        public void givenAnInput_whenMultiplicationServiceThrowsAnException_thenCalculatorBubblesThatExceptionUp() {
            // Given (Setup)
            int x = 10;
            mockUpUntil(MockId.MULTIPLICATION_SERVICE);
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
            verifyUpUntilAnd(MockId.MULTIPLICATION_SERVICE);
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

        private void mockUpUntil(MockId mockId) {
            if (mockId == MockId.ADDITION_SERVICE) {
                return;
            }
            when(additionService.add(anyInt(), anyInt())).thenReturn(SAMPLE_ADDITION_OUTPUT);

            if (mockId == MockId.MULTIPLICATION_SERVICE) {
                return;
            }
            when(multiplicationService.multiply(anyInt(), anyInt())).thenReturn(SAMPLE_MULTIPLICATION_OUTPUT);

            if (mockId == MockId.SUBTRACTION_SERVICE) {
                return;
            }
            when(subtractionService.subtract(anyInt(), anyInt())).thenReturn(SAMPLE_SUBTRACTION_OUTPUT);

            if (mockId == MockId.SUB_MULTI_SERVICE) {
                return;
            }
            when(subMultiService.subtractThenMultiplyBy2(anyInt(), anyInt())).thenReturn(SAMPLE_SUB_MULTI_OUTPUT);
        }

        private void verifyUpUntilAnd(MockId mockId) {
            verify(additionService, times(1)).add(anyInt(), anyInt());
            if (mockId == MockId.ADDITION_SERVICE) {
                return;
            }

            verify(multiplicationService, times(1)).multiply(anyInt(), anyInt());
            if (mockId == MockId.MULTIPLICATION_SERVICE) {
                return;
            }

            verifyZeroInteractions(subtractionService);
            if (mockId == MockId.SUBTRACTION_SERVICE) {
                return;
            }

            verify(subMultiService, times(1)).subtractThenMultiplyBy2(anyInt(), anyInt());
            if (mockId == MockId.SUB_MULTI_SERVICE) {
                return;
            }
        }
    }

    @Nested
    class SameValue {
        @Test
        public void sameValueTest() {
            assertEquals(1, calculator.sameValue(1));
        }
    }

    // Optional: verifyUpUntil(), used only when the method is a void and the test being done is the verify to underlying services.

}