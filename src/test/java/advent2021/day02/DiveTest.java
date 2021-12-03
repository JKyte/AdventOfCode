package advent2021.day02;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DiveTest {

    @Test
    public void partOneExample() {
        List<String> cmds = Arrays.asList("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        assertEquals(150L, new Dive().givenStart(0, 0).givenCommands(cmds).getHorizontalTimesDepth());
    }


    @Test
    public void partOne() throws FileNotFoundException {
        List<String> cmds = readCmds();
        assertEquals(2091984L, new Dive().givenStart(0, 0).givenCommands(cmds).getHorizontalTimesDepth());
    }

    @Test
    public void partTwoExample() {
        List<String> cmds = Arrays.asList("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        assertEquals(900L, new Dive().givenStart(0, 0).givenAim(cmds).getHorizontalTimesDepth());
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        List<String> cmds = readCmds();
        assertEquals(2086261056L, new Dive().givenStart(0, 0).givenAim(cmds).getHorizontalTimesDepth());
    }


    private List<String> readCmds() throws FileNotFoundException {
        List<String> cmds = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/advent2021/Dive.txt"));
        while (scanner.hasNext()) {
            cmds.add(scanner.nextLine());
        }
        return cmds;
    }
}