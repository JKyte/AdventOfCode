package advent2020.day11;

public class SeatingSystem {

    private char[][] layout;
    private int round = 0;

    public SeatingSystem() {

    }

    public void loadInput(String input) {
        String[] lines = input.split("\n");
        layout = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            layout[i] = lines[i].toCharArray();
        }
    }

    public int applyRules() {
        int filled = 0;
        char[][] current = deepCopy(layout);

        //  Apply the rules
        boolean applying = true;
        while (applying) {
            round++;
            //  Operate on a copy
            char[][] next = deepCopy(current);

            //  Apply rules to entire copy
            applyRules(current, next);

            if (layoutChanged(current, next)) {
                current = next;
            } else {
                applying = false;
                continue;
            }
            //  Log map after rules applied
            filled = countFilledSeats(current);
            System.out.println("round: " + round + " filled seats: " + filled);
//            print(copy);
//            if (round > 5) break;
        }
        return filled;
    }

    public void applyRules(char[][] current, char[][] next){
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[0].length; j++) {
                char c = next[i][j];
                if (c == 'L' && allAdjacentSeatsEmpty(current, i, j)) {
                    next[i][j] = '#';
                }
                if (c == '#' && fourAdjacentSeatsFilled(current, i, j)) {
                    next[i][j] = 'L';
                }
            }
        }
    }

    private boolean allAdjacentSeatsEmpty(char[][] a, int i, int j) {
        int iStart = Math.max(i - 1, 0);
        int iStop = Math.min(i + 1, a.length - 1);
        int jStart = Math.max(j - 1, 0);
        int jStop = Math.min(j + 1, a.length - 1);
        for (int ii = iStart; ii <= iStop; ii++) {
            for (int jj = jStart; jj <= jStop; jj++) {
                if (ii == i && jj == j) continue;
                if (a[ii][jj] == '#') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fourAdjacentSeatsFilled(char[][] a, int i, int j) {
        int iStart = Math.max(i - 1, 0);
        int iStop = Math.min(i + 1, a.length - 1);
        int jStart = Math.max(j - 1, 0);
        int jStop = Math.min(j + 1, a.length - 1);
        int filled = 0;
        for (int ii = iStart; ii <= iStop; ii++) {
            for (int jj = jStart; jj <= jStop; jj++) {
                if (ii == i && jj == j) continue;
                if (a[ii][jj] == '#') filled++;
            }
        }
        return filled >= 4;
    }

    private boolean layoutChanged(char[][] a, char[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] != b[i][j]) return true;
            }
        }
        return false;
    }

    public int countFilledSeats(char[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == '#') count++;
            }
        }
        return count;
    }

    private char[][] deepCopy(char[][] a) {
        char[][] b = new char[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, b[i], 0, a[0].length);
        }
        return b;
    }

    public void print() {
        print(layout);
    }

    public void print(char[][] a) {
        for (char[] chars : a) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < a[0].length; j++) {
                sb.append(chars[j]);
            }
            System.out.println(sb.toString());
        }
        System.out.println("----------");
    }

    public void printSubset(char[][] a, int iStart, int iStop, int jStart, int jStop) {
        for (int ii = iStart; ii <= iStop; ii++) {
            StringBuilder sb = new StringBuilder();
            for (int jj = jStart; jj <= jStop; jj++) {
                sb.append(a[ii][jj]);
            }
            System.out.println(sb.toString());
        }
        System.out.println("----------");
    }
}
