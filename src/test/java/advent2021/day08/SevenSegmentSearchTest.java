package advent2021.day08;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class SevenSegmentSearchTest {

    @Ignore
    @Test
    public void exampleOne() {
        List<String> segments = Collections.singletonList("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        assertEquals(26, new SevenSegmentSearch().identify(segments).count(1, 4, 7, 8));
    }

    @Test
    public void exampleTwo() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/SevenSegmentSearchExample.txt";
        List<String> segments = readToList(path);
        assertEquals(26, new SevenSegmentSearch().identify(segments).count(1, 4, 7, 8));
    }

    @Test
    public void partOne() throws FileNotFoundException {
        String path = "src/main/resources/advent2021/SevenSegmentSearch.txt";
        List<String> segments = readToList(path);
        assertEquals(412, new SevenSegmentSearch().identify(segments).count(1, 4, 7, 8));
    }

    @Test
    public void exampleThree() {

    }

    @Test
    public void partTwo() {

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