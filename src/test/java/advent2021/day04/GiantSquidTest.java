package advent2021.day04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GiantSquidTest {

    @Test
    public void exampleOne() {
        GiantSquid squid = new GiantSquid();
        squid.loadFile("src/main/resources/advent2021/GiantSquidExample.txt");
        squid.playGame();
        assertEquals(4512, squid.winningScore());
    }

    @Test
    public void partOne() {
        GiantSquid squid = new GiantSquid();
        squid.loadFile("src/main/resources/advent2021/GiantSquid.txt");
        squid.playGame();
        assertEquals(71708, squid.winningScore());
    }

    @Test
    public void exampleTwo() {
        GiantSquid squid = new GiantSquid();
        squid.loadFile("src/main/resources/advent2021/GiantSquidExample.txt");
        assertEquals(1924, squid.playToLastBoard());
    }

    @Test
    public void partTwo() {
        GiantSquid squid = new GiantSquid();
        squid.loadFile("src/main/resources/advent2021/GiantSquid.txt");
        assertEquals(34726, squid.playToLastBoard());
    }
}