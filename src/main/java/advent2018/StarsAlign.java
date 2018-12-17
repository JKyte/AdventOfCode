package advent2018;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import core.Core;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution for https://adventofcode.com/2018/day/10
 * <p>
 * Part 1: AJZNXHKE
 * <p>
 * Part 2: 10905
 */
public class StarsAlign extends Core {

    public static void main(String[] args) {
        StarsAlign sa = new StarsAlign();
        sa.run();
    }

    public StarsAlign() {
        this.inputPath += "advent2018/StarsAlign.txt";
    }

    @Override
    public void run() {
        List<String> lines = readLines();
        List<int[]> positions = linesToPositionVelocity(lines);
        simulateTime(positions);
    }

    private List<int[]> linesToPositionVelocity(List<String> lines) {
        List<int[]> positionVelocity = new ArrayList<>();
        for (String line : lines) {
            line = line.substring(line.indexOf("<"));
            line = line.replace("velocity=", ",");
            line = line.replaceAll(" ", "");
            line = line.replaceAll("<", "");
            line = line.replaceAll(">", "");
//            System.out.println("Line: " + line);
            String[] parts = line.split(",");
            int[] element = new int[4];
            for (int i = 0; i < parts.length; i++) {
                element[i] = Integer.parseInt(parts[i]);
            }
            positionVelocity.add(element);
//            System.out.println(" to line: " + line);
        }
        return positionVelocity;
    }

    private void simulateTime(List<int[]> positions) {
        int time = 11000;
//        int time = 5;
        List<int[]> curPositions = new ArrayList<>(positions);
        for (int ii = 0; ii < time; ii++) {
            printPositions(curPositions, ii);
            curPositions = applyVelocities(curPositions);
//            System.exit(0);
        }
    }

    private void printPositions(List<int[]> curPositions, int time) {
        if (!containsLines(curPositions)) {
            return;
        }
        int minX = 0, maxX = 0;
        int minY = 0, maxY = 0;
        for (int[] pos : curPositions) {
            if (pos[0] < minX)
                minX = pos[0];
            if (pos[0] > maxX)
                maxX = pos[0];
            if (pos[1] < minY)
                minY = pos[1];
            if (pos[1] > maxY)
                maxY = pos[1];
        }
//        System.out.println("min x: " + minX + " max x: " + maxX + " min y: " + minY + " max y: " + maxY);
        if (minX < 0)
            minX = Math.abs(minX) + 1;  //  Account for 0
        if (minY < 0)
            minY = Math.abs(minY) + 1;

        int x = minX + maxX + 1;
        int y = minY + maxY + 1;

//        System.out.println("min x: " + minX + " max x: " + maxX + " min y: " + minY + " max y: " + maxY);
        System.out.println("x: " + x + " y: " + y);
        if (x > 500 && y > 500) {
            return;
        }
        char[][] view = createBlankSky(x, y);
        int placed = 0;
        for (int[] pos : curPositions) {
            placed++;
//            System.out.println("Placing " + printIntArray(pos));
            if (pos[0] < 0 && pos[1] < 0) {
                view[minX + pos[0]][minY + pos[1]] = '#';
            } else if (pos[0] < 0) {
                view[minX + pos[0]][pos[1]] = '#';
            } else if (pos[1] < 0) {
                view[pos[0]][minY + pos[1]] = '#';
            } else {
                view[pos[0]][pos[1]] = '#';
            }
        }

        System.out.println("Stars at time: " + time);
//        System.out.println("Placed: " + placed + "/" + curPositions.size());
        printSky(view);
        System.out.println("Printed stars at time: " + time);
    }

    private boolean containsLines(List<int[]> positions) {
        Multimap<Integer, Integer> xCounts = ArrayListMultimap.create();
        Multimap<Integer, Integer> yCounts = ArrayListMultimap.create();
        for (int[] position : positions) {
            xCounts.put(position[0], position[0]);
            yCounts.put(position[1], position[1]);
        }
//        System.out.println("=========");
        boolean xLine = false;
        boolean yLine = false;
        for (int key : xCounts.keySet()) {
//            System.out.println("x K: " + key + " V: " + xCounts.get(key).size());
            if (xCounts.get(key).size() >= 20) { //  8
                xLine = true;
                break;
            }
        }
        for (int key : yCounts.keySet()) {
//            System.out.println("y K: " + key + " V: " + yCounts.get(key).size());
            if (yCounts.get(key).size() >= 5) { //  5
                yLine = true;
                break;
            }
        }

        if (yLine && xLine) {
            System.out.println("Found lines!");
            return true;
        }
        return false;
    }

    private char[][] createBlankSky(int x, int y) {
        char[][] view = new char[x][y];
        for (int ii = 0; ii < x; ii++) {
            for (int jj = 0; jj < y; jj++) {
                view[ii][jj] = '.';
            }
        }
        return view;
    }

    private void printSky(char[][] sky) {
        for (int jj = 0; jj < sky[0].length; jj++) {
            StringBuilder sb = new StringBuilder();
            for (int ii = 0; ii < sky.length; ii++) {
                sb.append(sky[ii][jj]);
            }
            System.out.println(sb.toString());
        }
    }

    private List<int[]> applyVelocities(List<int[]> curPositions) {
        List<int[]> nextPositions = new ArrayList<>();
        for (int[] curPos : curPositions) {
//            System.out.println("Before: " + printIntArray(curPos));
            int[] next = new int[4];
            next[0] = curPos[0] + curPos[2];
            next[1] = curPos[1] + curPos[3];
            next[2] = curPos[2];
            next[3] = curPos[3];
            nextPositions.add(next);
//            System.out.println("After: " + printIntArray(next));
        }
        return nextPositions;
    }

    private String printIntArray(int[] pos) {
        return pos[0] + " " + pos[1] + " " + pos[2] + " " + pos[3];
    }
}
