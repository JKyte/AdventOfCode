package advent2019.day10;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.lang3.StringUtils;

import java.awt.Point;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MonitoringStation {

    char[][] map;

    public MonitoringStation(String mapString) {
        loadMap(mapString);
    }

    public void loadMap(String mapString) {
        int asteroidCount = 0;
        String[] mapStrings = StringUtils.split(mapString, '\n');
        map = new char[mapStrings.length][mapStrings.length];
        for (int y = 0; y < mapStrings.length; y++) {
            char[] line = mapStrings[y].toCharArray();
            for (int x = 0; x < mapStrings.length; x++) {
                if (line[x] == '#')
                    asteroidCount++;
                map[y][x] = line[x];
            }
        }
//        System.out.println("Map loaded with " + asteroidCount + " asteroids.");
    }

    public void printMap() {
        for (int y = 0; y < map.length; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < map.length; x++) {
                sb.append(map[y][x]);
            }
            System.out.println(sb.toString());
        }
    }

    public Point findBestPosition() {
        int bestX = 0;
        int bestY = 0;
        int mostAsteroids = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == '#') {
                    int seenAsteroids = evaluatePosition(x, y);
//                    System.out.println("Position (" + x + ", " + y + ") can see " + seenAsteroids + " asteroids.");
                    if (seenAsteroids > mostAsteroids) {
                        bestX = x;
                        bestY = y;
                        mostAsteroids = seenAsteroids;
                    }
                }
            }
        }
//        System.out.println("Best position is (" + bestX + ", " + bestY + ") with " + mostAsteroids + " seen asteroids.");
        return new Point(bestX, bestY);
    }

    /**
     * Evaluate the asteroid at (X,Y) by determining how many other asteroids it has line of sight on.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the number of asteroids visible from this position.
     */
    public int evaluatePosition(int x, int y) {
        if (map[y][x] != '#') {
            return -1;
        }

        //   x,y is the start.
        //  Find the slope for each other asteroid.
        Multimap<Double, Point> otherAsteroids = HashMultimap.create();
        for (int y2 = 0; y2 < map.length; y2++) {
            for (int x2 = 0; x2 < map[0].length; x2++) {
                //  Must be an asteroid and not the current point.
                if (map[y2][x2] == '#' && !(x == x2 && y == y2)) {
                    double slope = findAngle(x, y, x2, y2);
                    otherAsteroids.put(slope, new Point(x2, y2));
                }
            }
        }

        //  Count the visible asteroids.
        return otherAsteroids.keySet().size();
//        int visible = 0;
//        for (Double key : otherAsteroids.keySet()) {
//            Collection<Point> asteroids = otherAsteroids.get(key);
//            if (asteroids.size() == 1) {
//                visible++;
//            }
//
//        }
//        SortedSet<Double> sortedKeys = new TreeSet<>(otherAsteroids.keySet());
//        return visible;
    }

    private double findAngle(int x, int y, int x2, int y2) {
        int deltaX = x2 - x;
        int deltaY = y2 - y;
        //  +90 because I messed up something in the map. 2D arrays have never been my thing in Java...
        double angle = Math.toDegrees(Math.atan2(deltaY, deltaX)) + 90;
        if (angle < 0)
            angle += 360;
        return angle;
    }

    //  Return the Nth asteroid zapped.
    public Point zapzap(int x, int y, int n) {
        Multimap<Double, Point> otherAsteroids = HashMultimap.create();
        for (int y2 = 0; y2 < map.length; y2++) {
            for (int x2 = 0; x2 < map.length; x2++) {
                if (x2 == 11 && y2 == 12) {
                    int somethign = 5;
                }
                //  Must be an asteroid and not the current point.
                if (map[y2][x2] == '#' && !(x == x2 && y == y2)) {
                    double slope = findAngle(x, y, x2, y2);
                    otherAsteroids.put(slope, new Point(x2, y2));
                }
            }
        }

        int zapCount = 0;
        int zapX = -1;
        int zapY = -1;
        while (zapCount != n) {
            SortedSet<Double> keys = new TreeSet<>(otherAsteroids.keySet());
            for (Double key : keys) {
                Collection<Point> points = otherAsteroids.get(key);
                if (points.size() == 1) {
                    Point head = points.iterator().next();
                    zapX = head.x;
                    zapY = head.y;
                    otherAsteroids.remove(key, head);
                } else {
                    //  Sort by distance and get closest point.
                    SortedSet<Point> sortedPoints = new TreeSet<>(new Comparator<Point>() {
                        @Override
                        public int compare(Point o1, Point o2) {
                            int o1Dist = findDistance(x, y, o1.x, o1.y);
                            int o2Dist = findDistance(x, y, o2.x, o2.y);
                            return o1Dist - o2Dist;
                        }
                    });
                    sortedPoints.addAll(points);

                    Point head = sortedPoints.first();
                    zapX = head.x;
                    zapY = head.y;
                    otherAsteroids.remove(key, head);
                }

                zapCount++;
//                System.out.println("zap " + zapCount + " " + zapX + ", " + zapY);
                if (zapCount == n) {
                    break;
                }
            }
        }
//        System.out.println(n + "th zap was at coord (" + zapX + ", " + zapY + ")");
        return new Point(zapX, zapY);
    }

    private int findDistance(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }
}
