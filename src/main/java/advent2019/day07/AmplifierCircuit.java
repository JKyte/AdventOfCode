package advent2019.day07;

import advent2019.IntcodeComputer;
import core.Permutation;

import java.util.Arrays;

/**
 * Part 1: 338603
 */
public class AmplifierCircuit {

    public static void main(String[] args) {
        //  Part 1
        int[] program = {3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 59, 84, 97, 110, 191, 272, 353, 434, 99999, 3, 9, 1002, 9, 2, 9, 101, 4, 9, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 1001, 9, 3, 9, 1002, 9, 5, 9, 101, 5, 9, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 101, 5, 9, 9, 1002, 9, 3, 9, 101, 2, 9, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 1002, 9, 3, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 1001, 9, 3, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99};

        AmplifierCircuit ac = new AmplifierCircuit(program);
        ac.getMaxThrust(5);
    }

    private int[] program;

    public AmplifierCircuit(int[] program) {
        this.program = Arrays.copyOf(program, program.length);
        System.out.println("Starting program: " + Arrays.toString(program));
    }

    public int getMaxThrust(int max) {
        int maxThrust = 0;
        int[] maxInputs = new int[0];

        int[] inputs = new int[max];
        for(int ii = 0; ii < max; ii++){
            inputs[ii] = ii;
        }

        Permutation permutation = new Permutation(inputs);
        while(permutation.hasNext()) {

            int[] input = permutation.next();

            int tmp = getThrust(input);
            System.out.println("Inputs: " + Arrays.toString(input) + " thrust: " + tmp + " program: " + Arrays.toString(program));
            if (tmp > maxThrust) {
                maxThrust = tmp;
                maxInputs = input;
            }
        }
        System.out.println("Max Thrust: " + maxThrust);
        System.out.println("Max Inputs: " + Arrays.toString(maxInputs));
        return maxThrust;
    }

    public int getThrust(int[] inputs) {
        int prevOutput = 0;
        for (int ii = 0; ii < inputs.length; ii++) {
            prevOutput = getThrust(inputs[ii], prevOutput);
        }
        return prevOutput;
    }

    public int getThrust(int phaseSetting, int input) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.runWithInputs(new int[]{phaseSetting, input});
        if (computer.getOutputs().size() > 1) {
            System.out.println("More than one output.");
            System.exit(0);
        }
        return computer.getOutputs().get(0);
    }
}
