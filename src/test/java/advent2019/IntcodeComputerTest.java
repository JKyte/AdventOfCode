package advent2019;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntcodeComputerTest {

    @Test
    public void testIsImmediate() {
        IntcodeComputer computer = new IntcodeComputer(new int[0]);

        //  When modes is zero, we are NOT in immediate mode
        assertFalse(computer.isImmediateMode(1, 0));

        //  Index 1 is zero
        assertFalse(computer.isImmediateMode(1, 10));
        //  Index 2 is one
        assertTrue(computer.isImmediateMode(2, 10));

        //  Implied indices
        //  Index 1 is one
        assertTrue(computer.isImmediateMode(1, 1));
        //  Index 2 is IMPLIED to be zero.
        assertFalse(computer.isImmediateMode(2, 1));

    }

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
    public void testAdvent2019Day2Part1() {
        int[] input = {1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 10, 19, 23, 2, 9, 23, 27, 1, 6, 27, 31, 2, 31, 9, 35, 1, 5, 35, 39, 1, 10, 39, 43, 1, 10, 43, 47, 2, 13, 47, 51, 1, 10, 51, 55, 2, 55, 10, 59, 1, 9, 59, 63, 2, 6, 63, 67, 1, 5, 67, 71, 1, 71, 5, 75, 1, 5, 75, 79, 2, 79, 13, 83, 1, 83, 5, 87, 2, 6, 87, 91, 1, 5, 91, 95, 1, 95, 9, 99, 1, 99, 6, 103, 1, 103, 13, 107, 1, 107, 5, 111, 2, 111, 13, 115, 1, 115, 6, 119, 1, 6, 119, 123, 2, 123, 13, 127, 1, 10, 127, 131, 1, 131, 2, 135, 1, 135, 5, 0, 99, 2, 14, 0, 0};
        input[1] = 12;
        input[2] = 2;
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(3760627, computer.valueAtIndex(0));
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
    public void testAdvent2019Day02Part2() {
        //  Part 2 -- input "71 95" produces the correct answer.
        int[] input = {1, 71, 95, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 10, 19, 23, 2, 9, 23, 27, 1, 6, 27, 31, 2, 31, 9, 35, 1, 5, 35, 39, 1, 10, 39, 43, 1, 10, 43, 47, 2, 13, 47, 51, 1, 10, 51, 55, 2, 55, 10, 59, 1, 9, 59, 63, 2, 6, 63, 67, 1, 5, 67, 71, 1, 71, 5, 75, 1, 5, 75, 79, 2, 79, 13, 83, 1, 83, 5, 87, 2, 6, 87, 91, 1, 5, 91, 95, 1, 95, 9, 99, 1, 99, 6, 103, 1, 103, 13, 107, 1, 107, 5, 111, 2, 111, 13, 115, 1, 115, 6, 119, 1, 6, 119, 123, 2, 123, 13, 127, 1, 10, 127, 131, 1, 131, 2, 135, 1, 135, 5, 0, 99, 2, 14, 0, 0};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(19690720, computer.valueAtIndex(0));
    }

    @Test
    public void testAdvent2019Day05Example1() {
        //  Example. Prints out what the prompted input is.
        int[] input = {3, 0, 4, 0, 99};
        String[] inputs = {"1234"};

        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "1234");
        assertEquals(1234, computer.getOutputs().get(0).intValue());
    }

    //  Multiply 33 * 3
    @Test
    public void testAdvent2019Day05Example2() {
        int[] input = {1002, 4, 3, 4, 33};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(99, computer.valueAtIndex(4));
    }

    /**
     * Test adding a negative number.
     * 100 + -1, store in position 4.
     */
    @Test
    public void testAdvent2019Day05Example2point5() {
        int[] input = {1101, 100, -1, 4, 0};
        IntcodeComputer computer = new IntcodeComputer(input);
        computer.run();
        assertEquals(99, computer.valueAtIndex(4));
    }

    @Test
    public void testAdvent2019Day05Part1() {
        int[] input = {3, 225, 1, 225, 6, 6, 1100, 1, 238, 225, 104, 0, 1102, 27, 28, 225, 1, 113, 14, 224, 1001, 224, -34, 224, 4, 224, 102, 8, 223, 223, 101, 7, 224, 224, 1, 224, 223, 223, 1102, 52, 34, 224, 101, -1768, 224, 224, 4, 224, 1002, 223, 8, 223, 101, 6, 224, 224, 1, 223, 224, 223, 1002, 187, 14, 224, 1001, 224, -126, 224, 4, 224, 102, 8, 223, 223, 101, 2, 224, 224, 1, 224, 223, 223, 1102, 54, 74, 225, 1101, 75, 66, 225, 101, 20, 161, 224, 101, -54, 224, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 7, 224, 1, 224, 223, 223, 1101, 6, 30, 225, 2, 88, 84, 224, 101, -4884, 224, 224, 4, 224, 1002, 223, 8, 223, 101, 2, 224, 224, 1, 224, 223, 223, 1001, 214, 55, 224, 1001, 224, -89, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 4, 224, 1, 224, 223, 223, 1101, 34, 69, 225, 1101, 45, 67, 224, 101, -112, 224, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 2, 224, 1, 223, 224, 223, 1102, 9, 81, 225, 102, 81, 218, 224, 101, -7290, 224, 224, 4, 224, 1002, 223, 8, 223, 101, 5, 224, 224, 1, 223, 224, 223, 1101, 84, 34, 225, 1102, 94, 90, 225, 4, 223, 99, 0, 0, 0, 677, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1105, 0, 99999, 1105, 227, 247, 1105, 1, 99999, 1005, 227, 99999, 1005, 0, 256, 1105, 1, 99999, 1106, 227, 99999, 1106, 0, 265, 1105, 1, 99999, 1006, 0, 99999, 1006, 227, 274, 1105, 1, 99999, 1105, 1, 280, 1105, 1, 99999, 1, 225, 225, 225, 1101, 294, 0, 0, 105, 1, 0, 1105, 1, 99999, 1106, 0, 300, 1105, 1, 99999, 1, 225, 225, 225, 1101, 314, 0, 0, 106, 0, 0, 1105, 1, 99999, 1007, 677, 677, 224, 102, 2, 223, 223, 1005, 224, 329, 101, 1, 223, 223, 1108, 226, 677, 224, 1002, 223, 2, 223, 1005, 224, 344, 101, 1, 223, 223, 1008, 677, 677, 224, 102, 2, 223, 223, 1005, 224, 359, 101, 1, 223, 223, 8, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 374, 101, 1, 223, 223, 108, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 389, 1001, 223, 1, 223, 1107, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 404, 1001, 223, 1, 223, 7, 226, 677, 224, 1002, 223, 2, 223, 1005, 224, 419, 101, 1, 223, 223, 1107, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 434, 1001, 223, 1, 223, 1107, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 449, 101, 1, 223, 223, 1108, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 464, 101, 1, 223, 223, 8, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 479, 101, 1, 223, 223, 8, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 494, 1001, 223, 1, 223, 1007, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 509, 1001, 223, 1, 223, 108, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 524, 1001, 223, 1, 223, 1108, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 539, 101, 1, 223, 223, 1008, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 554, 101, 1, 223, 223, 107, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 569, 101, 1, 223, 223, 107, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 584, 101, 1, 223, 223, 7, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 599, 101, 1, 223, 223, 1008, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 614, 1001, 223, 1, 223, 107, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 629, 101, 1, 223, 223, 7, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 644, 1001, 223, 1, 223, 1007, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 659, 101, 1, 223, 223, 108, 677, 677, 224, 102, 2, 223, 223, 1005, 224, 674, 1001, 223, 1, 223, 4, 223, 99, 226};
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "1");

        int secondToLastIndex = computer.getOutputs().size() - 1;
        assertEquals(16348437, computer.getOutputs().get(secondToLastIndex).intValue());
    }

    /**
     * Using position mode, consider whether the input is equal to 8 or not.
     * Output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testAdvent2019Day05Example3() {
        int[] input = {3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};
        IntcodeComputer computer = new IntcodeComputer(input);

        //  Input not equal to 8 outputs 0
        testWithInput(computer, "1");
        assertEquals(0, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "8");
        assertEquals(1, computer.getOutputs().get(0).intValue());
    }

    /**
     * Using position mode, consider whether the input is less than 8 or it.
     * Output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testAdvent2019Day05Example4() {
        int[] input = {3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8};
        IntcodeComputer computer = new IntcodeComputer(input);

        testWithInput(computer, "7");
        assertEquals(1, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "8");
        assertEquals(0, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "9");
        assertEquals(0, computer.getOutputs().get(0).intValue());
    }

    /**
     * Using immediate mode, consider whether the input is equal to 8;
     * output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testAdvent2019Day05Example5() {
        int[] input = {3, 3, 1108, -1, 8, 3, 4, 3, 99};
        IntcodeComputer computer = new IntcodeComputer(input);

        testWithInput(computer, "7");
        assertEquals(0, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "8");
        assertEquals(1, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "9");
        assertEquals(0, computer.getOutputs().get(0).intValue());
    }

    /**
     * Using immediate mode, consider whether the input is less than 8;
     * output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testAdvent2019Day05Example6() {
        int[] input = {3, 3, 1107, -1, 8, 3, 4, 3, 99};
        IntcodeComputer computer = new IntcodeComputer(input);

        testWithInput(computer, "7");
        assertEquals(1, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "8");
        assertEquals(0, computer.getOutputs().get(0).intValue());
    }

    /**
     * Here are some jump tests that take an input, then output 0 if the input was zero
     * or 1 if the input was non-zero:
     */
    @Test
    public void testAdvent2019Day05Example7_position() {
        int[] input = {3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}; //  Position mode
        IntcodeComputer computer = new IntcodeComputer(input);

        testWithInput(computer, "0");
        assertEquals(0, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "-1");
        assertEquals(1, computer.getOutputs().get(0).intValue());

        computer = new IntcodeComputer(input);
        testWithInput(computer, "1");
        assertEquals(1, computer.getOutputs().get(0).intValue());
    }

    @Test
    public void testAdvent2019Day05Example7_position_zeroInput() {
        int[] input = {3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}; //  Position mode
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "0");
        assertEquals(0, computer.getOutputs().get(0).intValue());
    }

    @Test
    public void testAdvent2019Day05Example7_position_positiveInput() {
        int[] input = {3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9}; //  Position mode
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "1");
        assertEquals(1, computer.getOutputs().get(0).intValue());
    }

    @Test
    public void testAdvent2019Day05Example7_immediate_negativeInput() {
        int[] input = {3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}; // Immediate mode
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "-1");
        assertEquals(1, computer.getOutputs().get(0).intValue());
    }

    @Test
    public void testAdvent2019Day05Example7_immediate_zeroInput() {
        int[] input = {3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}; // Immediate mode
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "0");
        assertEquals(0, computer.getOutputs().get(0).intValue());
    }

    @Test
    public void testAdvent2019Day05Example7_immediate_positiveInput() {
        int[] input = {3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1}; // Immediate mode
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "1");
        assertEquals(1, computer.getOutputs().get(0).intValue());
    }

    // The program will then output 999 if the input value is below 8,
    // output 1000 if the input value is equal to 8,
    // or output 1001 if the input value is greater than 8.
    @Test
    public void testAdvent2019Day05Example8_7() {
        int[] input = {3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "7");
        assertEquals(999, computer.getOutputs().get(0).intValue());
    }

    // The program will then output 999 if the input value is below 8,
    // output 1000 if the input value is equal to 8,
    // or output 1001 if the input value is greater than 8.
    @Test
    public void testAdvent2019Day05Example8_8() {
        int[] input = {3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "8");
        assertEquals(1000, computer.getOutputs().get(0).intValue());
    }

    // The program will then output 999 if the input value is below 8,
    // output 1000 if the input value is equal to 8,
    // or output 1001 if the input value is greater than 8.
    @Test
    public void testAdvent2019Day05Example8_9() {
        int[] input = {3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99};
        IntcodeComputer computer = new IntcodeComputer(input);
        testWithInput(computer, "9");
        assertEquals(1001, computer.getOutputs().get(0).intValue());
    }

    private void testInputsAndOutputs(int[] program, String[] inputs, String[] outputs) {
        for (int ii = 0; ii < inputs.length; ii++) {
            testInputsAndOutputs(program, inputs[ii], outputs[ii]);
        }
    }

    private void testInputsAndOutputs(int[] program, String input, String output) {
        //  Set aside original input/output stream
        InputStream originalSystemIn = System.in;
        PrintStream originalSystemOut = System.out;
        try {
            //  Setup input stream to feed program input
            InputStream testInput = new ByteArrayInputStream(input.getBytes());
            System.setIn(testInput);

            //  Setup output stream to capture program output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);

            IntcodeComputer computer = new IntcodeComputer(program);
            computer.run();

            //  Capture output
            System.out.flush();

            assertEquals(output, baos.toString());
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(originalSystemOut);
        }
    }

    private IntcodeComputer testWithInput(IntcodeComputer computer, String input) {
        //  Set aside original input stream
        InputStream originalSystemIn = System.in;
        try {
            //  Setup input stream to feed program input
            InputStream testInput = new ByteArrayInputStream(input.getBytes());
            System.setIn(testInput);

            computer.run();

        } finally {
            System.setIn(originalSystemIn);
        }
        return computer;
    }
}
