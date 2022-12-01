package advent2022.day01;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCounting {

    private final List<Integer> elves;
    private int maxIndex;
    private int maxCalorie;

    public CalorieCounting() {
        elves = new ArrayList<>();
        maxIndex = 0;
        maxCalorie = 0;
    }

    public void consumeInputs(List<String> inputs) {
        int count = 0;
        for (String input : inputs) {
            if (StringUtils.isNotBlank(input)) {
                int calorie = Integer.parseInt(input);
                count += calorie;
            } else {
                elves.add(count);
                count = 0;
            }
        }

        if (count > 0) {
            elves.add(count);
        }
    }

    public void findTheElfWithTheMost() {
        for (int i = 0; i < elves.size(); i++) {
            Integer elf = elves.get(i);
            if (elf >= maxCalorie) {
                maxCalorie = elf;
                maxIndex = i + 1;
            }
        }
    }

    public int getMaxCalorie() {
        return this.maxCalorie;
    }

    public int getMaxIndex() {
        return this.maxIndex;
    }

    public int sumTopThree() {
        Collections.sort(elves);
        Collections.reverse(elves);

        return elves.get(0) + elves.get(1) + elves.get(2);
    }
}
