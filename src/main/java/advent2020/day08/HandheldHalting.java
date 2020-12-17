package advent2020.day08;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HandheldHalting {

    private int pointer = 0;
    private int accumulator = 0;

    private boolean isExecuting = false;
    private int errorCode = 0;

    private ArrayList<String> instructions;
    private Set<Integer> history;

    public HandheldHalting() {
        this.history = new HashSet<>();
    }

    public HandheldHalting(HandheldHalting other) {
        this.pointer = other.pointer;
        this.accumulator = other.accumulator;
        this.isExecuting = other.isExecuting;
        this.instructions = other.instructions;
        this.history = other.history;
    }

    /**
     * Load instructions and prime for a run
     *
     * @param input
     */
    public void loadInstructions(String input) {
        this.pointer = 0;
        this.accumulator = 0;
        this.isExecuting = false;
        this.history.clear();
        this.instructions = new ArrayList<>();
        this.instructions.addAll(Arrays.asList(StringUtils.split(input, '\n')));
    }

    public void loadInstructions(ArrayList<String> input) {
        this.pointer = 0;
        this.accumulator = 0;
        this.isExecuting = false;
        this.history.clear();
        this.instructions = new ArrayList<>();
        this.instructions.addAll(input);
    }

    public ArrayList<ArrayList<String>> permuteInstructions() {
        ArrayList<ArrayList<String>> permutations = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            if (instruction.startsWith("jmp")) {
                ArrayList<String> copy = new ArrayList<>(instructions);
                copy.set(i, instruction.replace("jmp", "nop"));
                permutations.add(copy);


            } else if (instruction.startsWith("nop")) {
                ArrayList<String> copy = new ArrayList<>(instructions);
                copy.set(i, instruction.replace("nop", "jmp"));
                permutations.add(copy);
            }
        }
        return permutations;
    }

    public void runProgram() {
        isExecuting = true;
        boolean success;
        while (isExecuting) {
            success = executeInstruction();
            if (!success) {
                isExecuting = false;
            }
        }
    }

    private boolean executeInstruction() {

        //  Make sure the new pointer doesn't put us out of bounds
        if (pointer > instructions.size() - 1 || pointer < 0) {
            errorCode = 1;
//            System.out.println("Pointer out of bounds, exiting. " + accumulator);
            return false;
        }
        String instruction = instructions.get(pointer);

//        System.out.println("executing index -> " + pointer);
        if (history.contains(pointer)) {
            //  Program terminates due to loop detection.
            return false;
        }
        history.add(pointer);

        int index = instruction.indexOf(' ');
        String op = instruction.substring(0, index);
        int arg = Integer.parseInt(instruction.substring(index + 1));

        switch (op) {
            case "acc":
                accumulator += arg;
                pointer++;
                break;
            case "jmp":
                pointer += arg;
                break;
            case "nop":
                pointer++;
                break;
            default:
                System.err.println("Not yet implemented.");
        }

        if (pointer > instructions.size()) {
            isExecuting = false;
            errorCode = 1;  //  Error code 1 is pointer out of bounds exception
            return false;
        }
        return true;
    }

    public int getAccumulator() {
        return this.accumulator;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
