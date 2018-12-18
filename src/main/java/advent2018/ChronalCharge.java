package advent2018;

import core.Core;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * Solution for https://adventofcode.com/2018/day/11
 * <p>
 * Part 1: 21,22
 * <p>
 * Part 2: not 236,287,12 or 235,286,13
 * <p>
 * Should implement https://en.wikipedia.org/wiki/Summed-area_table
 */
public class ChronalCharge extends Core {

    public static void main(String[] args) {
        ChronalCharge cc = new ChronalCharge();
        cc.run();
    }

    Map<Point, Integer> points = new HashMap<>();
    Map<Point, Integer> summedArea = new HashMap<>();

//    int gridSerialNumber = 18;
//    int gridSerialNumber = 42;
    int gridSerialNumber = 7511;

    public ChronalCharge() {
        this.inputPath += "advent2018/ChronalCharge.txt";
    }

    @Override
    public void run() {
        int gridSize = 300;
        findPowerLevels(gridSize);
        sumAreas(gridSize);
        findLargestTotalPower(gridSize);
    }

    private void sumAreas(int gridSize) {
        System.out.println("Summing areas...");
        for (int ii = 0; ii <= gridSize; ii++) {
            for (int jj = 0; jj <= gridSize; jj++) {
                int sum = 0;
                for (int x = 0; x <= ii; x++) {
                    for (int y = 0; y <= jj; y++) {
                        sum += points.get(new Point(x, y));
                    }
                }
                summedArea.put(new Point(ii, jj), sum);
            }
            System.out.println("ii: " + ii);
        }
        System.out.println("Summing areas complete.");
    }

    public void findLargestTotalPower(int gridSize) {
        int[] best = new int[]{0, 0, 0, 0};
        int[] info;
        for (int ii = 1; ii <= gridSize; ii++) {
            info = findLargestTotalPower(gridSize, ii);
            if (info[0] > best[0]) {
                best = info;
            }
            System.out.println("Crunching: " + ii);
        }
        System.out.println("For grid serial number " + gridSerialNumber);
        System.out.println("Max power is " + best[0]);
        System.out.println("Found at: " + best[1] + "," + best[2] + " with square size " + best[3]);
    }

    public int[] findLargestTotalPower(int gridSize, int squareSize) {
        int maxPower = 0;
        int maxX = 0;
        int maxY = 0;
        int powerLevel = 0;
        for (int x = 0; x < gridSize - squareSize; x++) {
            for (int y = 0; y < gridSize - squareSize; y++) {
                powerLevel = findGridPowerLevel(x, y, squareSize);
                if (powerLevel > maxPower) {
                    maxPower = powerLevel;
                    maxX = x;
                    maxY = y;
                }
            }
        }
        return new int[]{maxPower, maxX, maxY, squareSize};
    }

    private int findGridPowerLevel(int gridX, int gridY, int squareSize) {
//        int sum = 0;
        int ax = gridX, ay = gridY;
        int bx = gridX + squareSize, by = gridY;
        int cx = gridX, cy = gridY + squareSize;
        int dx = gridX + squareSize, dy = gridY + squareSize;
        int a = summedArea.get(new Point(ax, ay));
        int b = summedArea.get(new Point(bx, by));
        int c = summedArea.get(new Point(cx, cy));
        int d = summedArea.get(new Point(dx, dy));
        return d + a - b - c;
//        for (int x = gridX; x < gridX + squareSize; x++) {
//            for (int y = gridY; y < gridY + squareSize; y++) {
//                sum += points.get(new Point(x, y));
//            }
//        }
//        return sum;
    }

    public void print5x5Grid(int maxX, int maxY) {
        if (maxX == 0 || maxY == 0) {
            System.err.println("Error: Tries to print 5x5 too close to the edge.");
            System.exit(-1);
        }
        for (int x = maxX - 1; x < maxX + 3; x++) {
            StringBuilder sb = new StringBuilder();
            for (int y = maxY - 1; y < maxY + 3; y++) {
                sb.append(points.get(new Point(x, y))).append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    public void findPowerLevels(int gridSize) {
        for (int y = 0; y <= gridSize; y++) {
            for (int x = 0; x <= gridSize; x++) {
                points.put(new Point(x, y), calculateCellPower(x, y));
            }
        }
    }

    public int calculateCellPower(int x, int y) {
        return calculateCellPower(x, y, gridSerialNumber);
    }

    public int calculateCellPower(int x, int y, int serialNumber) {
        long rackId = x + 10;
        long powerLevel = rackId * y;
        powerLevel += (long) serialNumber;
        powerLevel *= rackId;
        int hundredthDigit = extractHundredthDigit(powerLevel);
        return hundredthDigit - 5;
    }

    public int extractHundredthDigit(long powerLevel) {
        if (-100 < powerLevel && powerLevel < 100) {
            return 0;
        }
        powerLevel = powerLevel / 100 % 10;
        return (int) Math.abs(powerLevel);
    }
}
