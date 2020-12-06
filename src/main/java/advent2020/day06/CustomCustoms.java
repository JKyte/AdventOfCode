package advent2020.day06;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class CustomCustoms {

    public CustomCustoms() {
    }

    public int countGroupsAny(String input) {
        int count = 0;
        String[] groups = input.split("\n\n");
        for (String group : groups) {
            count += countGroupAny(group);
        }
        return count;
    }

    private int countGroupAny(String group) {
        int count = 0;
        Set<Character> chars = new HashSet<>();
        String[] passengers = StringUtils.split(group, '\n');
        for (String p : passengers) {
            for (char c : p.toCharArray()) {
                if (chars.add(c)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countGroupsAll(String input) {
        int count = 0;
        String[] groups = input.split("\n\n");
        for (String group : groups) {
            count += countGroupAll(group);
        }
        return count;
    }

    private int countGroupAll(String group) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Set<Character> all = new HashSet<>();
        for (char c : alphabet.toCharArray()) {
            all.add(c);
        }

        String[] passengers = StringUtils.split(group, '\n');
        for (String p : passengers) {
            Set<Character> chars = new HashSet<>();
            for (char c : p.toCharArray()) {
                chars.add(c);
            }
            all = Sets.intersection(all, chars);
        }
        return all.size();
    }
}
