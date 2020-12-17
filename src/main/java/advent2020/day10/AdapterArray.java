package advent2020.day10;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterArray {

    private List<Integer> adaptors;
    private int[] backing;
    public int oneDiff = 0;
    public int threeDiff = 0;

    public long count = 0L;

    public AdapterArray() {

    }

    public void loadAdaptors(String input) {
        String[] inputs = StringUtils.split(input, ' ');
        adaptors = new ArrayList<>();
        adaptors.add(0);
        for (String s : inputs) {
            adaptors.add(Integer.parseInt(s));
        }
        Collections.sort(adaptors);
        adaptors.add(adaptors.get(adaptors.size() - 1) + 3);

        backing = new int[adaptors.size()];
        for (int i = 0; i < adaptors.size(); i++) {
            backing[i] = adaptors.get(i);
        }

        oneDiff = 0;
        threeDiff = 0;
        max = adaptors.size() - 1;
    }

    //  With a zero-list-max setup..
    public void countDifferences() {
        for (int i = 0; i < adaptors.size() - 1; i++) {
            int diff = adaptors.get(i + 1) - adaptors.get(i);
//            System.out.println(i + " diff between " + adaptors.get(i + 1) + " and " + adaptors.get(i) + " is " + diff);
            if (diff == 1) oneDiff++;
            if (diff == 3) threeDiff++;
        }
    }

    private int max = 0;

    private Map<Integer, Long> cache;

    public long countCombinationsV3() {
        this.count = 0L;
        this.cache = new HashMap<>();
        long total = countComboV3(0);
        return this.count;
    }

    private long countComboV3(int index) {
        if (index == max) return 1;

        long total;
        if (cache.containsKey(index)) {
            total = cache.get(index);
            count += total;
        } else {
            //  If this layer is not in the cache, time to count 'em up.
            total = 0L;
            for (int i = index + 1; i < index + 4; i++) {
                if (i >= backing.length) continue;
                if (backing[i] - backing[index] > 3) continue;

                total += countComboV3(i);
            }

            cache.put(index, total);
            this.count = total;
        }
        return total;
    }

    public int getMaxBuiltIn() {
        return adaptors.get(adaptors.size() - 1);
    }
}
