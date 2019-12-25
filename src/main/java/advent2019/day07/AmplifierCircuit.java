package advent2019.day07;

import advent2019.IntcodeComputer;
import core.Permutation;

import java.util.Arrays;

/**
 * Part 1: 338603
 * <p>
 * Part 2: 63103596
 */
public class AmplifierCircuit {

    public static void main(String[] args) {
        //  Part 1
        long[] program = {3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 59, 84, 97, 110, 191, 272, 353, 434, 99999, 3, 9, 1002, 9, 2, 9, 101, 4, 9, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 1001, 9, 3, 9, 1002, 9, 5, 9, 101, 5, 9, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 101, 5, 9, 9, 1002, 9, 3, 9, 101, 2, 9, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 1002, 9, 3, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 1001, 9, 3, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99};

        AmplifierCircuit ac = new AmplifierCircuit(program);
        ac.getMaxThrust(5);
    }

    private long[] program;

    public AmplifierCircuit(long[] program) {
        this.program = Arrays.copyOf(program, program.length);
//        System.out.println("Starting program: " + Arrays.toString(program));
    }


    public long getMaxThrust(int max) {
        long maxThrust = 0;
        long[] maxInputs = new long[0];

        long[] inputs = new long[max];
        for (int ii = 0; ii < max; ii++) {
            inputs[ii] = ii;
        }

        Permutation permutation = new Permutation(inputs);
        while (permutation.hasNext()) {

            long[] input = permutation.next();

            long tmp = getThrust(input);
//            System.out.println("Inputs: " + Arrays.toString(input) + " thrust: " + tmp + " program: " + Arrays.toString(program));
            if (tmp > maxThrust) {
                maxThrust = tmp;
                maxInputs = input;
            }
        }
//        System.out.println("Max Thrust: " + maxThrust);
//        System.out.println("Max Inputs: " + Arrays.toString(maxInputs));
        return maxThrust;
    }

    public long getThrust(long[] inputs) {
        long prevOutput = 0;
        for (int ii = 0; ii < inputs.length; ii++) {
            prevOutput = getThrust(inputs[ii], prevOutput);
        }
        return prevOutput;
    }

    public long getThrust(long phaseSetting, long input) {
        IntcodeComputer computer = new IntcodeComputer(program);
        computer.runWithInputs(phaseSetting, input);
        if (computer.getOutputs().size() > 1) {
            System.out.println("More than one output.");
            System.exit(0);
        }
        return computer.getOutputs().get(0);
    }


    public long getMaxThrustWithFeedbackLoop() {
        long maxThrust = 0;
        long[] maxInputs = new long[0];

        long[] inputs = new long[5];
        for (int ii = 0; ii < 5; ii++) {
            inputs[ii] = ii + 5;
        }

        Permutation permutation = new Permutation(inputs);
        while (permutation.hasNext()) {

            long[] input = permutation.next();

            long tmp = getThrustWithFeedbackLoop(input);
//            System.out.println("Inputs: " + Arrays.toString(input) + " thrust: " + tmp + " program: " + Arrays.toString(program));
            if (tmp > maxThrust) {
                maxThrust = tmp;
                maxInputs = input;
            }
        }
//        System.out.println("Max Thrust: " + maxThrust);
//        System.out.println("Max Inputs: " + Arrays.toString(maxInputs));
        return maxThrust;
    }

    public long getThrustWithFeedbackLoop(long[] inputs) {
        long prevOutput = 0;

        IntcodeComputer[] computerBank = new IntcodeComputer[5];
        for (int ii = 0; ii < computerBank.length; ii++) {
            computerBank[ii] = new IntcodeComputer(program, ii);
        }

        boolean allDone = false;
        while (!allDone) {
            for (int ii = 0; ii < inputs.length; ii++) {

                //  Only run if computer is executing.
                if (computerBank[ii].isExecuting()) {
                    if (computerBank[ii].isHalted()) {
                        //  When resuming only provide the previous output.
                        computerBank[ii].resumeWithInputs(prevOutput);
                    } else {
                        computerBank[ii].runWithInputs(inputs[ii], prevOutput);
                    }

                    prevOutput = computerBank[ii].getLastOutput();
                }
            }

            boolean anyExecuting = false;
            for (IntcodeComputer computer : computerBank) {
                if (computer.isExecuting()) {
                    anyExecuting = true;
                    break;
                }
            }

            if (!anyExecuting) {
                allDone = true;
            }
        }
        return prevOutput;
    }
}
