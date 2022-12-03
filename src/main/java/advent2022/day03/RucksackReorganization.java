package advent2022.day03;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RucksackReorganization {

    private final String lowerUpper = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public RucksackReorganization() {

    }

    public int rucks(List<String> rucksacks) {
        int priority = 0;
        for (String ruck : rucksacks) {
            priority += fixRuck(ruck);
        }
        return priority;
    }

    public int fixRuck(String ruck) {
        int index = ruck.length() / 2;
        String left = ruck.substring(0, index);
        String right = ruck.substring(index);

        Set<Character> leftSet = ruckToSet(left);
        Set<Character> rightSet = ruckToSet(right);

        Set<Character> s = Sets.intersection(leftSet, rightSet);
        if (!s.isEmpty()) {
            char c = s.iterator().next();
            return lowerUpper.indexOf(c) + 1;
        }
        throw new IllegalStateException("expected to find a duplicate character but did not");
    }

    public int badge(List<String> rucks) {
        int n = 0;
        for (int i = 0; i < rucks.size(); i += 3) {
            n += findBadge(rucks.get(i), rucks.get(i + 1), rucks.get(i + 2));
        }
        return n;
    }

    public int findBadge(String r1, String r2, String r3) {
        Set<Character> s1 = ruckToSet(r1);
        Set<Character> s2 = ruckToSet(r2);
        Set<Character> s3 = ruckToSet(r3);
        Set<Character> combined = Sets.intersection(s1, s2);
        combined = Sets.intersection(combined, s3);

        if (!combined.isEmpty()) {
            char c = combined.iterator().next();
            return lowerUpper.indexOf(c) + 1;
        }
        throw new IllegalStateException("expected to find a duplicate character but did not");
    }

    private Set<Character> ruckToSet(String ruck) {
        Set<Character> set = new HashSet<>();
        for (char c : ruck.toCharArray()) {
            set.add(c);
        }
        return set;
    }
}
