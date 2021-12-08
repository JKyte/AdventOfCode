package advent2021.day07;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import core.MathUtil;

import java.util.Set;

public class WhaleTreachery {

    private int max = Integer.MIN_VALUE;
    private Multiset<Integer> crabs = HashMultiset.create();

    private int minFuelCost = Integer.MAX_VALUE;

    public WhaleTreachery() {
    }

    public WhaleTreachery givenInput(String input) {
        int i;
        String[] parts = input.split(",");
        for (String part : parts) {
            i = Integer.parseInt(part);
            crabs.add(i);
            if (i > max) {
                max = i;
            }
        }
        return this;
    }

    public WhaleTreachery calculateFuel() {
        for (int i = 0; i <= max; i++) {
            int fuelSpent = 0;
            Set<Integer> elements = crabs.elementSet();
            for (int element : elements) {
                int mult = crabs.count(element);
                int delta = Math.abs(i - element);
                fuelSpent += (mult * delta);
            }

            if (fuelSpent < minFuelCost) {
                minFuelCost = fuelSpent;
            }
        }
        return this;
    }

    public WhaleTreachery factorialFuel() {
        for (int i = 0; i <= max; i++) {
            int fuelSpent = 0;
            Set<Integer> elements = crabs.elementSet();
            for (int element : elements) {
                int mult = crabs.count(element);
                int delta = Math.abs(i - element);
                fuelSpent += (mult * MathUtil.summation(delta));
            }

            if (fuelSpent < minFuelCost) {
                minFuelCost = fuelSpent;
            }
        }
        return this;
    }

    public int getCost() {
        return minFuelCost;
    }
}
