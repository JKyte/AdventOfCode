package advent2015.day06;

import java.util.List;

public class FireHazard {

    private boolean[][] grid;

    public FireHazard() {

    }

    public FireHazard createGrid(int x, int y) {
        grid = new boolean[x][y];
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
                        grid[x][y] = !grid[x][y];
                        break;
                    case 1:
                        grid[x][y] = true;
                        break;
                    case 0:
                        grid[x][y] = false;
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
                if (grid[x][y]) count++;
            }
        }
        return count;
    }


}
