package advent2022.day01;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CalorieCountingTest {

    private CalorieCounting counting;

    @Before
    public void setup() {
        counting = new CalorieCounting();
    }

    @Test
    public void exampleOne() throws FileNotFoundException {
        String inputFile = "src/main/resources/advent2022/CalorieCountingExample.txt";
        List<String> inputs = readFileToList(inputFile);
        counting.consumeInputs(inputs);
        counting.findTheElfWithTheMost();

        assertEquals(24000, counting.getMaxCalorie());
        assertEquals(4, counting.getMaxIndex());
    }

    @Test
    public void partOne() throws FileNotFoundException {
        String inputFile = "src/main/resources/advent2022/CalorieCounting.txt";
        List<String> inputs = readFileToList(inputFile);
        counting.consumeInputs(inputs);
        counting.findTheElfWithTheMost();

        assertEquals(69310, counting.getMaxCalorie());
        assertEquals(178, counting.getMaxIndex());
    }

    @Test
    public void exampleTwo() throws FileNotFoundException {
        String inputFile = "src/main/resources/advent2022/CalorieCountingExample.txt";
        List<String> inputs = readFileToList(inputFile);
        counting.consumeInputs(inputs);
        counting.findTheElfWithTheMost();

        assertEquals(45000, counting.sumTopThree());
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        String inputFile = "src/main/resources/advent2022/CalorieCounting.txt";
        List<String> inputs = readFileToList(inputFile);
        counting.consumeInputs(inputs);
        counting.findTheElfWithTheMost();

        assertEquals(206104, counting.sumTopThree());
    }


    private List<String> readFileToList(String file) throws FileNotFoundException {
        List<String> inputs = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNext()) {
                inputs.add(scanner.nextLine());
            }
        }
        return inputs;
    }

}