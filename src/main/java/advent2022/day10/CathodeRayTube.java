package advent2022.day10;

import java.util.ArrayList;
import java.util.List;

import static core.Constants.SPACE;


public class CathodeRayTube {

    private int cycle = 0;
    private int registerX = 1;

    
    private final List<Integer> signals;

    private final String NOOP = "noop";
    private final String ADD_X = "addx";

    public CathodeRayTube() {
        this.signals = new ArrayList<>();
        this.signals.add(1);
    }

    public void withInput(List<String> inputs) {
        for (String input : inputs) {
            process(input);
        }
    }

    private void process(String input) {
        if (input.equals(NOOP)) {
            processNoOp();
        } else if (input.startsWith(ADD_X)) {
            processAddX(input);
        } else {
            throw new IllegalArgumentException("Unexpected cmd: " + input);
        }
    }

    private void processNoOp() {
        cycle++;
        addSignalStrength();
    }

    private void processAddX(String cmd) {
        cycle++;
        addSignalStrength();
        cycle++;
        int index = cmd.indexOf(SPACE) + 1;
        int value = Integer.parseInt(cmd.substring(index));
        registerX += value;
        addSignalStrength();
    }

    private void addSignalStrength() {
        signals.add(getSignalStrength());
    }

    public int getSignalStrength() {
//        System.out.println("cycle: " + cycle + " = " + registerX);
        return cycle * registerX;
    }

    public int getRegisterX() {
        return registerX;
    }

    public int getSignalStrength(int n) {
        return signals.get(n);
    }

    public int sumSignals(int... ns) {
        int sum = 0;
        for (int n : ns) {
            sum += signals.get(n);
        }
        return sum;
    }
}
