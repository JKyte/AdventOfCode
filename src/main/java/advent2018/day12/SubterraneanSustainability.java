package advent2018.day12;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1582 is too low
 * <p>
 * Need to mimic not having pots past the bounds of the array.
 * <p>
 * You see a row of pots, extending as far as the eye can see.
 *
 * Part 1 = 2040
 *
 * Part 2 = Automoton breaks down and it should be easy to predict where it is at 50 billion iterations.
 *
 * Gen 191 is where it levels off.
 *
 * 191 = 6505 + 34 each gen
 * !1,699,999,785,335
 *
 * 50000000000 - 50 B
 * gen 200 - sum 6811
 *
 * ((50000000000 - 200 ) * 34 ) + 6811 = sum
 * 1699999993200 + 6811
 *
 * = 1699999993200 -- too low
 *
 * 1,700,000,000,011
 * 1700000000011‬‬ -- too low -- i think i'm off by one.
 *
 *
 * 35 gliders -- confirm tomorrow
 */
public class SubterraneanSustainability {

    public static void main(String[] args) {
        //  Example Input
//        String initialState = "#..#.#..##......###...###";
//        String rules = "...## => #\n..#.. => #\n.#... => #\n.#.#. => #\n.#.## => #\n.##.. => #\n.#### => #\n#.#.# => #\n#.### => #\n##.#. => #\n##.## => #\n###.. => #\n###.# => #\n####. => #";

        //  Part 1 Input
        String initialState = "###....#..#..#......####.#..##..#..###......##.##..#...#.##.###.##.###.....#.###..#.#.##.#..#.#";
        String rules = "..### => #\n" +
                        "..... => .\n" +
                        "..#.. => .\n" +
                        ".###. => .\n" +
                        "...## => #\n" +
                        "#.### => .\n" +
                        "#.#.# => #\n" +
                        "##..# => .\n" +
                        "##.## => #\n" +
                        "#...# => .\n" +
                        "..##. => .\n" +
                        "##.#. => .\n" +
                        "...#. => .\n" +
                        "#..#. => #\n" +
                        ".#### => #\n" +
                        ".#..# => #\n" +
                        "##... => #\n" +
                        ".##.# => .\n" +
                        "....# => .\n" +
                        "#.... => .\n" +
                        ".#.#. => #\n" +
                        ".##.. => .\n" +
                        "###.# => #\n" +
                        "####. => .\n" +
                        "##### => #\n" +
                        "#.##. => #\n" +
                        ".#... => #\n" +
                        ".#.## => #\n" +
                        "###.. => #\n" +
                        "#..## => .\n" +
                        "#.#.. => #\n" +
                        "..#.# => .";

//                "..### => #\n..... => .\n..#.. => .\n.###. => .\n" +
//                "...## => #\n#.### => .\n#.#.# => #\n##..# => .\n##.## => #\n" +
//                "#...# => .\n..##. => .\n##.#. => .\n...#. => .\n#..#. => #\n" +
//                ".#### => #\n.#..# => #\n##... => #\n.##.# => .\n....# => .\n" +
//                "#.... => .\n.#.#. => #\n.##.. => .\n###.# => #\n####. => .\n" +
//                "##### => #\n#.##. => #\n.#... => #\n.#.## => #\n###.. => #\n" +
//                "#..## => .\n#.#.. => #\n..#.# => .";


        System.out.println("Initial state: " + initialState.length());
        SubterraneanSustainability ss = new SubterraneanSustainability(initialState, rules, 200);
        String endState = ss.applyRules();
        System.out.println("Final State    : " + endState);
        System.out.println("Expected       : .#....##....#####...#######....#.#..##.");

        System.out.println("Pots: " + ss.countPots(endState));
//        System.out.println("Pots: " + ss.countPots(".#....##....#####...#######....#.#..##."));
    }

    private String input;
    private List<Rule> rules;
    private int numGenerations;
    private String[] expected = {
//            "...#..#.#..##......###...###...........",
            "...#...#....#.....#..#..#..#...........",
            "...##..##...##....#..#..#..##..........",
            "..#.#...#..#.#....#..#..#...#..........",
            "...#.#..#...#.#...#..#..##..##.........",
            "....#...##...#.#..#..#...#...#.........",
            "....##.#.#....#...#..##..##..##........",
            "...#..###.#...##..#...#...#...#........",
            "...#....##.#.#.#..##..##..##..##.......",
            "...##..#..#####....#...#...#...#.......",
            "..#.#..#...#.##....##..##..##..##......",
            "...#...##...#.#...#.#...#...#...#......",
            "...##.#.#....#.#...#.#..##..##..##.....",
            "..#..###.#....#.#...#....#...#...#.....",
            "..#....##.#....#.#..##...##..##..##....",
            "..##..#..#.#....#....#..#.#...#...#....",
            ".#.#..#...#.#...##...#...#.#..##..##...",
            "..#...##...#.#.#.#...##...#....#...#...",
            "..##.#.#....#####.#.#.#...##...##..##..",
            ".#..###.#..#.#.#######.#.#.#..#.#...#..",
            ".#....##....#####...#######....#.#..##."
    };

    String pad = "........................................................................................................................" +
            "........................................................................................................................";

    public SubterraneanSustainability(String input, String rawRules, int numGenerations) {
        this.input = pad + input + pad;
        this.rules = buildRules(rawRules);
        for (Rule rule : rules) {
            System.out.println("Rule: " + rule.toString());
        }
        this.numGenerations = numGenerations;
    }

    private List<Rule> buildRules(String rawRules) {
        String[] ruleChunks = StringUtils.split(rawRules, '\n');
        List<Rule> rules = new ArrayList<>(ruleChunks.length);
        for (String chunk : ruleChunks) {
            rules.add(parseIntoRule(chunk));
        }
        return rules;
    }

    int prevPotCount = 0;

    public String applyRules() {
        String state = input;
        System.out.println("State at gen 0 : " + state);
        for (int ii = 1; ii <= numGenerations; ii++) {
            state = applyRulesInner(state);
            int pots = countPots(state);
            int delta = pots - prevPotCount;
            if (ii < 10) {
                System.out.println(pots + "  " + delta + "  State at gen " + ii + " : " + state);
            } else {
                System.out.println(pots + "  " + delta + "  State at gen " + ii + ": " + state);
            }
            prevPotCount = pots;
//            boolean equality = state.contains(expected[ii - 1]);
//            System.out.println(equality);
//            if (!equality) {
//                System.out.println("State at gen " + ii + ": " + expected[ii - 1] + "\n");
//                System.exit(0);
//            }
        }

        return state;
    }

    private String applyRulesInner(String state) {
        //  Chunk through the state, apply rules as necessary.
//        System.out.println("Apply rules to state: " + state);
        char[] chars = new char[state.length()];
        Arrays.fill(chars, '.');
        for (int ii = 0; ii < state.length() - 4; ii++) {
            String chunk = state.substring(ii, ii + 5);
//            System.out.println("Chunk: " + chunk);

            for (Rule rule : rules) {
                if (rule.matches(chunk)) {
//                    System.out.println(rule.toString());
                    chars[ii + 2] = rule.getResult().charAt(0);
                }
            }
        }
        String endState = new String(chars);
//        System.out.println("Expected : ...#...#....#.....#..#..#..#...........");
//        System.out.println("End State: " + endState);
        return endState;
    }
    //16: ...#..#...#.#...##...#...#.#..##..##...
    //16: .#.#..#...#.#...##...#...#.#..##..##...

    public Rule parseIntoRule(String input) {
//        System.out.println("rule input: " + input);
        String[] parts = StringUtils.split(input, ' ');
//        System.out.println("Parts: " + Arrays.toString(parts));
        return new Rule(parts[0], parts[2]);
    }

    public int countPots(String state) {
        int count = 0;
        char[] chars = state.toCharArray();
        for (int ii = 0; ii < chars.length; ii++) {
            if (chars[ii] == '#') {
                count += (ii - pad.length());  //  Account for our offset.
            }
        }
        return count;
    }


    private class Rule {
        public String template;
        public String result;

        public Rule(String template, String result) {
            this.template = template;
            this.result = result;
            if (template.length() != 5) {
                System.out.println("Failure: " + template + " " + template.length());
                System.exit(0);
            }
        }

        public boolean matches(String input) {
            if (input.equals(template)) {
                return true;
            }
            return false;
        }

        public String getResult() {
            return result;
        }

        @Override
        public String toString() {
            return template + " -> " + result;
        }
    }
}
