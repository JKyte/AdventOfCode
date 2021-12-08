package advent2021.day08;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.List;

public class SevenSegmentSearch {

    private Multiset<Integer> identified = HashMultiset.create();

    public SevenSegmentSearch() {

    }

    public SevenSegmentSearch identify(List<String> segments) {
        for (String segment : segments) {
            identifySegment(segment);
        }
        return this;
    }

    //  0   6   abcefg
    //  1   2   cf
    //  2   5   acdeg
    //  3   5   acdfg
    //  4   4   bcdf
    //  5   5   abdfg
    //  6   6   abdefg
    //  7   3   acf
    //  8   7   abcdefg
    //  9   6   abcdfg

    private void identifySegment(String segment) {
        String[] parts = segment.split(" \\| ");

        for (String s : parts[1].split(" ")) {
            switch (s.length()) {
                case 2:
                    identified.add(1);
                    break;
                case 4:
                    identified.add(4);
                    break;
                case 3:
                    identified.add(7);
                    break;
                case 7:
                    identified.add(8);
                    break;
                default:
                    //  do nothing by default
                    break;
            }
        }
    }

    public int count(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            if (identified.contains(number)) {
                sum += identified.count(number);
            }
        }
        return sum;
    }
}
