package advent2018.day11;

import core.Core;

/**
 * Solution for https://adventofcode.com/2018/day/11 credit to the subreddit for algorithm pseudo-code
 * <p>
 * Part 1: 21,22
 * <p>
 * Part 2: 235,288,13
 * <p>
 * Should implement https://en.wikipedia.org/wiki/Summed-area_table
 */
public class ChronalCharge extends Core {

    public static void main(String[] args) {
        ChronalCharge cc = new ChronalCharge();
        cc.run();
    }

//    int gridSerialNumber = 18; -> 90,269,16 with 113 power
//    int gridSerialNumber = 42; -> 232,251,12 with 42 power
//    int gridSerialNumber = 5791;
    int gridSerialNumber = 7511;
//    int gridSerialNumber = 8141;

    public ChronalCharge() {
        this.inputPath += "advent2018/ChronalCharge.txt";
    }

    @Override
    public void run() {
        int size = 301;
        int[][] grid = new int[size][size];
        for (int y = 1; y < grid.length; y++) {
            for (int x = 1; x < grid.length; x++) {
                int id = x + 10;
                long power = id * y + gridSerialNumber;
                power = (power * id) / 100 % 10 - 5;

                grid[y][x] = (int) (power + grid[y - 1][x] + grid[y][x - 1] - grid[y - 1][x - 1]);
            }
        }
        int best = 0, bx = 0, by = 0, bs = 0;
        for (int s = 1; s < grid.length; s++) {
//        int s = 3;
            for (int y = s; y < grid.length; y++) {
                for (int x = s; x < grid.length; x++) {
                    int total = grid[y][x] - grid[y - s][x] - grid[y][x - s] + grid[y - s][x - s];
                    if (total > best) {
                        best = total;
                        bx = x;
                        by = y;
                        bs = s;
                    }
                }
            }
        }
        System.out.println("Best x,y,size: " + (bx - bs + 1) + "," + (by - bs + 1) + "," + bs + " has a value of " + best);
    }
}
