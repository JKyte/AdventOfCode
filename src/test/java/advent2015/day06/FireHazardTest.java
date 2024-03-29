package advent2015.day06;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FireHazardTest {

    @Test
    public void exampleOne() {
        assertEquals(1000000, new FireHazard()
                .createGrid(1000, 1000)
                .processInstructions(Collections.singletonList("turn on 0,0 through 999,999"))
                .countLights());
    }

    @Test
    public void partOne() throws FileNotFoundException {
        List<String> lines = readToList();
        assertEquals(543903, new FireHazard().createGrid(1000, 1000).processInstructions(lines).countLights());  //  542387 too low
    }

    @Test
    public void exampleTwo() {
        assertEquals(1, new FireHazard()
                .createGrid(1000, 1000)
                .altProcessInstructions(Collections.singletonList("turn on 0,0 through 0,0"))
                .countLights());

        assertEquals(2000000, new FireHazard()
                .createGrid(1000, 1000)
                .altProcessInstructions(Collections.singletonList("toggle 0,0 through 999,999"))
                .altCountLights());
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        List<String> lines = readToList();
        assertEquals(14687245, new FireHazard().createGrid(1000, 1000).altProcessInstructions(lines).altCountLights());  //  542387 too low
    }

    private List<String> readToList() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/main/resources/advent2015/FireHazard.txt"))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }
}