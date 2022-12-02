package advent2019.day06;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Part 1: 151345
 * <p>
 * Part 2: 391
 */
public class UniversalOrbitMap {

    public static void main(String[] args) throws FileNotFoundException {
        String raw = "COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L";
//        String[] inputs = StringUtils.split(raw, '\n');

        String[] inputs = new String[0];
        ArrayList<String> tmp = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("src/main/resources/advent2019/InputDay6"))) {
            while (scanner.hasNext()) {
                tmp.add(scanner.nextLine());
            }
        }
        inputs = tmp.toArray(inputs);

        LinkedListMultimap<String, String> orbitChains = LinkedListMultimap.create();

        for (String input : inputs) {
            String[] parts = StringUtils.split(input, ')');
            orbitChains.put(parts[0], parts[1]);
        }

//        for (String key : orbitChains.keySet()) {
//            System.out.println(key + " -- " + orbitChains.get(key));
//        }

//        System.out.println("Orbit chain length for [B] is " + findChainLength(orbitChains, "B"));
//        System.out.println("Orbit chain length for [D] is " + findChainLength(orbitChains, "D"));
//        System.out.println("Orbit chain length for [L] is " + findChainLength(orbitChains, "L"));
//        System.out.println("Orbit chain length for [COM] is " + findChainLength(orbitChains, "COM"));
        countDirectAndIndirectOrbits(orbitChains);
        List<String> yourPath = findChain(orbitChains, "YOU");
        List<String> sanPath = findChain(orbitChains, "SAN");
        System.out.println("Chain YOU: " + yourPath);
        System.out.println("Chain SAN: " + sanPath);

        //  Intersect chains
        Set<String> transfers = Sets.intersection(Sets.newHashSet(yourPath), Sets.newHashSet(sanPath));
        System.out.println("Transfers : " + transfers);

        int numTransfers = (yourPath.size() - transfers.size()) + (sanPath.size() - transfers.size());
        System.out.println("Num transfers = " + numTransfers);
    }

    private static void countDirectAndIndirectOrbits(LinkedListMultimap<String, String> orbitChains) {
        int total = 0;
        for (String key : orbitChains.values()) {
            total += findChainLength(orbitChains, key);
        }
        System.out.println("Total orbit count: " + total);
    }

    private static int findChainLength(LinkedListMultimap<String, String> orbitChains, String value) {
        if (value.equals("COM"))
            return 0;

        boolean searching = true;
        int searchCount = 0;
        while (searching) {

            for (String key : orbitChains.keySet()) {
                if (orbitChains.get(key).contains(value)) {
                    value = key;    //  Search up
                    break;
                }
            }

            if (value.equals("COM"))
                searching = false;
            searchCount++;
        }
        return searchCount;
    }

    private static List<String> findChain(LinkedListMultimap<String, String> orbitChains, String value) {
        ArrayList<String> chain = new ArrayList<>();
        if (value.equals("COM"))
            return chain;

        boolean searching = true;
        while (searching) {

            for (String key : orbitChains.keySet()) {
                if (orbitChains.get(key).contains(value)) {
                    value = key;    //  Search up
                    chain.add(value);
                    break;
                }
            }

            if (value.equals("COM"))
                searching = false;
        }
        return chain;
    }
}
