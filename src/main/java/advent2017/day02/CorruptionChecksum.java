package advent2017.day02;

import java.util.ArrayList;
import java.util.List;

public class CorruptionChecksum {

    private List<int[]> sheet;

    public CorruptionChecksum(String input) {
        sheet = inputToSheet(input);
    }

    private List<int[]> inputToSheet(String input) {
        String[] rows = input.split("\n");
        ArrayList<int[]> sheets = new ArrayList<>(rows.length);

        for (int i = 0; i < rows.length; i++) {
            String[] cells = rows[i].split(" ");

            int[] row = new int[cells.length];
            for (int j = 0; j < cells.length; j++) {
                row[j] = Integer.parseInt(cells[j]);
            }
            sheets.add(row);
        }
        return sheets;
    }

    public int checksumV1() {
        int sum = 0;
        for (int[] row : sheet) {
            sum += checksumV1(row);
        }
        return sum;
    }

    public int checksumV1(int[] ints) {
        int hi = Integer.MIN_VALUE;
        int lo = Integer.MAX_VALUE;
        for (int i : ints) {
            if (i > hi) hi = i;
            if (i < lo) lo = i;
        }
        return hi - lo;
    }

    public int checksumV2() {
        int sum = 0;
        for (int[] row : sheet) {
            sum += checksumV2(row);
        }
        return sum;
    }

    public int checksumV2(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {

                //  Skip math'ing ourselves
                if (i == j) continue;

                if (ints[i] % ints[j] == 0) {
                    return ints[i] / ints[j];
                }
            }
        }
        return -1;
    }
}
