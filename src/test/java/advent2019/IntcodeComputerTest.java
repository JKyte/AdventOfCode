package advent2019;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntcodeComputerTest {

    @Test
    public void testAdvent2019Day2Example1() {
        //  1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
        int[] input = {1, 0, 0, 0, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(2, computer.valueAtIndex(0));
    }

    @Test
    public void testAdvent2019Day2Example2() {
        //  2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
        int[] input = {2, 3, 0, 3, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(6, computer.valueAtIndex(3));
    }

    @Test
    public void testAdvent2019Day2Example3() {
        //  2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
        int[] input = {2, 4, 4, 5, 99, 0};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(9801, computer.valueAtIndex(5));
    }

    @Test
    public void testAdvent2019Day2Example4() {
        //  1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
        int[] input = {1, 1, 1, 4, 99, 5, 6, 0, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(30, computer.valueAtIndex(0));
    }

    @Test
    public void testSmallInput() {
        //  Sample input. Position 0 has value 3500
        int[] input = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(3500, computer.valueAtIndex(0));
    }

    @Test
    public void testAdvent2019Day02PartTwo() {
        //  Part 2 input.
        int[] input = {1, 71, 95, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 10, 19, 23, 2, 9, 23, 27, 1, 6, 27, 31, 2, 31, 9, 35, 1, 5, 35, 39, 1, 10, 39, 43, 1, 10, 43, 47, 2, 13, 47, 51, 1, 10, 51, 55, 2, 55, 10, 59, 1, 9, 59, 63, 2, 6, 63, 67, 1, 5, 67, 71, 1, 71, 5, 75, 1, 5, 75, 79, 2, 79, 13, 83, 1, 83, 5, 87, 2, 6, 87, 91, 1, 5, 91, 95, 1, 95, 9, 99, 1, 99, 6, 103, 1, 103, 13, 107, 1, 107, 5, 111, 2, 111, 13, 115, 1, 115, 6, 119, 1, 6, 119, 123, 2, 123, 13, 127, 1, 10, 127, 131, 1, 131, 2, 135, 1, 135, 5, 0, 99, 2, 14, 0, 0};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(19690720, computer.valueAtIndex(0));
    }

    @Ignore
    @Test
    public void testAdvent2019Day05Example1() {
        //  Example. Prints out what the prompted input is.
        int[] input = {3, 0, 4, 0, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
    }

    @Test
    public void testAdvent2019Day05Example2() {
        int[] input = {1002, 4, 3, 4, 33};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(99, computer.valueAtIndex(4));
    }
}
