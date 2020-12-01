package advent2020.day01;

import org.apache.commons.lang3.StringUtils;

public class ReportRepair {

    int[] expenses;

    public ReportRepair(String input) {
        expenses = parseInput(input);
    }

    private int[] parseInput(String input) {
        String[] parts = StringUtils.split(input, '\n');
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }

    public int repair() {
        for (int i = 0; i < expenses.length; i++) {
            for (int j = 0; j < expenses.length; j++) {
                //  Skip same element comparison
                if (i == j) continue;

                if (expenses[i] + expenses[j] == 2020) {
                    return expenses[i] * expenses[j];
                }
            }
        }
        return -1;
    }

    public int repair3() {
        for (int i = 0; i < expenses.length; i++) {
            for (int j = 0; j < expenses.length; j++) {
                //  Skip same element comparison
                if (i == j) continue;

                for (int k = 0; k < expenses.length; k++) {

                    //  Skip same element comparison
                    if (j == k) continue;

                    if (expenses[i] + expenses[j] + expenses[k] == 2020) {
                        return expenses[i] * expenses[j] * expenses[k];
                    }
                }
            }
        }
        return -1;
    }
}
