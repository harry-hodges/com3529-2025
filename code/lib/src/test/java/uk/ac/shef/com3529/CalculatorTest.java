package uk.ac.shef.com3529;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.*;
import uk.ac.shef.com3529.calculator.*;

import java.util.InputMismatchException;

public class CalculatorTest {

    private Reader mockReader;
    private Writer mockWriter;
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        // Create mocks for Reader and Writer
        mockReader = mock(Reader.class);
        mockWriter = mock(Writer.class);

        // Create the Calculator instance, passing the mocked Reader and Writer
        calculator = new Calculator(mockReader, mockWriter);
    }

    @Test
    public void shouldAddTwoNumbers(){
        // Given the sum to do
        when(mockReader.readDouble()).thenReturn(8.0, 5.0);
        when(mockReader.readWord()).thenReturn("+");

        // When the calculate method is called
        calculator.calculate();

        // Then the correct output was written by Writer
        verify(mockWriter, times(2)).write("Enter a number:");
        verify(mockWriter).write("Enter an operator (+, -, *, or /):");
        verify(mockWriter).write("8.0 + 5.0 = 13.0");
    }

    @Test
    public void shouldSubtractTwoNumbers(){
        // Given the subtraction to do
        when(mockReader.readDouble()).thenReturn(8.0, 5.0);
        when(mockReader.readWord()).thenReturn("-");

        // When the calculate method is called
        calculator.calculate();

        // Then the correct output was written by Writer
        verify(mockWriter).write("8.0 - 5.0 = 3.0");
    }

    @Test
    public void shouldMultiplyTwoNumbers(){
        // Given the multiplication to do
        when(mockReader.readDouble()).thenReturn(8.0, 5.0);
        when(mockReader.readWord()).thenReturn("*");

        // When the calculate method is called
        calculator.calculate();

        // Then the correct output was written by Writer
        verify(mockWriter).write("8.0 * 5.0 = 40.0");
    }

    @Test
    public void shouldDivideTwoNumbers(){
        // Given the division to do
        when(mockReader.readDouble()).thenReturn(8.0, 5.0);
        when(mockReader.readWord()).thenReturn("/");

        // When the calculate method is called
        calculator.calculate();

        // Then the correct output was written by Writer
        verify(mockWriter).write("8.0 / 5.0 = 1.6");
    }

    @Test
    public void shouldHandleInvalidOperatorInput(){
        // Given the invalid operator input
        when(mockReader.readDouble()).thenReturn(8.0, 5.0);
        when(mockReader.readWord()).thenReturn("%", "+");

        // When the calculate method is called
        calculator.calculate();

        // Then the correct output was written by Writer
        verify(mockWriter).write("Please try again (enter an operator):");
    }

    @Test
    public void shouldHandleInvalidNumberInput(){
        // Given the invalid number input
        when(mockReader.readDouble()).thenThrow(new InputMismatchException()).thenReturn(8.0, 5.0);
        when(mockReader.readWord()).thenReturn("+");

        // When the calculate method is called
        calculator.calculate();

        // Then the correct output was written by Writer
        verify(mockWriter).write("Please try again (enter a number):");
    }

}
