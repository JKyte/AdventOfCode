package advent2019;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IntcodeComputer {

    //  Used to identify a running computer.
    private long id = -1;

    // Flag to determine if computer is still executing.
    private boolean executing;

    //  Flag to determine if execution is halted or not.
    private boolean halted;

    // Track the execution pointer globally.
    private long index = 0;

    //  Used for instructions in relative mode (mode 2)
    private long relativeBase = 0;

    private long[] register;

    private ArrayList<Long> outputs;

    public IntcodeComputer(long[] register) {
        this(register, 0);
    }

    public IntcodeComputer(long[] register, long id) {
        //  Operate on copy of program.
        this.register = Arrays.copyOf(register, register.length);
        this.outputs = new ArrayList<>();
        this.id = id;
        this.executing = true;
        this.halted = false;
    }

    public void resume() {
        if (!halted & executing) {
            throw new RuntimeException("IntcodeComputer is already running, cannot resume.");
        }
        halted = false;
        run();
    }

    public void resumeWithInputs(long input) {
        if (!halted & executing) {
            throw new RuntimeException("IntcodeComputer is already running, cannot resume.");
        }
        halted = false;

        log("RESUMING at instruction pointer: " + index);

        runWithInputs(input);
    }

    public void runWithInputs(long... inputs) {
        //  Set aside original input stream
        InputStream originalSystemIn = System.in;
        try {
            StringBuilder sb = new StringBuilder();
            for (long input : inputs) {
                sb.append(input).append(System.getProperty("line.separator"));
            }

            //  Setup input stream to feed program input
            InputStream testInput = new ByteArrayInputStream(sb.toString().getBytes());
            System.setIn(testInput);

            run();
        } finally {
            System.setIn(originalSystemIn);
        }
    }

    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (executing && !halted) {

            String opcodeAndMode = "";
            //  Switch on opcode
            long opcode = (int) register[(int) index];
            opcodeAndMode += opcode;
            long modes = 0;

            if (opcode > 99) {
//                System.out.println("We got parameter modes: " + opcode);
                long actualOpCode = opcode % 100;
//                System.out.println("Actual op code: " + actualOpCode);
                modes = opcode / 100;
//                System.out.println("Remainder: " + modes);
                opcode = actualOpCode;
                opcodeAndMode += "  modes: " + modes;
            }
            log("opcode: " + opcodeAndMode);

            switch ((int) opcode) {

                case 1:
                    //  OPCODE 1 -- ADD, 3 args
                    long addArgOne = parseArgument(index, 1, modes);
                    long addArgTwo = parseArgument(index, 2, modes);
//                    System.out.println("ADD  " + addArgOne + "  " + addArgTwo);
                    register[(int) register[(int) (index + 3)]] = addArgOne + addArgTwo;
                    index += 4;
                    continue;

                case 2:
                    //  OPCODE 2 -- MULTIPLY, 3 args
                    long multArgOne = parseArgument(index, 1, modes);
                    long multArgTwo = parseArgument(index, 2, modes);
//                    System.out.println("MULTIPLY  " + multArgOne + "  " + multArgTwo);
                    register[(int) register[(int) (index + 3)]] = multArgOne * multArgTwo;
                    index += 4;
                    continue;

                case 3:
                    //  OPCODE 3 -- SAVE INPUT, 1 arg
//                    System.out.println("SAVE INPUT");
//                    System.out.println("Papers, please: ");
                    long input = -1;
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                    } catch (NoSuchElementException e) {
                        //  If there's no element, halt the computer and save state.
                        log("HALTING at instruction pointer: " + index);
                        halted = true;
                        break;
                    }
//                    scanner.close();
                    register[(int) register[(int) (index + 1)]] = input;
                    index += 2;
                    continue;

                case 4:
                    //  OPCODE 4 -- OUTPUT, 1 arg
//                    long outValue = register[register[index + 1]];
                    long outValue = parseArgument(index, 1, modes);
                    outputs.add(outValue);
//                    System.out.println("OUTPUT  " + outValue);
//                    if (isImmediateMode(1, modes)) {
//                        System.out.println("Output (immediate): ");
//                        System.exit(0);
//                    }
                    index += 2;
                    continue;

                case 5:
                    //  OPCODE 5 -- JUMP-IF-TRUE
                    long jumpIfTrueArgOneValue = parseArgument(index, 1, modes);
                    long jumpIfTrueArgTwoValue = parseArgument(index, 2, modes);
//                    System.out.println("JUMP-IF-TRUE  " + jumpIfTrueArgOneValue + "  " + jumpIfTrueArgTwoValue);
                    if (jumpIfTrueArgOneValue != 0) {

                        index = jumpIfTrueArgTwoValue;
                    } else {
                        //  No Jump if zero
                        index += 3;
                    }
                    continue;

                case 6:
                    //  OPCODE 6 -- JUMP-IF-FALSE
                    long jumpIfFalseArgOneValue = parseArgument(index, 1, modes);
                    long jumpIfFalseArgTwoValue = parseArgument(index, 2, modes);
//                    System.out.println("JUMP-IF-FALSE  " + jumpIfFalseArgOneValue + "  " + jumpIfFalseArgTwoValue);
                    if (jumpIfFalseArgOneValue == 0) {
                        //  If the first parameter is zero, set the instruction polong to the second arg value.
                        index = jumpIfFalseArgTwoValue;
                    } else {
                        //  No Jump if non-zero
                        index += 3;
                    }
                    continue;

                case 7:
                    //  OPCODE 7 -- LESS-THAN
                    long lessThanArgOneValue = parseArgument(index, 1, modes);
                    long lessThanArgTwoValue = parseArgument(index, 2, modes);
//                    System.out.println("LESS-THAN  " + lessThanArgOneValue + "  " + lessThanArgTwoValue);
                    long lessThanDest = register[(int) (index + 3)];
                    if (lessThanArgOneValue < lessThanArgTwoValue) {
                        register[(int) lessThanDest] = 1;
                    } else {
                        register[(int) lessThanDest] = 0;
                    }
                    index += 4;
                    continue;

                case 8:
                    //  OPCODE 8 -- EQUALS
                    long equalsArgOneValue = parseArgument(index, 1, modes);
                    long equalsArgTwoValue = parseArgument(index, 2, modes);
//                    System.out.println("EQUALS  " + equalsArgOneValue + "  " + equalsArgTwoValue);
                    long equalsDestArg = register[(int) (index + 3)];
                    if (equalsArgOneValue == equalsArgTwoValue) {
                        register[(int) equalsDestArg] = 1;
                    } else {
                        register[(int) equalsDestArg] = 0;
                    }
                    index += 4;
                    continue;

                case 99:
                    //  OPCODE 99 -- END PROGRAM
//                    System.out.println("99 -- END PROGRAM");
                    executing = false;
                    halted = true;
                    continue;

                default:
                    System.out.println("Unknown opcode: " + register[(int) index]);
                    System.exit(0);
            }
        }
    }

    //  TODO -- refactor so it parses the mode for the index, then identifies the  mode.
    //   Throw illegal arg exception if unknown mode encountered.
    public long parseArgument(long opcodeIndex, long argIndex, long modes) {
        if (isImmediateMode(argIndex, modes)) {
//            System.out.println("\targ " + argIndex + " is immediate");
            //  If mode is ONE then execute in IMMEDIATE MODE (value of arg_value)
            return register[(int) (opcodeIndex + argIndex)];
        } else {
//            System.out.println("\targ " + argIndex + " is position");
            //  If mode is ZERO then execute in POSITION MODE (value is register[arg_value]
            return register[(int) register[(int) (opcodeIndex + argIndex)]];
        }
    }

    /**
     * Position mode is mode 0
     * Immediate mode is mode 1
     *
     * @param argIndex
     * @param modes
     * @return
     */
    public boolean isImmediateMode(long argIndex, long modes) {
        if (modes == 0) {
            return false;
        }

        if (modes > 0) {
            //  Check for possibility of immediate mode.

            String modeString = Long.toString(modes);
            //  If the argIndex is greater than the mode string, then assume zero -- immediate mode.
            if (argIndex <= modeString.length()) {
                //  Otherwise the mode string falls within our bounds.
                modeString = StringUtils.reverse(modeString);
                long mode = Long.parseLong("" + modeString.charAt((int) (argIndex - 1)));
//                System.out.println("Mode Int: " + mode);
                if (mode == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void log(String msg) {
        System.out.println("computer " + id + " " + msg);
    }

    public boolean isHalted() {
        return halted;
    }

    public boolean isExecuting() {
        return executing;
    }

    public long valueAtIndex(long index) {
        return register[(int) index];
    }

    public ArrayList<Long> getOutputs() {
        return outputs;
    }

    public long getLastOutput() {
        return outputs.get(outputs.size() - 1);
    }
}
