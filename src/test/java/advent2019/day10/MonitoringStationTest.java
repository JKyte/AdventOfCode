package advent2019.day10;

import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

public class MonitoringStationTest {

    @Test
    public void testDay10Example1() {
        String map = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##";
        //  Best is 3,4 with 8 other asteroids detected.
        MonitoringStation ms = new MonitoringStation(map);
//        ms.printMap();

        Point expected = new Point(3, 4);
        assertEquals(expected, ms.findBestPosition());
        assertEquals(8, ms.evaluatePosition(3, 4));
    }

    @Test
    public void testDay10Example2() {
        String map = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####";

        //  Best is 5,8 with 33 other asteroids detected.
        MonitoringStation ms = new MonitoringStation(map);
//        ms.printMap();

        Point expected = new Point(5, 8);
        assertEquals(expected, ms.findBestPosition());
        assertEquals(33, ms.evaluatePosition(5, 8));
    }

    @Test
    public void testDay10Example3() {
        String map = "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###.";
        //  Best is 1,2 with 35 other asteroids detected.
        MonitoringStation ms = new MonitoringStation(map);
//        ms.printMap();

        Point expected = new Point(1, 2);
        assertEquals(expected, ms.findBestPosition());
        assertEquals(35, ms.evaluatePosition(1, 2));
    }

    @Test
    public void testDay10Example4() {
        String map = ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#..";
        //  Best is 6,3 with 41 other asteroids detected.
        MonitoringStation ms = new MonitoringStation(map);
//        ms.printMap();

        Point expected = new Point(6, 3);
        assertEquals(expected, ms.findBestPosition());
        assertEquals(41, ms.evaluatePosition(6, 3));
    }

    @Test
    public void testDay10Example5() {
        String map = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##";
        //  Best is 11,13 with 210 other asteroids detected:
        MonitoringStation ms = new MonitoringStation(map);
//        ms.printMap();

        Point expected = new Point(11, 13);
        assertEquals(expected, ms.findBestPosition());
        assertEquals(210, ms.evaluatePosition(11, 13));

        //  Part 2, the 200th asteroid to be zapped is 8,2
        assertEquals(new Point(8, 2), ms.zapzap(11, 13, 200));
        assertEquals(802, pointToAnswer(new Point(8, 2)));
    }

    @Test
    public void testDay10Part1and2() {
        String map = ".#....#.###.........#..##.###.#.....##...\n" +
                "...........##.......#.#...#...#..#....#..\n" +
                "...#....##..##.......#..........###..#...\n" +
                "....#....####......#..#.#........#.......\n" +
                "...............##..#....#...##..#...#..#.\n" +
                "..#....#....#..#.....#.#......#..#...#...\n" +
                ".....#.#....#.#...##.........#...#.......\n" +
                "#...##.#.#...#.......#....#........#.....\n" +
                "....##........#....#..........#.......#..\n" +
                "..##..........##.....#....#.........#....\n" +
                "...#..##......#..#.#.#...#...............\n" +
                "..#.##.........#...#.#.....#........#....\n" +
                "#.#.#.#......#.#...##...#.........##....#\n" +
                ".#....#..#.....#.#......##.##...#.......#\n" +
                "..#..##.....#..#.........#...##.....#..#.\n" +
                "##.#...#.#.#.#.#.#.........#..#...#.##...\n" +
                ".#.....#......##..#.#..#....#....#####...\n" +
                "........#...##...#.....#.......#....#.#.#\n" +
                "#......#..#..#.#.#....##..#......###.....\n" +
                "............#..#.#.#....#.....##..#......\n" +
                "...#.#.....#..#.......#..#.#............#\n" +
                ".#.#.....#..##.....#..#..............#...\n" +
                ".#.#....##.....#......##..#...#......#...\n" +
                ".......#..........#.###....#.#...##.#....\n" +
                ".....##.#..#.....#.#.#......#...##..#.#..\n" +
                ".#....#...#.#.#.......##.#.........#.#...\n" +
                "##.........#............#.#......#....#..\n" +
                ".#......#.............#.#......#.........\n" +
                ".......#...##........#...##......#....#..\n" +
                "#..#.....#.#...##.#.#......##...#.#..#...\n" +
                "#....##...#.#........#..........##.......\n" +
                "..#.#.....#.....###.#..#.........#......#\n" +
                "......##.#...#.#..#..#.##..............#.\n" +
                ".......##.#..#.#.............#..#.#......\n" +
                "...#....##.##..#..#..#.....#...##.#......\n" +
                "#....#..#.#....#...###...#.#.......#.....\n" +
                ".#..#...#......##.#..#..#........#....#..\n" +
                "..#.##.#...#......###.....#.#........##..\n" +
                "#.##.###.........#...##.....#..#....#.#..\n" +
                "..........#...#..##..#..##....#.........#\n" +
                "..#..#....###..........##..#...#...#..#..";

        MonitoringStation ms = new MonitoringStation(map);
//        ms.printMap();

        Point expected = new Point(28, 29);
        assertEquals(expected, ms.findBestPosition());
        assertEquals(340, ms.evaluatePosition(28, 29));

        //  Part 2, the 200th asteroid to be zapped is !2,14
        assertEquals(new Point(26, 28), ms.zapzap(28, 29, 200));
        assertEquals(2628, pointToAnswer(new Point(26, 28)));
    }

    private int pointToAnswer(Point p) {
        return (p.x * 100) + p.y;
    }
}
