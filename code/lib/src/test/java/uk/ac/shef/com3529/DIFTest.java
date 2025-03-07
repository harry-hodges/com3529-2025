package uk.ac.shef.com3529;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DIFTest {

//    @Test
//    public void findLast_failure() {
//        int result = DIF.findLast(new int[]{1,2,3,4,5}, 1);
//        assertEquals(0, result);
//    }

    @Test
    public void findLast_defectNotExecuted() {
        int result = DIF.findLast(new int[]{1,2,3,4,5}, 3);
        assertEquals(2, result);
    }

    @Test
    public void findLast_defectExecuted_noInfection() {
        int result = DIF.findLast(new int[]{1,2,3,4,5}, 6);
        assertEquals(-1, result);
    }

//    @Test
//    public void countPositive_failure() {
//        int result = DIF.countPositive(new int[]{0});
//        assertEquals(0, result);
//    }

    @Test
    public void countPositive_defectNotExecuted() {
        int result = DIF.countPositive(new int[]{1,2,3,4,5});
        assertEquals(5, result);
    }

//    @Test
//    public void lastZero_failure() {
//        int result = DIF.lastZero(new int[]{1,2,0,4,5,0});
//        assertEquals(5, result);
//    }

    @Test
    public void lastZero_defectNotExecuted() {
        int result = DIF.lastZero(new int[]{1,2,3,4,5,0});
        assertEquals(5, result);
    }

    @Test
    public void lastZero_defectExecuted_noInfection() {
        int result = DIF.lastZero(new int[]{0,2,3,4,5});
        assertEquals(0, result);
    }
}
