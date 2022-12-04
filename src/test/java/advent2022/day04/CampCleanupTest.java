package advent2022.day04;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CampCleanupTest {

    @Test
    public void exampleOne() {
        List<String> input = InputUtil.toList("advent2022/CampCleanupExample.txt");
        CampCleanup cleanup = new CampCleanup();
        assertEquals(2, cleanup.countPairs(input));
    }

    @Test
    public void partOne() {
        List<String> input = InputUtil.toList("advent2022/CampCleanup.txt");
        CampCleanup cleanup = new CampCleanup();
        assertEquals(576, cleanup.countPairs(input));
    }

    @Test
    public void exampleTwo() {
        List<String> input = InputUtil.toList("advent2022/CampCleanupExample.txt");
        CampCleanup cleanup = new CampCleanup();
        assertEquals(4, cleanup.countPairs(input, true));
    }

    @Test
    public void partTwo() {
        List<String> input = InputUtil.toList("advent2022/CampCleanup.txt");
        CampCleanup cleanup = new CampCleanup();
        //  952 is not correct, too high
        assertEquals(905, cleanup.countPairs(input, true));
    }
}