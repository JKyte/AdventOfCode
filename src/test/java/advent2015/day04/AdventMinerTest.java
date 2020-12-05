package advent2015.day04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdventMinerTest {

    @Test
    public void testExamplePart1() {
        AdventMiner miner = new AdventMiner();
        assertEquals(609043, miner.findLowestHash("abcdef", "00000"));
        assertEquals(1048970, miner.findLowestHash("pqrstuv", "00000"));
    }

    @Test
    public void testPartOne() {
        AdventMiner miner = new AdventMiner();
        assertEquals(117946, miner.findLowestHash("ckczppom", "00000"));
    }

    @Test
    public void testPartTwo() {
        AdventMiner miner = new AdventMiner();
        assertEquals(3938038, miner.findLowestHash("ckczppom", "000000"));
    }
}
