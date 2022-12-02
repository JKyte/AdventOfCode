package advent2015.day05;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NaughtyOrNiceTest {

    @Test
    public void exampleOne() {
        NaughtyOrNice finder = new NaughtyOrNice();
        //  It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
        assertTrue(finder.containsThreeVowels("aei"));
        assertTrue(finder.containsThreeVowels("xazegov"));
        assertTrue(finder.containsThreeVowels("aeiouaeiouaeiou"));

        //  It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
        assertTrue(finder.containsDoubleChar("xx"));
        assertTrue(finder.containsDoubleChar("abcdde"));
        assertTrue(finder.containsDoubleChar("aabbccdd"));
        assertTrue(finder.containsDoubleChar("abcdd"));

        //  It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
        assertTrue(finder.containsBadPair("aab"));
        assertTrue(finder.containsBadPair("cdd"));
        assertTrue(finder.containsBadPair("ppqq"));
        assertTrue(finder.containsBadPair("xxxyyy"));

        //  actual naughty/nice strings
        assertTrue(finder.isNice("ugknbfddgicrmopn"));
        assertTrue(finder.isNice("aaa"));
        assertFalse(finder.isNice("jchzalrnumimnmhp"));
        assertFalse(finder.isNice("haegwjzuvuyypxyu"));
        assertFalse(finder.isNice("dvszwmarrgswjxmb"));
    }

    @Test
    public void partOne() throws FileNotFoundException {
        List<String> lines = readLines();
        NaughtyOrNice finder = new NaughtyOrNice();
        assertEquals(236, finder.countNiceStrings(lines));    //  165 to low
    }

    @Test
    public void exampleTwo() {
        NaughtyOrNice finder = new NaughtyOrNice();
        /*
It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
         */

        assertTrue(finder.containsTwoNonOverlapping("xyxy"));   //  xy repeats
        assertTrue(finder.containsTwoNonOverlapping("aabcdefgaa")); //  aa repeats

        //  contains repeat
        assertTrue(finder.containsRepeat("xyx"));
        assertTrue(finder.containsRepeat("abcdefeghi"));
        assertTrue(finder.containsRepeat("aaa"));

        //  nice check
        assertTrue(finder.isNice2("qjhvhtzxzqqjkmpb"));
        assertTrue(finder.isNice2("xxyxx"));
        assertFalse(finder.isNice2("uurcxstgmygtbstg"));
        assertFalse(finder.isNice2("ieodomkazucvgmuy"));
    }

    @Test
    public void partTwo() throws FileNotFoundException {
        List<String> lines = readLines();
        NaughtyOrNice finder = new NaughtyOrNice();
        assertEquals(51, finder.countNiceStrings2(lines));
    }

    private List<String> readLines() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/main/resources/advent2015/NaughtyOrNice.txt"))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

}