package advent2022.day10;

import core.InputUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CathodeRayTubeTest {

    @Test
    public void miniExample() {
        List<String> input = Arrays.asList("noop", "addx 3", "addx -5");
        CathodeRayTube crt = new CathodeRayTube();
        crt.withInput(input);
        assertEquals(-1, crt.getRegisterX());
    }

    @Test
    public void exampleOne() {
        List<String> input = InputUtil.toList("advent2022/CathodeRayTubeExample.txt");
        CathodeRayTube crt = new CathodeRayTube();
        crt.withInput(input);
        assertEquals(420, crt.getSignalStrength(20));
        assertEquals(1140, crt.getSignalStrength(60));
        assertEquals(1800, crt.getSignalStrength(100));
        assertEquals(2940, crt.getSignalStrength(140));
        assertEquals(2880, crt.getSignalStrength(180));
//        assertEquals(3960, crt.getSignalStrength(220));
        assertEquals(13140, crt.sumSignals(20, 60, 100, 140, 180, 220));
    }

    @Test
    public void partOne() {

    }

    @Test
    public void exampleTwo() {

    }

    @Test
    public void partTwo() {

    }

}