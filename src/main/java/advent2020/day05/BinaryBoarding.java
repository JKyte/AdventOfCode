package advent2020.day05;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.TreeSet;

public class BinaryBoarding {

    public BinaryBoarding() {
    }

    public int getMaxSeatId(String input) {
        return getMaxSeatId(StringUtils.split(input, '\n'));
    }

    public int getMaxSeatId(String[] input) {
        int max = 0;
        for (String s : input) {
            int id = getSeatId(s);
            if (id > max) {
                max = id;
            }
        }
        return max;
    }

    public int getSeatId(String input) {
        int row = getRow(input);
        int column = getColumn(input);
        return (row * 8) + column;
    }

    public int getRow(String input) {
        return getBinaryFromString(input, 0, 7);
    }

    public int getColumn(String input) {
        return getBinaryFromString(input, 7, 10);
    }

    private int getBinaryFromString(String s, int start, int stop) {
//        System.out.println("Examining: " + s.substring(start, stop));
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < stop; i++) {
            if (s.charAt(i) == 'F' || s.charAt(i) == 'L') {
                sb.append("0");
            } else {
                sb.append("1");
            }
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    public int findMySeatId(String input) {
        return findMySeatId(StringUtils.split(input, '\n'));
    }

    public int findMySeatId(String[] input) {
        TreeSet<Integer> sorted = new TreeSet<>();
        for (String s : input) {
            int id = getSeatId(s);
            sorted.add(id);
        }

        Iterator<Integer> iter = sorted.descendingIterator();
        int mySeatId = -1;
        int prev = iter.next();
        while (iter.hasNext()) {
            int cur = iter.next();
            if (prev - cur > 1) {
//                System.out.println("Found it between " + prev + " and " + cur);
                mySeatId = prev - 1;
            } else {
//                System.out.println(cur);
            }
            prev = cur;
        }
        return mySeatId;
    }
}
