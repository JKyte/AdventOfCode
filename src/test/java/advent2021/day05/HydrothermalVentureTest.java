package advent2021.day05;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class HydrothermalVentureTest {

    @Test
    public void exampleOne() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/HydrothermalVentureExample.txt";
        List<String> inputs = readToList(path);
        HydrothermalVenture venture = new HydrothermalVenture();
        assertEquals(5, venture.processVents(inputs).countOverlapsGreaterThanTwo());
//        venture.printGrid();
    }

    @Test
    public void partOne() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/HydrothermalVenture.txt";
        List<String> inputs = readToList(path);
        assertEquals(5084, new HydrothermalVenture().processVents(inputs).countOverlapsGreaterThanTwo());
    }

    @Test
    public void exampleTwo() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/HydrothermalVentureExample.txt";
        List<String> inputs = readToList(path);
        HydrothermalVenture venture = new HydrothermalVenture().processVents(inputs, true);
//        venture.printGrid();
        assertEquals(12, venture.countOverlapsGreaterThanTwo());
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/HydrothermalVenture.txt";
        List<String> inputs = readToList(path);
        assertEquals(17882, new HydrothermalVenture().processVents(inputs, true).countOverlapsGreaterThanTwo());
    }

    private List<String> readToList(String path) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }
}