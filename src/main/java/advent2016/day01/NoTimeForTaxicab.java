package advent2016.day01;

import org.apache.commons.lang3.StringUtils;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class NoTimeForTaxicab {

    public NoTimeForTaxicab() {
    }

    public int distance(String input) {
        return distance(input, false);
    }

    public int distance(String input, boolean stopAtDuplicate) {
        return distance(StringUtils.split(input, ", "), stopAtDuplicate);
    }

    private int distance(String[] split, boolean stopAtDuplicate) {
        Set<Point> locs = new HashSet<>();
        int x = 0;
        int y = 0;
        String dir = "N";
        outer:
        for (String s : split) {

            boolean left = s.charAt(0) == 'L';
            dir = changeDir(dir, left);

            int steps = Integer.parseInt(s.substring(1));

            switch (dir) {
                case "N":
                    if (stopAtDuplicate) {
                        for (int i = 0; i < steps; i++) {
                            y++;
                            if (!locs.add(new Point(x, y))) {
                                y--;
                                break outer;
                            }
                        }
                    } else {
                        y += steps;
                    }
                    break;
                case "S":
                    if (stopAtDuplicate) {
                        for (int i = 0; i < steps; i++) {
                            y--;
                            if (!locs.add(new Point(x, y))) {
                                y++;
                                break outer;
                            }
                        }
                    } else {
                        y -= steps;
                    }
                    break;
                case "E":
                    if (stopAtDuplicate) {
                        for (int i = 0; i < steps; i++) {
                            x++;
                            if (!locs.add(new Point(x, y))) {
                                x--;
                                break outer;
                            }
                        }
                    } else {
                        x += steps;
                    }
                    break;
                case "W":
                    if (stopAtDuplicate) {
                        for (int i = 0; i < steps; i++) {
                            x--;
                            if (!locs.add(new Point(x, y))) {
                                x++;
                                break outer;
                            }
                        }
                    } else {
                        x -= steps;
                    }
                    break;
                default:
                    System.err.println("Unknown direction: " + dir);
                    System.exit(0);
            }
//            System.out.println("Move " + dir + " " + steps + " steps");
        }

//        System.out.println("------------------------------");
        return Math.abs(x) + Math.abs(y);
    }

    private String changeDir(String dir, boolean left) {
        switch (dir) {
            case "N":
                if (left)
                    return "W";
                else
                    return "E";
            case "S":
                if (left)
                    return "E";
                else
                    return "W";
            case "E":
                if (left)
                    return "N";
                else
                    return "S";
            case "W":
                if (left)
                    return "S";
                else
                    return "N";
            default:
                return "";
        }
    }


}
