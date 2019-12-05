package advent2018.day12;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubterraneanSustainability {

    public static void main(String[] args) {
        //  Example Input
        String initialState = "...#..#.#..##......###...###...........";
        String rules = "...## => #\n..#.. => #\n.#... => #\n.#.#. => #\n.#.## => #\n.##.. => #\n.#### => #\n#.#.# => #\n#.### => #\n##.#. => #\n##.## => #\n###.. => #\n###.# => #\n####. => #";

        //  Part 1 Input
//        String initialState = "###....#..#..#......####.#..##..#..###......##.##..#...#.##.###.##.###.....#.###..#.#.##.#..#.#";
//        String rules = "..### => #\n..... => .\n..#.. => .\n.###. => .\n" +
//                "...## => #\n#.### => .\n#.#.# => #\n##..# => .\n##.## => #\n" +
//                "#...# => .\n..##. => .\n##.#. => .\n...#. => .\n#..#. => #\n" +
//                ".#### => #\n.#..# => #\n##... => #\n.##.# => .\n....# => .\n" +
//                "#.... => .\n.#.#. => #\n.##.. => .\n###.# => #\n####. => .\n" +
//                "##### => #\n#.##. => #\n.#... => #\n.#.## => #\n###.. => #\n" +
//                "#..## => .\n#.#.. => #\n..#.# => .";


        System.out.println("Initial state: " + initialState.length());
        SubterraneanSustainability ss = new SubterraneanSustainability(initialState, rules, 20);
        String endState = ss.applyRules();
        System.out.println("Final State    : " + endState);
        System.out.println("Expected       : .#....##....#####...#######....#.#..##.");
    }

    private String input;
    private List<Rule> rules;
    private int numGenerations;

    public SubterraneanSustainability(String input, String rawRules, int numGenerations) {
        this.input = input;
        this.rules = buildRules(rawRules);
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

    public String applyRules() {
        String state = input;
        System.out.println("State at gen 0 : " + state);
        for (int ii = 1; ii <= numGenerations; ii++) {
            state = applyRulesInner(state);
            if (ii < 10) {
                System.out.println("State at gen " + ii + " : " + state);
            } else {
                System.out.println("State at gen " + ii + ": " + state);
            }
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

    public Rule parseIntoRule(String input) {
        System.out.println("rule input: " + input);
        String[] parts = StringUtils.split(input, ' ');
        System.out.println("Parts: " + Arrays.toString(parts));
        return new Rule(parts[0], parts[2]);
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
