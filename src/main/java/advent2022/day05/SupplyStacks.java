package advent2022.day05;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static core.Constants.SPACE;

public class SupplyStacks {

    private boolean partTwo = false;
    private List<Stack<String>> stacks;
    private final Stack<String> partTwoStack = new Stack<>();

    public SupplyStacks() {
        //  empty constructor
    }

    public void givenInput(List<String> inputs) {
        int blankIndex = findBlankIndex(inputs);
        String stackLine = inputs.get(blankIndex - 1);
        createStacks(stackLine);
        fillStacks(inputs, blankIndex - 1);
        processMoves(inputs, blankIndex + 1);
    }

    private int findBlankIndex(List<String> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            if (StringUtils.isBlank(inputs.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void createStacks(String stackLine) {
        stackLine = stackLine.substring(1);
        stackLine = stackLine.replace("   ", " ");
        String[] parts = stackLine.split(SPACE);
        stacks = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            stacks.add(new Stack<>());
        }
    }

    private void fillStacks(List<String> inputs, int stop) {
        for (int i = stop - 1; i >= 0; i--) {
            String input = inputs.get(i);
            processLine(input);
        }
    }

    private void processLine(String line) {
        List<String> crates = new ArrayList<>();
        for (int j = 1; j < line.length(); j += 4) {
            String s = "" + line.charAt(j);
            crates.add(s);
        }

        for (int i = 0; i < crates.size(); i++) {
            String crate = crates.get(i);

            if (StringUtils.isNotBlank(crates.get(i))) {
                stacks.get(i).push(crate);
            }
        }
    }

    private void processMoves(List<String> inputs, int start) {
        for (int i = start; i < inputs.size(); i++) {
            String input = inputs.get(i);
            String[] parts = input.split(SPACE);
            int a = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[3]);
            int c = Integer.parseInt(parts[5]);
            processMove(a, b, c);
        }
    }

    private void processMove(int a, int b, int c) {
        if (partTwo) {
            for (int i = 0; i < a; i++) {
                if (!stacks.get(b - 1).isEmpty()) {
                    String crate = stacks.get(b - 1).pop();
                    partTwoStack.push(crate);
                }
            }

            while (!partTwoStack.isEmpty()) {
                stacks.get(c - 1).push(partTwoStack.pop());
            }
        } else {
            for (int i = 0; i < a; i++) {
                if (!stacks.get(b - 1).isEmpty()) {
                    String crate = stacks.get(b - 1).pop();
                    stacks.get(c - 1).push(crate);
                }
            }
        }
    }

    public String reportTopStacks() {
        StringBuilder sb = new StringBuilder();
        for (Stack<String> stack : stacks) {
            sb.append(stack.peek());
        }
        return sb.toString().replace("[", "").replace("]", "");
    }

    public void setPartTwo() {
        this.partTwo = true;
    }
}
