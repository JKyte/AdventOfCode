package advent2021.day09;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SmokeBasin {

    List<Point> lowPoints;
    List<Set<Point>> basins;
    int[][] map;

    public SmokeBasin() {
        this.lowPoints = new ArrayList<>();
        this.basins = new ArrayList<>();
    }

    public SmokeBasin withInput(String[] input) {
        int len = input.length;
        int width = input[0].length();
        map = new int[len][width];
        for (int i = 0; i < input.length; i++) {
            String s = input[i];
            char[] cs = s.toCharArray();
            for (int j = 0; j < cs.length; j++) {
                map[i][j] = Integer.parseInt("" + cs[j]);
            }
        }
        return this;
    }

    public SmokeBasin findLowPoints() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                boolean north = false;
                if (i == 0) {
                    north = true;
                } else {
                    north = map[i - 1][j] > map[i][j];
                }

                boolean south = false;
                if (i == map.length - 1) {
                    south = true;
                } else {
                    south = map[i + 1][j] > map[i][j];
                }

                boolean west = false;
                if (j == 0) {
                    west = true;
                } else {
                    west = map[i][j - 1] > map[i][j];
                }

                boolean east = false;
                if (j == map[0].length - 1) {
                    east = true;
                } else {
                    east = map[i][j + 1] > map[i][j];
                }

                if (north && south && west && east) {
                    lowPoints.add(new Point(i, j));
                }
            }
        }
        return this;
    }

    public SmokeBasin findBasins() {
        findLowPoints();
        for (Point p : lowPoints) {
            Set<Point> visited = new HashSet<>();
            Stack<Point> edges = new Stack<>();
            edges.push(p);

            while (!edges.isEmpty()) {
                Point edge = edges.pop();
                visited.add(edge);

                //  north
                if (edge.x > 0) {
                    Point north = new Point(edge.x - 1, edge.y);
                    if (!visited.contains(north) && map[north.x][north.y] != 9) {
                        edges.push(north);
                    }
                }

                //  south
                if (edge.x < map.length - 1) {
                    Point south = new Point(edge.x + 1, edge.y);
                    if (!visited.contains(south) && map[south.x][south.y] != 9) {
                        edges.push(south);
                    }
                }

                //  west
                if (edge.y > 0) {
                    Point west = new Point(edge.x, edge.y - 1);
                    if (!visited.contains(west) && map[west.x][west.y] != 9) {
                        edges.push(west);
                    }
                }

                //  east
                if (edge.y < map[0].length - 1) {
                    Point west = new Point(edge.x, edge.y + 1);
                    if (!visited.contains(west) && map[west.x][west.y] != 9) {
                        edges.push(west);
                    }
                }
            }
            basins.add(visited);
        }
        return this;
    }

    public int sum() {
        int sum = 0;
        for (Point point : lowPoints) {
            sum += (map[point.x][point.y] + 1);
        }
        return sum;
    }

    public int sumBasins() {
        basins.sort(Comparator.comparing(Set::size));
        int mult = 1;
        for (int i = 0; i < 3; i++) {
            mult *= basins.get(basins.size() - 1 - i).size();
        }
        return mult;
    }
}
