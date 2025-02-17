package uk.ac.shef.com3529;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalendarTest {

    @Test
    public void shouldRecogniseSameDate() {
        int result = Calendar.daysBetweenTwoDates(2025, 17, 2,
                                                  2025, 17, 2);
        assertEquals(0, result);
    }

    @Test
    public void correctlyWorksOutDaysBetween() {
        int result = Calendar.daysBetweenTwoDates(2023, 1, 1,
                2024, 1, 1);
        assertEquals(365, result);
    }

    @Test
    public void accountsForLeapYear() {
        int result = Calendar.daysBetweenTwoDates(2024, 1, 1,
                                                  2025, 1, 1);
        assertEquals(366, result);
    }

}
