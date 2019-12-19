package advent2019;

import advent2019.day12.NBodyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NBodyProblemTest {

    @Test
    public void testLeastCommonMultiple_simple() {
        int x = 3;
        int y = 4;

        //  Expected LCM is 12

        NBodyProblem nBody = new NBodyProblem();
        assertEquals(12, nBody.lcm(x, y));
    }

    @Test
    public void testLeastCommonMultiple_complex() {
        int x = 18;
        int y = 28;
        int z = 44;

        //  Expected LCM is 2772.

        NBodyProblem nBody = new NBodyProblem();
        assertEquals(2772, nBody.lcm(x, y, z));
    }

    @Test
    public void testAllEquals() {

        NBodyProblem nBody = new NBodyProblem();
        assertTrue(nBody.allEquals(1L, 1L));
        assertTrue(nBody.allEquals(1L, 1L, 1L, 1L));
        assertFalse(nBody.allEquals(1L, 1L, 1L, 1000L));
        assertFalse(nBody.allEquals(25L, 50L));
    }
}
