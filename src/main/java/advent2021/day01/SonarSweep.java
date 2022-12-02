package advent2021.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SonarSweep {

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> depths;
        try (Scanner scanner = new Scanner(new File("src/main/resources/advent2021/SonarSweep.txt"))) {
            depths = new ArrayList<>();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                depths.add(Integer.parseInt(line));
            }
        }

        SonarSweep sonarSweep = new SonarSweep();
        sonarSweep.partOne(depths, true);
        sonarSweep.partTwo(depths, true); //  1230 is too low
    }

    public SonarSweep() {

    }

    public int partOne(List<Integer> depths) {
        return partOne(depths, false);
    }

    public int partOne(List<Integer> depths, boolean verbose) {
        int greater = 0;
        int last = -1;
        for (int depth : depths) {
            if (last == -1) {
                last = depth;
                if (verbose) System.out.println(depth + " (N/A - no previous measurement)");
            } else {
                if (depth > last) {
                    greater++;
                    if (verbose) System.out.println(depth + " (increased)");
                } else {
                    if (verbose) System.out.println(depth + " (decreased)");
                }
                last = depth;
            }
        }

        if (verbose) System.out.println("depths greater than last: " + greater);
        return greater;
    }

    public int partTwo(List<Integer> depths) {
        return partTwo(depths, false);
    }

    public int partTwo(List<Integer> depths, boolean verbose) {
        int greater = 0;
        int last = -1;
        for (int i = 3; i <= depths.size(); i++) {
            //  sum last three
            int depth = 0;
            for (int j = i - 3; j < i; j++) {
                depth += depths.get(j);
                if (verbose) System.out.println("summing: " + depths.get(j));
            }

            if (last == -1) {
                if (verbose) System.out.println(depth + " (N/A - no previous measurement)");
            } else {
                if (depth > last) {
                    greater++;
                    if (verbose) System.out.println(depth + " (increased)");
                } else {
                    if (verbose) System.out.println(depth + " (decreased)");
                }
            }
            last = depth;
        }
        if (verbose) System.out.println("depths greater than last: " + greater);
        return greater;
    }

}
