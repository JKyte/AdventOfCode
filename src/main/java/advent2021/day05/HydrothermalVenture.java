package advent2021.day05;

import java.util.List;

public class HydrothermalVenture {

    private int[][] grid;

    public HydrothermalVenture() {

    }

    public HydrothermalVenture processVents(List<String> lines) {
        return processVents(lines, false);
    }

    public HydrothermalVenture processVents(List<String> lines, boolean part2) {
        createGrid(lines);
        layVents(lines, part2);
        return this;
    }

    private void createGrid(List<String> lines) {
        //  find max X,Y and create grid from that
        int maxX = 0;
        int maxY = 0;
        for (String line : lines) {
            String[] parts = line.split(" -> ");
            String[] leftParts = parts[0].split(",");
            String[] rightParts = parts[1].split(",");

            int leftX = Integer.parseInt(leftParts[0]);
            int leftY = Integer.parseInt(leftParts[1]);

            int rightX = Integer.parseInt(rightParts[0]);
            int rightY = Integer.parseInt(rightParts[1]);

            if (leftX > maxX) maxX = leftX;
            if (leftY > maxY) maxY = leftY;
            if (rightX > maxX) maxX = rightX;
            if (rightY > maxY) maxY = rightY;
        }

        grid = new int[maxX + 1][maxY + 1];
    }

    private void layVents(List<String> lines, boolean part2) {
        for (String line : lines) {
            layVents(line, part2);
        }
    }

    private void layVents(String line, boolean part2) {
        String[] parts = line.split(" -> ");
        String[] leftParts = parts[0].split(",");
        String[] rightParts = parts[1].split(",");

        int leftX = Integer.parseInt(leftParts[0]);
        int leftY = Integer.parseInt(leftParts[1]);

        int rightX = Integer.parseInt(rightParts[0]);
        int rightY = Integer.parseInt(rightParts[1]);

        if (leftX == rightX) {
            //  lay horizontal line
            int minY = Math.min(leftY, rightY);
            int maxY = Math.max(leftY, rightY);
            for (int y = minY; y <= maxY; y++) {
                grid[leftX][y]++;
            }
        } else if (leftY == rightY) {
            //  lay vertical line
            int minX = Math.min(leftX, rightX);
            int maxX = Math.max(leftX, rightX);
            for (int x = minX; x <= maxX; x++) {
                grid[x][leftY]++;
            }
        } else if (part2) {
            //  also do diagonal lines
            do {
                grid[leftX][leftY]++;

                if (leftX <= rightX) {
                    leftX++;
                } else {
                    leftX--;
                }
                if (leftY <= rightY) {
                    leftY++;
                } else {
                    leftY--;
                }

            } while (leftX != rightX && leftY != rightY);

            grid[leftX][leftY]++;
        }
    }

    public int countOverlapsGreaterThanTwo() {
        int count = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] >= 2) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printGrid() {
        for (int x = 0; x < grid.length; x++) {
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[y][x] == 0) {
                    sb.append(".");
                } else {
                    sb.append(grid[y][x]);
                }
            }
            System.out.println(sb.toString());
        }
    }
}
