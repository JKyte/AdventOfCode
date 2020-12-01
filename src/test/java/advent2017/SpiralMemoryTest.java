package advent2017;

import advent2017.day03.SpiralMemory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpiralMemoryTest {

    @Test
    public void testExample1() {
        SpiralMemory memory = new SpiralMemory();
        assertEquals(0, memory.getSteps(1));
    }

    @Test
    public void testExample2() {
        SpiralMemory memory = new SpiralMemory();
        assertEquals(3, memory.getSteps(12));
    }

    @Test
    public void testExample3() {
        SpiralMemory memory = new SpiralMemory();
        assertEquals(2, memory.getSteps(23));
    }

    @Test
    public void testExample4() {
        SpiralMemory memory = new SpiralMemory();
        assertEquals(31, memory.getSteps(1024));
    }

    @Test
    public void testInner() {
        SpiralMemory memory = new SpiralMemory();
        assertEquals(1, memory.getSteps(2));
        assertEquals(2, memory.getSteps(3));
        assertEquals(1, memory.getSteps(4));
        assertEquals(2, memory.getSteps(5));
        assertEquals(1, memory.getSteps(6));
        assertEquals(2, memory.getSteps(7));
        assertEquals(1, memory.getSteps(8));
        assertEquals(2, memory.getSteps(9));
    }

    @Test
    public void testPartOne() {
        SpiralMemory memory = new SpiralMemory();
        assertEquals(-1, memory.getSteps(277678));
    }

}
