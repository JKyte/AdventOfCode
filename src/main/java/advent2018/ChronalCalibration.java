package advent2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for https://adventofcode.com/2018/day/1
 * <p>
 * Day 1: Final frequency is +477.
 * <p>
 * Day 2: Found duplicate freq [390] in 137 cycles, iteration: 138573.
 */
public class ChronalCalibration implements Runnable {


    public static void main(String[] args) {
        ChronalCalibration chronalCalibration = new ChronalCalibration();
        chronalCalibration.run();
    }

    private String inputPath = "src/main/resources/advent2018/ChronalCalibrationInput.txt";
    private boolean findRepeats = false;
    private HashSet<Integer> seenFrequencies;

    public ChronalCalibration() {
        seenFrequencies = new HashSet<>();
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new File(inputPath));
            String line;
            int curFreq = 0;
            int deltaFreq = 0;
            int nextFreq = 0;

            int iteration = 0;
            int cycle = 0;
            String msg;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                iteration++;

                deltaFreq = Integer.parseInt(line);
                nextFreq = curFreq + deltaFreq;


                msg = "iter: " + iteration + "   " + stringify(curFreq) + ", " + stringify(deltaFreq) + " results in " + stringify(nextFreq);
                curFreq = nextFreq;
                System.out.println(msg);

                //  Check for duplicates?
                if (findRepeats) {
                    if (!seenFrequencies.add(curFreq)) {
                        System.out.println("Found duplicate freq [" + curFreq + "] in " + cycle + " cycles, iteration: " + iteration + ".");
                        System.exit(0);
                    }
                    if (!scanner.hasNext()) {
                        //  Reset scanner
                        scanner = new Scanner(new File(inputPath));
                        cycle++;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String stringify(int i) {
        if (i < 0) {
            return "" + i;
        } else {
            return "+" + i;
        }
    }
}
