package advent2019.day08;

import core.Core;

import java.util.ArrayList;
import java.util.List;

/**
 * Part 1: 2318
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
        System.out.println("line.len: " + line.length());
        List<String> layers = new ArrayList<>();
        //  Layer is 25x6
        int layerSize = 25 * 6;
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
