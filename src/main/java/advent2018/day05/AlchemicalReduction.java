package advent2018.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Solution for https://adventofcode.com/2018/day/5
 *
 * Part 1: After 20219 reactions, 9562 units remain.
 *
 * Part 2: Smallest resulting polymer was 4934 long by removing N
 */
public class AlchemicalReduction implements Runnable {

    public static void main(String[] args) {
        AlchemicalReduction ar = new AlchemicalReduction();
        ar.run();
    }

    private String inputPath = "src/main/resources/advent2018/AlchemicalReduction.txt";

    public AlchemicalReduction() {

    }

    @Override
    public void run() {
        String polymer = readPolymer();
        int len = polymer.length();
        System.out.println("Starting polymer length: " + len);
        fullyReact(polymer);
        System.out.println("Remaining polymer: " + polymer);

        improvePolymer(readPolymer());
    }

    private String readPolymer() {
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void improvePolymer(String polymer) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int smallestResult = Integer.MAX_VALUE;
        char smallestChar = ' ';
        for (char c : chars) {
            String tmpPoly = polymer;
            tmpPoly = tmpPoly.replaceAll(("" + c), "");
            tmpPoly = tmpPoly.replaceAll(("" + c).toLowerCase(), "");
            tmpPoly = fullyReact(tmpPoly);
            if (tmpPoly.length() < smallestResult) {
                smallestChar = c;
                smallestResult = tmpPoly.length();
            }
            System.out.println("Removing " + c + " results in " + tmpPoly.length() + " length.");
        }
        System.out.println("Smallest resulting polymer was " + smallestResult + " long by removing " + smallestChar);
    }

    private String fullyReact(String polymer) {
        int len = polymer.length();
        int numReactions = 0;
        boolean reaction = true;
        while (reaction) {
            polymer = react(polymer);
            if (polymer.length() < len) {
                len = polymer.length();
                numReactions++;
            } else {
                reaction = false;
            }
        }
        System.out.println("After " + numReactions + " reactions, " + polymer.length() + " units remain.");
        return polymer;
    }

    private String react(String polymer) {
        for (int ii = 0; ii < polymer.length() - 1; ii++) {
            //  Check for toUpper equality
            if (polymer.substring(ii, ii + 1).toUpperCase().equals(polymer.substring(ii + 1, ii + 2).toUpperCase())) {
                //  Check for character inequality
                if (polymer.charAt(ii) != polymer.charAt(ii + 1)) {

                    //  Perform reaction and return
                    return polymer.substring(0, ii) + polymer.substring(ii + 2);
                }
            }
        }
        return polymer;
    }
}
