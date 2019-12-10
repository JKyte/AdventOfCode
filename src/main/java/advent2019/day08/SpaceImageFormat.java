package advent2019.day08;

import core.Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Part 1: 2318
 * <p>
 * Part 2:
 *
 * 0110010010111100110011100
 * 1001010010100001001010010
 * 1001011110111001000011100
 * 1111010010100001000010010
 * 1001010010100001001010010
 * 1001010010100000110011100
 *
 * !AHFGR
 * !AHFCB
 */
public class SpaceImageFormat extends Core {

    public static void main(String[] args) {
        SpaceImageFormat sif = new SpaceImageFormat();
        sif.run();
    }

    @Override
    public void run() {
        inputPath += "advent2019/InputDay8";
        String line = readLine();
//        line = "0222112222120000";
        System.out.println("line.len: " + line.length());
        List<String> layers = new ArrayList<>();
        //  Layer is 25x6
        int layerSize = 25 * 6;
//        int layerSize = 2*2;
        int numLayers = line.length() / layerSize;
        System.out.println("Num Layers: " + numLayers);
        for (int ii = 0; ii < numLayers; ii++) {
            layers.add(line.substring((ii * layerSize), (ii + 1) * layerSize));
        }
        System.out.println("Num Layers: " + layers.size());

        int minLayer = Integer.MAX_VALUE;
        int minCount = Integer.MAX_VALUE;
        for (int ii = 0; ii < layers.size(); ii++) {
            int count = countChar(layers.get(ii), "0");
            if (count < minCount) {
                minCount = count;
                minLayer = ii;
            }
//            System.out.println("Layer " + ii + " zero count: " + count);
        }

        System.out.println("Layer " + minLayer + " has smallest zero count " + minCount);
        int oneCount = countChar(layers.get(minLayer), "1");
        int twoCount = countChar(layers.get(minLayer), "2");
        int result = oneCount * twoCount;
        System.out.println("Product: " + result);

        int[] tops = new int[layerSize];
        for (int ii = 0; ii < layerSize; ii++) {
            int[] ints = getIntsForIndex(layers, ii);
            int top = getTopInt(ints);
            System.out.println("Index: " + ii + " -- " + top + " -- " + Arrays.toString(ints));
            tops[ii] = top;
        }
        StringBuilder s = new StringBuilder();
        s.append('\n');
        for (int i = 0; i < tops.length; i++) {
            int top = tops[i];
            if (i % 25 == 0 && i > 0)
                s.append('\n');
            s.append(top);

        }
        System.out.println("Final: " + s.toString());
        System.out.println("Final: " + s.toString().length());
    }

    private int getTopInt(int[] ints) {
        for (int i : ints) {
            if (i != 2) {
                return i;
            }
        }
        return -1;
    }

    public static int[] getIntsForIndex(List<String> layers, int index) {
        int[] ints = new int[layers.size()];
        for (int i = 0; i < layers.size(); i++) {
            String layer = layers.get(i);
            if (layer.charAt(index) == '0') {
                ints[i] = 0;
            } else if (layer.charAt(index) == '1') {
                ints[i] = 1;
            } else if (layer.charAt(index) == '2') {
                ints[i] = 2;
            } else {
                System.out.println("HALT");
                System.exit(0);
            }
        }
        return ints;
    }

    public static int countChar(String layer, String c) {
        int count = 0;
        for (int ii = 0; ii < layer.length(); ii++) {
            if (layer.charAt(ii) == c.charAt(0))
                count++;
        }
        return count;
    }
}
