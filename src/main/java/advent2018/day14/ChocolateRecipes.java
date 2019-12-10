package advent2018.day14;

import java.util.ArrayList;

public class ChocolateRecipes {
    public static void main(String[] args) {
        String exampleInput = "37";
        String problemInput = "635041";
        ChocolateRecipes cr = new ChocolateRecipes(exampleInput);
        cr.run();
    }

    public ArrayList<Integer> recipes;

    public ChocolateRecipes(String input) {
        recipes = new ArrayList<>(input.length());
        for (char c : input.toCharArray()) {
            recipes.add(Integer.parseInt("" + c));
        }
        System.out.println("Input: " + recipes.toString());
    }

    public String scoresAfterNRecipes(int n) {
        int elfOneIndex = 0;
        int elfTwoIndex = 1;
        int nextScore = recipes.get(elfOneIndex) + recipes.get(elfTwoIndex);

        if (nextScore >= 10) {

        } else {

        }
        return null;
    }
}
