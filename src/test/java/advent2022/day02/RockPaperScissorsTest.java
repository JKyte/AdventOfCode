package advent2022.day02;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RockPaperScissorsTest {

    @Test
    public void exampleOne() {
        List<String> game = InputUtil.toList("advent2022/RockPaperScissorsExample.txt");
        RockPaperScissors rps = new RockPaperScissors();
        assertEquals(15, rps.processGame(game));
    }

    @Test
    public void partOne() {
        List<String> game = InputUtil.toList("advent2022/RockPaperScissors.txt");
        RockPaperScissors rps = new RockPaperScissors();
        assertEquals(12276, rps.processGame(game));
    }

    @Test
    public void exampleTwo() {
        List<String> game = InputUtil.toList("advent2022/RockPaperScissorsExample.txt");
        RockPaperScissors rps = new RockPaperScissors();
        assertEquals(12, rps.processGame(game, true));
    }

    @Test
    public void partTwo() {
        List<String> game = InputUtil.toList("advent2022/RockPaperScissors.txt");
        RockPaperScissors rps = new RockPaperScissors();
        assertEquals(9975, rps.processGame(game, true));
    }
}