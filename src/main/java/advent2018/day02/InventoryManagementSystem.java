package advent2018.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Solution for https://adventofcode.com/2018/day/2
 * <p>
 * Part 1: Checksum: 6448 = 248 * 26
 * <p>
 * Part 2: Common letters of two correct boxes: evsialkqyiurohzpwucngttmf
 */
public class InventoryManagementSystem implements Runnable {

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        ims.run();
    }

    private String inputPath = "src/main/resources/advent2018/InventoryMangementSystemInput.txt";
    private int twoCount = 0;
    private int threeCount = 0;
    private List<String> keptLabels;
    private boolean partTwo = true;

    public InventoryManagementSystem() {
        keptLabels = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new File(inputPath));
            String boxLabel;
            Map<Character, Integer> multimap;
            boolean isPair;
            boolean isTriple;
            while (scanner.hasNext()) {
                isPair = false;
                isTriple = false;
                boxLabel = scanner.nextLine();
                multimap = burstLabelToMap(boxLabel);
                if (hasPair(multimap)) {
                    isPair = true;
                    twoCount++;
                }
                if (hasTriple(multimap)) {
                    isTriple = true;
                    threeCount++;
                }

                //  Add label if either is true
                if (partTwo && isPair || isTriple) {
                    keptLabels.add(boxLabel);
                }

                String msg;
                if (isPair && isTriple) {
                    msg = "Label " + boxLabel + " contains letters that appear exactly two and three times.";
                } else if (isPair) {
                    msg = "Label " + boxLabel + " contains letters that appear exactly two times.";
                } else if (isTriple) {
                    msg = "Label " + boxLabel + " contains letters that appear exactly three times.";
                } else {
                    msg = "Label " + boxLabel + " contains no letters that appear exactly two or three times.";
                }
                System.out.println(msg);
            }

            int checksum = twoCount * threeCount;
            System.out.println("Checksum: " + checksum + " = " + twoCount + " * " + threeCount);

            if (partTwo) {
                for (int ii = 0; ii < keptLabels.size(); ii++) {
                    String labelOne = keptLabels.get(ii);
                    for (int jj = ii; jj < keptLabels.size(); jj++) {
                        String labelTwo = keptLabels.get(jj);
                        if (labelOne.equals(labelTwo)) {
                            continue;
                        }
                        int deltaSize = labelDelta(labelOne, labelTwo);
                        System.out.println(labelOne + " " + labelTwo + "  delta size: " + deltaSize + " " + labelOne.length());
                        if (deltaSize == 1) {
                            System.out.println("Common letters: " + labelUnion(labelOne, labelTwo));
                            System.exit(0);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<Character, Integer> burstLabelToMap(String boxLabel) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = boxLabel.toCharArray();
        for (char c : charArray) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                int tmp = map.get(c);
                tmp++;
                map.put(c, tmp);
            }
        }
        return map;
    }

    public boolean hasPair(Map<Character, Integer> map) {
        for (Character value : map.keySet()) {
            if (map.get(value) == 2)
                return true;
        }
        return false;
    }

    public boolean hasTriple(Map<Character, Integer> map) {
        for (Character value : map.keySet()) {
            if (map.get(value) == 3)
                return true;
        }
        return false;
    }

    public int labelDelta(String labelOne, String labelTwo) {
        int delta = 0;
        char[] one = labelOne.toCharArray();
        char[] two = labelTwo.toCharArray();
        for (int ii = 0; ii < one.length; ii++) {
            if (one[ii] != two[ii]) {
                delta++;
            }
        }
        return delta;
    }

    public String labelUnion(String labelOne, String labelTwo) {
        StringBuilder sb = new StringBuilder();
        char[] one = labelOne.toCharArray();
        char[] two = labelTwo.toCharArray();
        for (int ii = 0; ii < one.length; ii++) {
            if (one[ii] == two[ii]) {
                sb.append(one[ii]);
            }
        }
        return sb.toString();
    }
}
