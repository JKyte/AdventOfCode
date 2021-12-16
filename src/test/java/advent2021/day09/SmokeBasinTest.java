package advent2021.day09;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class SmokeBasinTest {

    @Test
    public void exampleOne() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/SmokeBasinExample.txt";
        String[] input = readToArray(path);
        assertEquals(15, new SmokeBasin().withInput(input).findLowPoints().sum());
    }

    @Test
    public void partOne() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/SmokeBasin.txt";
        String[] input = readToArray(path);
        assertEquals(456, new SmokeBasin().withInput(input).findLowPoints().sum());
    }

    @Test
    public void exampleTwo() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/SmokeBasinExample.txt";
        String[] input = readToArray(path);
        assertEquals(1134, new SmokeBasin().withInput(input).findBasins().sumBasins());
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/SmokeBasin.txt";
        String[] input = readToArray(path);
        assertEquals(1047744, new SmokeBasin().withInput(input).findBasins().sumBasins());
    }

    private String[] readToArray(String path) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines.toArray(new String[0]);
    }

}