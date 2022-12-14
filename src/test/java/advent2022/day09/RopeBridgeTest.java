package advent2022.day09;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RopeBridgeTest {

    @Test
    public void exampleOne() {
        List<String> input = InputUtil.toList("advent2022/RopeBridgeExample.txt");
        RopeBridge rb = new RopeBridge();
        rb.withInput(input);
        assertEquals(13, rb.getUniqueVisits());
    }

    @Test
    public void partOne() {
        List<String> input = InputUtil.toList("advent2022/RopeBridge.txt");
        RopeBridge rb = new RopeBridge();
        rb.withInput(input);
        assertEquals(13, rb.getUniqueVisits());
    }

    @Test
    public void exampleTwo() {

    }

    @Test
    public void partTwo() {

    }
}