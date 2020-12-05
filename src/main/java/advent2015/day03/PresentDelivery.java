package advent2015.day03;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class PresentDelivery {

    Set<Point> houses;

    public PresentDelivery() {
    }

    public int countDeliveries(String input) {
        return countDeliveries(input.toCharArray());
    }

    private int countDeliveries(char[] input) {
        houses = new HashSet<>();
        int x = 0;
        int y = 0;
        houses.add(new Point(x, y));
        for (char c : input) {
            switch (c) {
                case '^':
                    y++;
                    houses.add(new Point(x, y));
                    continue;
                case 'v':
                    y--;
                    houses.add(new Point(x, y));
                    continue;
                case '<':
                    x--;
                    houses.add(new Point(x, y));
                    continue;
                case '>':
                    x++;
                    houses.add(new Point(x, y));
                    continue;
                default:
                    System.out.println("Problem!");
            }
        }
        return houses.size();
    }

    public int countRobotDeliveries(String input) {
        return countRobotDeliveries(input.toCharArray());
    }

    private int countRobotDeliveries(char[] input) {
        houses = new HashSet<>();
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        houses.add(new Point(x, y));
        for (int i = 0; i < input.length; i += 2) {
            char c = input[i];
            switch (c) {
                case '^':
                    y++;
                    break;
                case 'v':
                    y--;
                    break;
                case '<':
                    x--;
                    break;
                case '>':
                    x++;
                    break;
                default:
                    System.out.println("Problem!");
            }
            houses.add(new Point(x, y));

            char c2 = input[i + 1];
            switch (c2) {
                case '^':
                    y2++;
                    break;
                case 'v':
                    y2--;
                    break;
                case '<':
                    x2--;
                    break;
                case '>':
                    x2++;
                    break;
                default:
                    System.out.println("Problem!");
            }
            houses.add(new Point(x2, y2));
        }
        return houses.size();
    }
}
