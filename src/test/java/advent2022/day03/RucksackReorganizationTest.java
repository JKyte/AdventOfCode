package advent2022.day03;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RucksackReorganizationTest {

    @Test
    public void testFixRuck() {
        RucksackReorganization rr = new RucksackReorganization();
        assertEquals(16, rr.fixRuck("vJrwpWtwJgWrhcsFMMfFFhFp"));
    }

    @Test
    public void exampleOne() {
        List<String> inputs = InputUtil.toList("advent2022/RucksackReorganizationExample.txt");
        RucksackReorganization rr = new RucksackReorganization();
        assertEquals(157, rr.rucks(inputs));
    }

    @Test
    public void partOne() {
        List<String> inputs = InputUtil.toList("advent2022/RucksackReorganization.txt");
        RucksackReorganization rr = new RucksackReorganization();
        assertEquals(7821, rr.rucks(inputs));
    }

    @Test
    public void exampleTwo() {
        List<String> inputs = InputUtil.toList("advent2022/RucksackReorganizationExample.txt");
        RucksackReorganization rr = new RucksackReorganization();
        assertEquals(70, rr.badge(inputs));
    }

    @Test
    public void partTwo() {
        List<String> inputs = InputUtil.toList("advent2022/RucksackReorganization.txt");
        RucksackReorganization rr = new RucksackReorganization();
        assertEquals(2752, rr.badge(inputs));
    }
}