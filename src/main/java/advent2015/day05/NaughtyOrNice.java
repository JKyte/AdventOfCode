package advent2015.day05;

import java.util.List;

public class NaughtyOrNice {

    public NaughtyOrNice() {

    }

    public int countNiceStrings(List<String> strings) {
        int niceCount = 0;
        for (String s : strings) {
            if (isNice(s)) {
                niceCount++;
            }
        }
        return niceCount;
    }

    public boolean isNice(String s) {
        return containsThreeVowels(s) && containsDoubleChar(s) && !containsBadPair(s);
    }

    public boolean containsDoubleChar(String s) {
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private final String vowels = "aeiou";

    public boolean containsThreeVowels(String s) {
        int containedVowels = 0;
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) > -1) {
                containedVowels++;
            }
        }
        return containedVowels >= 3;
    }

    private final String[] badPairs = new String[]{"ab", "cd", "pq", "xy"};

    public boolean containsBadPair(String s) {
        for (String badPair : badPairs) {
            if (s.contains(badPair)) {
                return true;
            }
        }
        return false;
    }

    public int countNiceStrings2(List<String> strings) {
        int niceCount = 0;
        for (String s : strings) {
            if (isNice2(s)) {
                niceCount++;
            }
        }
        return niceCount;
    }

    public boolean isNice2(String s) {
        return containsTwoNonOverlapping(s) && containsRepeat(s);
    }

    public boolean containsTwoNonOverlapping(String s) {
        int len = s.length();
        if (len < 4) {
            return false;
        }
        for (int i = 0; i < len - 3; i++) {
            String sub = s.substring(i, i + 2);
            if (s.indexOf(sub, i + 2) > -1) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRepeat(String s) {
        int len = s.length();
        for (int i = 0; i < len - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }


}
