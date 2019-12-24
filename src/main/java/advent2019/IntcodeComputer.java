package advent2019;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class IntcodeComputer implements Runnable {

    private int[] register;

    private ArrayList<Integer> outputs;

    public IntcodeComputer(int[] register) {
        this.register = register;
        this.outputs = new ArrayList<>();
    }

    @Override
    public void run() {
        int index = 0;
        boolean running = true;
        while (running) {

            String opcodeAndMode = "";
            //  Switch on opcode
            int opcode = register[index];
            opcodeAndMode += opcode;
            int modes = 0;

            if (opcode > 99) {
//                System.out.println("We got parameter modes: " + opcode);
                int actualOpCode = opcode % 100;
//                System.out.println("Actual op code: " + actualOpCode);
                modes = opcode / 100;
//                System.out.println("Remainder: " + modes);
                opcode = actualOpCode;
                opcodeAndMode += "  modes: " + modes;
            }
            System.out.println("opcode: " + opcodeAndMode);

            switch (opcode) {

                case 1:
                    //  OPCODE 1 -- ADD, 3 args
                    int addArgOne = parseArgument(index, 1, modes);
                    int addArgTwo = parseArgument(index, 2, modes);
                    System.out.println("ADD  " + addArgOne + "  " + addArgTwo);
                    register[register[index + 3]] = addArgOne + addArgTwo;
                    index += 4;
                    continue;

                case 2:
                    //  OPCODE 2 -- MULTIPLY, 3 args
                    int multArgOne = parseArgument(index, 1, modes);
                    int multArgTwo = parseArgument(index, 2, modes);
                    System.out.println("MULTIPLY  " + multArgOne + "  " + multArgTwo);
                    register[register[index + 3]] = multArgOne * multArgTwo;
                    index += 4;
                    continue;

                case 3:
                    //  OPCODE 3 -- SAVE INPUT, 1 arg
//                    System.out.println("SAVE INPUT");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Papers, please: ");
                    int input = Integer.parseInt(scanner.nextLine());
                    scanner.close();
                    register[register[index + 1]] = input;
                    index += 2;
                    continue;

                case 4:
                    //  OPCODE 4 -- OUTPUT, 1 arg
//                    int outValue = register[register[index + 1]];
                    int outValue = parseArgument(index, 1, modes);
                    outputs.add(outValue);
                    System.out.println("OUTPUT  " + outValue);
//                    if (isImmediateMode(1, modes)) {
//                        System.out.println("Output (immediate): ");
//                        System.exit(0);
//                    }
                    index += 2;
                    continue;

                case 5:
                    //  OPCODE 5 -- JUMP-IF-TRUE
                    int jumpIfTrueArgOneValue = parseArgument(index, 1, modes);
                    int jumpIfTrueArgTwoValue = parseArgument(index, 2, modes);
                    System.out.println("JUMP-IF-TRUE  " + jumpIfTrueArgOneValue + "  " + jumpIfTrueArgTwoValue);
                    if (jumpIfTrueArgOneValue != 0) {

                        index = jumpIfTrueArgTwoValue;
                    } else {
                        //  No Jump if zero
                        index += 3;
                    }
                    continue;

                case 6:
                    //  OPCODE 6 -- JUMP-IF-FALSE
                    int jumpIfFalseArgOneValue = parseArgument(index, 1, modes);
                    int jumpIfFalseArgTwoValue = parseArgument(index, 2, modes);
                    System.out.println("JUMP-IF-FALSE  " + jumpIfFalseArgOneValue + "  " + jumpIfFalseArgTwoValue);
                    if (jumpIfFalseArgOneValue == 0) {
                        //  If the first parameter is zero, set the instruction point to the second arg value.
                        index = jumpIfFalseArgTwoValue;
                    } else {
                        //  No Jump if non-zero
                        index += 3;
                    }
                    continue;

                case 7:
                    //  OPCODE 7 -- LESS-THAN
                    int lessThanArgOneValue = parseArgument(index, 1, modes);
                    int lessThanArgTwoValue = parseArgument(index, 2, modes);
                    System.out.println("LESS-THAN  " + lessThanArgOneValue + "  " + lessThanArgTwoValue);
                    int lessThanDest = register[index + 3];
                    if (lessThanArgOneValue < lessThanArgTwoValue) {
                        register[lessThanDest] = 1;
                    } else {
                        register[lessThanDest] = 0;
                    }
                    index += 4;
                    continue;

                case 8:
                    //  OPCODE 8 -- EQUALS
                    int equalsArgOneValue = parseArgument(index, 1, modes);
                    int equalsArgTwoValue = parseArgument(index, 2, modes);
                    System.out.println("EQUALS  " + equalsArgOneValue + "  " + equalsArgTwoValue);
                    int equalsDestArg = register[index + 3];
                    if (equalsArgOneValue == equalsArgTwoValue) {
                        register[equalsDestArg] = 1;
                    } else {
                        register[equalsDestArg] = 0;
                    }
                    index += 4;
                    continue;

                case 99:
                    //  OPCODE 99 -- END PROGRAM
                    System.out.println("99 -- END PROGRAM");
                    running = false;
                    continue;

                default:
                    System.out.println("Unknown opcode: " + register[index]);
                    System.exit(0);
            }
        }
    }

    public int parseArgument(int opcodeIndex, int argIndex, int modes) {
        if (isImmediateMode(argIndex, modes)) {
            System.out.println("\targ " + argIndex + " is immediate");
            //  If mode is ONE then execute in IMMEDIATE MODE (value of arg_value)
            return register[opcodeIndex + argIndex];
        } else {
            System.out.println("\targ " + argIndex + " is position");
            //  If mode is ZERO then execute in POSITION MODE (value is register[arg_value]
            return register[register[opcodeIndex + argIndex]];
        }
    }

    /**
     * TODO bug is here.
     * Position mode is mode 0
     * Immediate mode is mode 1
     *
     * @param argIndex
     * @param modes
     * @return
     */
    public boolean isImmediateMode(int argIndex, int modes) {
        if (modes == 0) {
            return false;
        }

        if (modes > 0) {
            //  Check for possibility of immediate mode.

            String modeString = Integer.toString(modes);
            //  If the argIndex is greater than the mode string, then assume zero -- immediate mode.
            if (argIndex <= modeString.length()) {
                //  Otherwise the mode string falls within our bounds.
                modeString = StringUtils.reverse(modeString);
                int mode = Integer.parseInt("" + modeString.charAt(argIndex - 1));
//                System.out.println("Mode Int: " + mode);
                if (mode == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public int valueAtIndex(int index) {
        return register[index];
    }

    public ArrayList<Integer> getOutputs() {
        return outputs;
    }

}
