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
        this.register = Arrays.copyOf(register, 4096);
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

//        log("RESUMING at instruction pointer: " + index);

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
//            log("opcode: " + opcodeAndMode);

            switch ((int) opcode) {

                case 1:
                    //  OPCODE 1 -- ADD, 3 args
                    long addArgOne = parseArgument(index, 1, modes);
                    long addArgTwo = parseArgument(index, 2, modes);
//                    long addDest = parseArgument(index, 3, modes);
//                    System.out.println("ADD  " + addArgOne + "  " + addArgTwo);
                    long addMode = getModeForIndex(3, modes);
                    if (addMode == 2) {
                        register[(int) (register[(int) (index + 3)] + relativeBase)] = addArgOne + addArgTwo;
//                        System.exit(0);
                    } else {
                        //  This is effectively position mode. store result in position of third arg.
                        register[(int) register[(int) (index + 3)]] = addArgOne + addArgTwo;
                    }
                    index += 4;
                    continue;

                case 2:
                    //  OPCODE 2 -- MULTIPLY, 3 args
                    long multArgOne = parseArgument(index, 1, modes);
                    long multArgTwo = parseArgument(index, 2, modes);
                    long multMode = getModeForIndex(3, modes);
                    long multDest = -1L;
                    if (multMode == 2) {
                        //  Handle relative mode.
                        multDest = register[(int) (index + 3)] + relativeBase;
                    } else {
                        //  Default to position mode
                        multDest = register[(int) (index + 3)];
                    }

//                    System.out.println("MULTIPLY  " + multArgOne + "  " + multArgTwo);
                    register[(int) multDest] = multArgOne * multArgTwo;
                    index += 4;
                    continue;

                case 3:
                    //  OPCODE 3 -- SAVE INPUT, 1 arg
                    //  Save input to the position given by its only parameter.

//                    System.out.println("SAVE INPUT");
//                    System.out.println("Papers, please: ");
                    long input = -1;
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                    } catch (NoSuchElementException e) {
                        //  If there's no element, halt the computer and save state.
//                        log("HALTING at instruction pointer: " + index);
                        halted = true;
                        break;
                    }
                    int saveMode = getModeForIndex(1, modes);
                    if (saveMode == 0) {
                        register[(int) register[(int) (index + 1)]] = input;
                    } else if (saveMode == 2) {
                        long position = parseArgument(index, 1, modes);
                        register[(int) (relativeBase + register[(int) (index + 1)])] = input;
                    } else {
                        System.out.println("something went wrong trying to save input.");
                        System.exit(0);
                    }
                    index += 2;
                    continue;

                case 4:
                    //  OPCODE 4 -- OUTPUT, 1 arg
                    long outValue = parseArgument(index, 1, modes);
                    outputs.add(outValue);
                    index += 2;
                    continue;

                case 5:
                    //  OPCODE 5 -- JUMP-IF-TRUE
                    long jumpIfTrueArgOneValue = parseArgument(index, 1, modes);
                    long jumpIfTrueArgTwoValue = parseArgument(index, 2, modes);
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
                    if (jumpIfFalseArgOneValue == 0) {
                        //  If the first parameter is zero, set the instruction to the second arg value.
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
                    long lessMode = getModeForIndex(3, modes);

                    long lessThanDest = -1L;
                    if (lessMode == 2) {
                        //  Handle relative base for destinations.
                        lessThanDest = register[(int) (index + 3)] + relativeBase;
                    } else {
                        //  Default is position mode. Destinations can never be immediate.
                        lessThanDest = register[(int) (index + 3)];
                    }
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
                    long equalsDestArg = -1L;
                    long eqMode = getModeForIndex(3, modes);
                    if (eqMode == 2) {
                        equalsDestArg = register[(int) (index + 3)] + relativeBase;
                    } else {
                        equalsDestArg = register[(int) (index + 3)];
                    }

                    if (equalsArgOneValue == equalsArgTwoValue) {
                        register[(int) equalsDestArg] = 1;
                    } else {
                        register[(int) equalsDestArg] = 0;
                    }
                    index += 4;
                    continue;

                case 9:
                    //  OPCODE 9 -- ADJUST RELATIVE BASE
                    //  Adjusts the relative base by the value of it's only parameter.
                    long adjustArgOneValue = parseArgument(index, 1, modes);
                    relativeBase += adjustArgOneValue;
                    index += 2;
                    continue;

                case 99:
                    //  OPCODE 99 -- END PROGRAM
                    executing = false;
                    halted = true;
                    continue;

                default:
                    System.out.println("Unknown opcode: " + register[(int) index]);
                    System.exit(0);
            }
        }
    }

    public long parseArgument(long opcodeIndex, long argIndex, long modes) {
//        log("parseArg opcodeIndex: " + opcodeIndex + " argIndex: " + argIndex + " modes: " + modes);
        long mode = getModeForIndex(argIndex, modes);
//        log("parsedMode: " + mode);

        switch ((int) mode) {
            case 0:
                //  ZERO is POSITION MODE. Argument value is at register[arg_value]

//            System.out.println("\targ " + argIndex + " is position");
                //  If mode is ZERO then execute in POSITION MODE (value is register[arg_value]
                return register[(int) register[(int) (opcodeIndex + argIndex)]];
            case 1:
                //  ONE is IMMEDIATE MODE. Argument value is the actual arg_value.
                //            System.out.println("\targ " + argIndex + " is immediate");
                //  If mode is ONE then execute in IMMEDIATE MODE (value of arg_value)
                return register[(int) (opcodeIndex + argIndex)];

            case 2:
                //  TWO is RELATIVE MODE. Argument value is relativeBase + the value of the arg.
                return register[(int) (relativeBase + register[(int) (opcodeIndex + argIndex)])];

            default:
                System.out.println("Unknown mode encountered, index " + argIndex + " for modes " + modes);
                System.exit(0);
                return -1L;
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

    public int getModeForIndex(long index, long modes) {
        if (modes == 0)
            return 0;
        String modeString = Long.toString(modes);
        //  Arguments out of bounds are assumed to be zero.
        if (index > modeString.length())
            return 0;
        int modeIndex = (int) (modeString.length() - index);
        long mode = Long.parseLong("" + modeString.charAt(modeIndex));
        return (int) mode;
    }

    public void log(String msg) {
        System.out.println("computer " + id + " " + msg);
    }

    public void setValueAtIndex(int index, long value) {
        register[index] = value;
    }

    public long getValueAtIndex(int index) {
        return register[index];
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

    public void clearOutputs() {
        this.outputs.clear();
    }

    public long getRelativeBase() {
        return relativeBase;
    }

    public void setRelativeBase(long relativeBase) {
        this.relativeBase = relativeBase;
    }
}
