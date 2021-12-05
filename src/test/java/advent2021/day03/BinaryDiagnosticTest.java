package advent2021.day03;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class BinaryDiagnosticTest {

    @Test
    public void exampleOne() {
        List<String> numbers = Arrays.asList("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010");
        assertEquals(198, new BinaryDiagnostic().findGammaEpsilon(numbers).getPowerConsumption());
    }

    @Test
    public void partOne() throws FileNotFoundException {
        List<String> numbers = readToList();
        assertEquals(738234, new BinaryDiagnostic().findGammaEpsilon(numbers).getPowerConsumption());
    }

    @Test
    public void exampleTwo() {
        List<String> numbers = Arrays.asList("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010");
        BinaryDiagnostic diagnostic = new BinaryDiagnostic();
        assertEquals(23, diagnostic.findOxygenGeneratorRating(numbers).getO2rating());
        assertEquals(10, diagnostic.findC02ScrubberRating(numbers).getC02rating());
        assertEquals(3969126, diagnostic.getLifeSupportRating());
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        List<String> numbers = readToList();
        BinaryDiagnostic diagnostic = new BinaryDiagnostic();
        diagnostic.findOxygenGeneratorRating(numbers).findC02ScrubberRating(numbers);
        assertEquals(230, diagnostic.getLifeSupportRating());
    }

    private List<String> readToList() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/advent2021/BinaryDiagnostic.txt"));
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}