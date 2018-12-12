package advent2018;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution for https://adventofcode.com/2018/day/6
 * <p>
 * Part 1: 4887
 * <p>
 * Part 2: 34096
 */
public class ChronalCoordinates implements Runnable {

    public static void main(String[] args) {
        ChronalCoordinates cc = new ChronalCoordinates();
        cc.run();
    }

    private String inputPath = "src/main/resources/advent2018/ChronalCoordinates.txt";

    public ChronalCoordinates() {

    }

    @Override
    public void run() {
        Set<String> lines = readLines();
        Set<Coord> coords = linesToCoords(lines);
        int[][] grid = createGridFromCoords(coords);
        fillGrid(grid, coords);
        int maxArea = findGreatestArea(grid);
        System.out.println("Greatest area size is: " + maxArea);
        int safestArea = findSafestArea(grid, coords);
        System.out.println("Safest area size is: " + safestArea);
    }

    private Set<String> readLines() {
        Set<String> lines = new TreeSet<>();
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private Set<Coord> linesToCoords(Set<String> lines) {
        Set<Coord> coords = new HashSet<>();
        String[] parts;
        int count = 0;
        for (String line : lines) {
            parts = line.replace(",", "").split(" ");
            coords.add(new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), count));
            count++;
        }
        return coords;
    }

    private int[][] createGridFromCoords(Set<Coord> coords) {
        int maxX = 0;
        int maxY = 0;
        for (Coord coord : coords) {
            if (coord.x > maxX)
                maxX = coord.x;
            if (coord.y > maxY)
                maxY = coord.y;
        }
        System.out.println("Max X: " + maxX + " Max Y: " + maxY);
        return new int[maxX][maxY];
//        if (maxX > maxY) {
//            return new int[maxX][maxX];
//        } else {
//            return new int[maxY][maxY];
//        }
    }

    private void fillGrid(int[][] grid, Set<Coord> coords) {
        for (int ii = 0; ii < grid.length; ii++) {
            for (int jj = 0; jj < grid[0].length; jj++) {
                grid[ii][jj] = findClosestCoord(ii, jj, coords);
            }
        }
    }

    /**
     * Returns the ID of the closest coord, or -1 if there is a tie.
     */
    private int findClosestCoord(int x, int y, Set<Coord> coords) {
        int distance = Integer.MAX_VALUE;
        int curDistance;
        int closestId = -1;
        for (Coord coord : coords) {
            curDistance = manhattanDist(x, y, coord);
            if (curDistance == 0) {
                return coord.id;
            }
            if (curDistance < distance) {
                distance = curDistance;
                closestId = coord.id;
            }
        }

        for (Coord coord : coords) {
            if (distance == manhattanDist(x, y, coord) && coord.id != closestId) {
                return -1;
            }
        }
        return closestId;
    }

    private int findGreatestArea(int[][] grid) {
        Multimap<Integer, Integer> areas = HashMultimap.create();
        Set<Integer> infiniteAreas = new HashSet<>();
        int count = 0;
        for (int ii = 0; ii < grid.length; ii++) {
            for (int jj = 0; jj < grid[0].length; jj++) {
                count++;
                //  Defeat infinity with boundary checks
                if (ii == 0 || jj == 0 || ii == grid.length - 1 || jj == grid[0].length - 1) {
                    infiniteAreas.add(grid[ii][jj]);
                    continue;
                }

                if (!infiniteAreas.contains(grid[ii][jj]) && grid[ii][jj] >= 0) {
                    if (areas.containsKey(grid[ii][jj])) {
                        areas.put(grid[ii][jj], count);
                    } else {
                        areas.put(grid[ii][jj], count);
                    }
                }
            }
        }
        int maxArea = 0;
        for (int key : areas.keySet()) {
            int curArea = areas.get(key).size();
            if (curArea > maxArea) {
                maxArea = curArea;
            }
        }
        return maxArea;
    }

    private int findSafestArea(int[][] grid, Set<Coord> coords) {
        int safeAreaSize = 0;
        int threshold = 10000;  //  10k
        for (int ii = 0; ii < grid.length; ii++) {
            for (int jj = 0; jj < grid[0].length; jj++) {
                int dist = manhattanSum(ii, jj, coords);
                if (dist < threshold) {
                    safeAreaSize++;
                }
            }
        }
        return safeAreaSize;
    }

    private int manhattanSum(int x, int y, Set<Coord> coords) {
        int sum = 0;
        for (Coord coord : coords) {
            sum += manhattanDist(x, y, coord);
        }
        return sum;
    }

    private int manhattanDist(int x, int y, Coord coord) {
        return manhattanDist(x, y, coord.x, coord.y);
    }

    private int manhattanDist(int x, int y, int x2, int y2) {
        return Math.abs((x - x2)) + Math.abs(y - y2);
    }

    public class Coord {
        int x;
        int y;
        int id;

        public Coord(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
}
