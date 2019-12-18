package advent2018.day14;

import java.util.ArrayList;

/**
 * Part 1: 1150511382
 * <p>
 * Part 2:
 * 13341098 -- too low.
 * 37144408
 * 37144409 -- too high.
 * 37144410 -- too high.
 */
public class ChocolateRecipes {

    public static void main(String[] args) {
        String input = "37";  //  Example input
        ChocolateRecipes cr = new ChocolateRecipes(input);
//        System.out.println("N =    9: " + cr.scoresAfterNRecipes(9));   //  5158916779
//        System.out.println("N =    5: " + cr.scoresAfterNRecipes(5));   //  0124515891
//        System.out.println("N =   18: " + cr.scoresAfterNRecipes(18));  //  9251071085
//        System.out.println("N = 2018: " + cr.scoresAfterNRecipes(2018));//  5941429882
//        System.out.println("Part 1: " + cr.scoresAfterNRecipes(635041));//  1150511382

//        System.out.println("N =    9: " + cr.scoresLeftOfMatch("51589"));   //  9
//        System.out.println("N =    5: " + cr.scoresLeftOfMatch("01245"));   //  5
//        System.out.println("N =   18: " + cr.scoresLeftOfMatch("92510"));  //  18
//        System.out.println("N = 2018: " + cr.scoresLeftOfMatch("59414"));//  2018
        System.out.println("Part 2: " + cr.scoresLeftOfMatch("635041"));// ?
//        System.out.println("Print last: " + cr.printLastN(20));
//        System.out.println(cr.recipes.size());
    }

    private String printLastN(int n) {
        StringBuilder sb = new StringBuilder();
        int len = recipes.size() - n;
        for (int ii = recipes.size(); ii > len; ii--) {
            sb.append((int)Integer.parseInt(""+recipes.get(ii-1)));
        }
        return sb.reverse().toString();
    }

    private String input;
    public ArrayList<Integer> recipes;

    public ChocolateRecipes(String input) {
        this.input = input;
    }

    public void primeIt() {
        recipes = new ArrayList<>();
        for (char c : input.toCharArray()) {
            recipes.add(Integer.parseInt("" + c));
        }
    }

    public String scoresAfterNRecipes(int n) {
        primeIt();
        int elfOneIndex = 0;
        int elfTwoIndex = 1;
//        System.out.println(buildPrintString(elfOneIndex, elfTwoIndex));
        int target = 12 + n;
        while (recipes.size() <= target) {
            int nextScore = recipes.get(elfOneIndex) + recipes.get(elfTwoIndex);

            if (nextScore >= 10) {
                recipes.add(1);
                recipes.add(nextScore - 10);
            } else {
                recipes.add(nextScore);
            }

            //  Move the elves forward.
            elfOneIndex += recipes.get(elfOneIndex) + 1;
            elfTwoIndex += recipes.get(elfTwoIndex) + 1;

            if (elfOneIndex >= recipes.size())
                elfOneIndex = elfOneIndex % recipes.size();
            if (elfTwoIndex >= recipes.size())
                elfTwoIndex = elfTwoIndex % recipes.size();
//            System.out.println(buildPrintString(elfOneIndex, elfTwoIndex));
        }
//        System.out.println("Final: " + buildPrintString(elfOneIndex, elfTwoIndex));

        StringBuilder sb = new StringBuilder();
        int limit = n + 10;
        for (int ii = n; ii < limit; ii++) {
            sb.append(recipes.get(ii));
        }
        return sb.toString();
    }

    public int scoresLeftOfMatch(String match) {
        primeIt();
        int elfOneIndex = 0;
        int elfTwoIndex = 1;
        boolean running = true;
//        StringBuilder sb = new StringBuilder();
//        sb.append(recipes.get(0)).append(recipes.get(1));
        while (running) {
            int nextScore = recipes.get(elfOneIndex) + recipes.get(elfTwoIndex);

            if (nextScore >= 10) {
                recipes.add(1);
                recipes.add(nextScore - 10);
//                sb.append(1);
//                sb.append(nextScore - 10);
            } else {
                recipes.add(nextScore);
//                sb.append(nextScore);
            }

            //  Move the elves forward.
            elfOneIndex += recipes.get(elfOneIndex) + 1;
            elfTwoIndex += recipes.get(elfTwoIndex) + 1;

            if (elfOneIndex >= recipes.size())
                elfOneIndex = elfOneIndex % recipes.size();
            if (elfTwoIndex >= recipes.size())
                elfTwoIndex = elfTwoIndex % recipes.size();
//            System.out.println(buildPrintString(elfOneIndex, elfTwoIndex));

            if (recipes.size() > 5) {
                int size = recipes.size() - 1;
                boolean matchFound = true;
                for (int ii = match.length() - 1; ii >= 0; ii--) {
                    if (recipes.get(size) != Integer.parseInt("" + match.charAt(ii))) {
                        matchFound = false;
                        break;
                    }
                    size--;
                }
                if (matchFound) {
                    running = false;
                }
            }
        }
        return recipes.size() - match.length();
    }

    //  used for debug
    public String buildPrintString(int elfOne, int elfTwo) {
        StringBuilder sb = new StringBuilder();
        for (int ii = 0; ii < recipes.size(); ii++) {
            if (ii == elfOne) {
                sb.append("(");
                sb.append(recipes.get(ii));
                sb.append(")");

            } else if (ii == elfTwo) {
                sb.append("[");
                sb.append(recipes.get(ii));
                sb.append("]");

            } else {
                sb.append(recipes.get(ii)).append(" ");
            }
        }
        return sb.toString();
    }
}
