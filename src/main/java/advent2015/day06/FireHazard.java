package advent2015.day06;

import java.util.List;

public class FireHazard {

    private int[][] grid;

    public FireHazard() {

    }

    public FireHazard createGrid(int x, int y) {
        grid = new int[x][y];
        return this;
    }

    public FireHazard processInstructions(List<String> lines) {
        for (String line : lines) {
            processInstruction(line);
        }
        return this;
    }

    private void processInstruction(String line) {
        String[] parts = line.split(" ");
        if (parts[0].equals("toggle")) {
            execute(2, parts[1], parts[3]);
        } else if (parts[1].equals("on")) {
            execute(1, parts[2], parts[4]);
        } else {
            execute(0, parts[2], parts[4]);
        }
    }

    private void execute(int mode, String start, String stop) {
        String[] startParts = start.split(",");
        String[] stopParts = stop.split(",");
        int startX = Integer.parseInt(startParts[0]);
        int startY = Integer.parseInt(startParts[1]);
        int stopX = Integer.parseInt(stopParts[0]);
        int stopY = Integer.parseInt(stopParts[1]);
        execute(mode, startX, startY, stopX, stopY);
    }

    //  mode 0 = turn off, mode 1 = turn on, mode 2 = toggle
    private void execute(int mode, int startX, int startY, int stopX, int stopY) {
        for (int x = startX; x <= stopX; x++) {
            for (int y = startY; y <= stopY; y++) {
                switch (mode) {
                    case 2:
                        if (grid[x][y] == 0) {
                            grid[x][y] = 1;
                        } else {
                            grid[x][y] = 0;
                        }
                        break;
                    case 1:
                        grid[x][y] = 1;
                        break;
                    case 0:
                        grid[x][y] = 0;
                    default:
                        break;
                }
            }
        }
    }

    public int countLights() {
        int count = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 1) count++;
            }
        }
        return count;
    }

    public FireHazard altProcessInstructions(List<String> lines) {
        for (String line : lines) {
            altProcessInstruction(line);
        }
        return this;
    }

    private void altProcessInstruction(String line) {
        String[] parts = line.split(" ");
        if (parts[0].equals("toggle")) {
            altExecute(2, parts[1], parts[3]);
        } else if (parts[1].equals("on")) {
            altExecute(1, parts[2], parts[4]);
        } else {
            altExecute(0, parts[2], parts[4]);
        }
    }

    private void altExecute(int mode, String start, String stop) {
        String[] startParts = start.split(",");
        String[] stopParts = stop.split(",");
        int startX = Integer.parseInt(startParts[0]);
        int startY = Integer.parseInt(startParts[1]);
        int stopX = Integer.parseInt(stopParts[0]);
        int stopY = Integer.parseInt(stopParts[1]);
        altExecute(mode, startX, startY, stopX, stopY);
    }

    //  mode 0 = turn off, mode 1 = turn on, mode 2 = toggle
    private void altExecute(int mode, int startX, int startY, int stopX, int stopY) {
        for (int x = startX; x <= stopX; x++) {
            for (int y = startY; y <= stopY; y++) {
                switch (mode) {
                    case 2:
                        grid[x][y] += 2;
                        break;
                    case 1:
                        grid[x][y] += 1;
                        break;
                    case 0:
                        if (grid[x][y] > 0) grid[x][y]--;
                    default:
                        break;
                }
            }
        }
    }

    public int altCountLights() {
        int count = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                count += grid[x][y];
            }
        }
        return count;
    }


}
