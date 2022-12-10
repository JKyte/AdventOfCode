package advent2022.day04;

import java.util.List;

import static core.Constants.COMMA_CHAR;
import static core.Constants.DASH_CHAR;

public class CampCleanup {

    public CampCleanup() {
        //  empty constructor
    }

    public int countPairs(List<String> pairs) {
        return countPairs(pairs, false);
    }

    public int countPairs(List<String> pairs, boolean anyOverlap) {
        int count = 0;
        for (String pair : pairs) {
            count += countFullOverlap(pair, anyOverlap);
        }
        return count;
    }

    private int countFullOverlap(String pair, boolean anyOverlap) {
        int index = pair.indexOf(COMMA_CHAR);
        String left = pair.substring(0, index);
        String right = pair.substring(index + 1);

        index = left.indexOf(DASH_CHAR);
        int x1 = Integer.parseInt(left.substring(0, index));
        int y1 = Integer.parseInt(left.substring(index + 1));

        index = right.indexOf(DASH_CHAR);
        int x2 = Integer.parseInt(right.substring(0, index));
        int y2 = Integer.parseInt(right.substring(index + 1));

        if (anyOverlap) {
            return countAnyOverlap(x1, y1, x2, y2);
        } else {
            return countFullOverlap(x1, y1, x2, y2);
        }
    }

    //  had to look up another answer to see what they were looking for
    //
    private int countAnyOverlap(int x1, int y1, int x2, int y2) {
        if ((x1 <= x2 && x2 <= y1) || (x2 <= x1 && x1 <= y2)) {
            return 1;
        }
        return 0;
    }

    private int countFullOverlap(int x1, int y1, int x2, int y2) {
        if ((x1 >= x2 && y1 <= y2) || (x1 <= x2 && y1 >= y2)) {
            return 1;
        }
        return 0;
    }
}
