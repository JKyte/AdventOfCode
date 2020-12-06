package advent2016.day03;

import org.apache.commons.lang3.StringUtils;

/**
 * Advent 2016, Day 3
 */
public class ThreeSidedSquares {

    public ThreeSidedSquares() {
    }

    public int countTriangles(String input) {
        return countTriangles(StringUtils.split(input, '\n'));
    }

    private int countTriangles(String[] inputs) {
        int count = 0;
        for (String input : inputs) {
            if (isTriangle(input)) {
                count++;
            }
        }
        return count;
    }

    private boolean isTriangle(String input) {
        String[] parts = StringUtils.split(input, ' ');
        return isTriangle(parts[0], parts[1], parts[2]);
    }

    private boolean isTriangle(String a, String b, String c) {
        return isTriangle(Integer.parseInt(a), Integer.parseInt(b), Integer.parseInt(c));
    }

    //  Sum of any two sides must be greater than remaining side
    private boolean isTriangle(int a, int b, int c) {
        return (a < b + c) && (b < a + c) && (c < a + b);
    }

    public int countVerticalTriangles(String input) {
        return countVerticalTriangles(StringUtils.split(input, '\n'));
    }

    private int countVerticalTriangles(String[] inputs) {
        int count = 0;
        for (int i = 0; i < inputs.length; i += 3) {
            String input = inputs[i] + " " + inputs[i + 1] + " " + inputs[i + 2];
            String[] parts = StringUtils.split(input, ' ');

            // traverse the flattened 3x3 matrix
            for (int j = 0; j < parts.length / 3; j++) {
                if (isTriangle(parts[j], parts[j + 3], parts[j + 6])) {
                    count++;
                }
            }
        }
        return count;
    }
}
