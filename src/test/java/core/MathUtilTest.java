package core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathUtilTest {

    @Test
    public void testLCM() {
        long[] longs = new long[]{3L, 5L};
        assertEquals(15, MathUtil.lcm(longs));

        longs = new long[]{5L, 21L};
        assertEquals(105L, MathUtil.lcm(longs));
    }

    @Test
    public void testSummation() {
        assertEquals(10, MathUtil.summation(4));
        assertEquals(45, MathUtil.summation(9));
        assertEquals(66, MathUtil.summation(11));
    }
}
