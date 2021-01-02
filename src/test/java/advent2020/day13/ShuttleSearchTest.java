package advent2020.day13;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShuttleSearchTest {

    @Test
    public void testExample1() {
        ShuttleSearch shuttles = new ShuttleSearch();
        shuttles.loadInputs(exampleInput);
        assertEquals(59, shuttles.findFirstShuttle());
        assertEquals(5, shuttles.getTimeToWait(59));
        assertEquals(295, shuttles.getShuttleHash());
    }

    @Test
    public void testPartOne() {
        ShuttleSearch shuttles = new ShuttleSearch();
        shuttles.loadInputs(puzzleInput);
        assertEquals(6568, shuttles.getShuttleHash());
    }

    @Test
    public void testExample2() {
        ShuttleSearch shuttles = new ShuttleSearch();
        shuttles.loadInputs(exampleInput);
        assertEquals(1068781, shuttles.crt());
    }

    @Test
    public void testExample3() {
        ShuttleSearch shuttles = new ShuttleSearch();
        shuttles.loadInputs("123\n17,x,13,19");
        assertEquals(3417, shuttles.crt());

        shuttles.loadInputs("123\n67,7,59,61");
        assertEquals(754018, shuttles.crt());

        shuttles.loadInputs("123\n67,x,7,59,61");
        assertEquals(779210, shuttles.crt());

        shuttles.loadInputs("123\n67,7,x,59,61");
        assertEquals(1261476, shuttles.crt());

        shuttles.loadInputs("123\n1789,37,47,1889");
        assertEquals(1202161486, shuttles.crt());
    }

    @Test
    public void testPartTwo() {
        ShuttleSearch shuttles = new ShuttleSearch();
        shuttles.loadInputs(puzzleInput);
        assertEquals(554865447501099L, shuttles.crt());
    }

    private String exampleInput = "939\n" +
            "7,13,x,x,59,x,31,19";

    private String puzzleInput = "1001612\n" +
            "19,x,x,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,821,x,x,x,x,x,x,x,x,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,463,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,23";
}
