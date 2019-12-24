package advent2019.day02;

import advent2019.IntcodeComputer;

import java.util.Arrays;

/**
 * 1202 Program Alarm
 * <p>
 * Part 1 Answer: 3760627
 * <p>
 * Part 2 Answer: 71 95
 */
public class ProgramAlarm {
    public static void main(String[] args) {
        //  Sample input. Position 0 has value 3500
//        int[] input = {1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50};
        //  Part 1 input.
        long[] input = {1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 9, 19, 1, 10, 19, 23, 2, 9, 23, 27, 1, 6, 27, 31, 2, 31, 9, 35, 1, 5, 35, 39, 1, 10, 39, 43, 1, 10, 43, 47, 2, 13, 47, 51, 1, 10, 51, 55, 2, 55, 10, 59, 1, 9, 59, 63, 2, 6, 63, 67, 1, 5, 67, 71, 1, 71, 5, 75, 1, 5, 75, 79, 2, 79, 13, 83, 1, 83, 5, 87, 2, 6, 87, 91, 1, 5, 91, 95, 1, 95, 9, 99, 1, 99, 6, 103, 1, 103, 13, 107, 1, 107, 5, 111, 2, 111, 13, 115, 1, 115, 6, 119, 1, 6, 119, 123, 2, 123, 13, 127, 1, 10, 127, 131, 1, 131, 2, 135, 1, 135, 5, 0, 99, 2, 14, 0, 0};

        //  Part 1
//        input[1] = 12;
//        input[2] = 2;
//        IntcodeComputer computer = new IntcodeComputer(input);
//        computer.run();
//        System.out.println(computer.valueAtIndex(0));

        for (int ii = 0; ii <= 99; ii++) {
            for (int jj = 0; jj <= 99; jj++) {
                long[] copy = Arrays.copyOf(input, input.length);
                copy[1] = ii;   //  Noun
                copy[2] = jj;   //  Verb

                IntcodeComputer computer = new IntcodeComputer(copy);
                computer.run();
                long value = computer.valueAtIndex(0);
                if (value == 19690720) {
                    System.out.println("Found value at Noun: " + ii + " Verb: " + jj);
                    System.exit(0);
                }
            }
        }
    }
}
