package advent2020.day03;

import org.apache.commons.lang3.StringUtils;

public class TobogganTrajectory {

    char[][] orig;

    public TobogganTrajectory(String input) {
        this.orig = inputToMap(input);
    }

    private char[][] inputToMap(String input) {
        String[] lines = StringUtils.split(input, '\n');
        char[][] map = new char[lines.length][];
        for (int i = 0, linesLength = lines.length; i < linesLength; i++) {
            map[i] = lines[i].toCharArray();
        }
        return map;
    }

    public int countTreesForSlope(int x, int y) {
        int numExpansions = getNumExpansions(orig, x, y);
        char[][] map = getExpandedMap(numExpansions);
//        printMap(map);
        return getTreesHit(map, x, y);
    }

    private int getNumExpansions(char[][] orig, int x, int y) {
        int currentWidth = orig[0].length;
        int totalWidthNeeded = orig.length * x;
        //  Copies needed is total width / current width
        return (totalWidthNeeded / currentWidth) + 1;
    }

    private char[][] getExpandedMap(int numExpansions) {
        char[][] map = new char[orig.length][];
        for (int i = 0; i < orig.length; i++) {
            map[i] = getExpandedRow(orig[i], numExpansions);
        }
        return map;
    }

    private char[] getExpandedRow(char[] orig, int numExpansions) {
        int origLen = orig.length;
        char[] row = new char[origLen * numExpansions];
        for (int i = 0; i < row.length; i++) {
            row[i] = orig[i % origLen];
        }
        return row;
    }

    private int getTreesHit(char[][] map, int x, int y) {
        int treesHit = 0;
        int curX = 0;
        int curY = 0;
        while (curY < map.length) {
            if (map[curY][curX] == '#') {
                treesHit++;
            }
            curX += x;
            curY += y;
        }
        return treesHit;
    }

    private void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j]).append(' ');
            }
            System.out.println(sb.toString());
        }
    }

}
