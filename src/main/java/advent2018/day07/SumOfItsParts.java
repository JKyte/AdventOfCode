package advent2018.day07;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution for
 * <p>
 * Part 1: Test -- CABDFE
 * Part 1: Production -- BHRTWCYSELPUVZAOIJKGMFQDXN
 * <p>
 * Part 2: Test -- CABFDE in 15 seconds
 * Part 2: Production -- BHRTWYZCSAELPUVOIMJKFGQDXN in 595 seconds
 */
public class SumOfItsParts implements Runnable {

    public static void main(String[] args) {
        SumOfItsParts soip = new SumOfItsParts();
        soip.run();
    }

    private String inputPath = "src/main/resources/advent2018/SumOfItsParts.txt";
    private String timeIndex = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int tick = 0;
    private int maxWorkers = 5;

    public SumOfItsParts() {
    }

    @Override
    public void run() {
        Set<String> steps = readInput();
        Multimap<String, String> requirements = parseRequirementsGraph(steps);
        Multimap<String, String> graph = parseStepsIntoGraph(steps);
//        String orderedSteps = dfsWithReqs(requirements, graph);
//        System.out.println("Ordered Steps: " + orderedSteps);
        String path = timedPathConstruction(requirements, graph);
        System.out.println("Ordered Steps: " + path);
    }

    private void printOrderedGraph(Multimap<String, String> graph) {
        System.out.println("Printing graph of " + graph.keySet().size() + " instructions...");
        TreeSet<String> keys = new TreeSet<>(graph.keySet());
        for (String key : keys) {
            StringBuilder sb = new StringBuilder();
            sb.append(key).append(":  ");

            TreeSet<String> values = new TreeSet<>(graph.get(key));
            sb.append(Joiner.on(",").join(values));
            System.out.println(sb.toString());
        }
    }

    private Set<String> readInput() {
        Set<String> lines = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private Multimap<String, String> parseRequirementsGraph(Set<String> steps) {
        Multimap<String, String> graph = HashMultimap.create();
        String[] split;
        for (String step : steps) {
            split = step.split(" ");
            graph.put(split[7], split[1]);
        }
        return graph;
    }

    private Multimap<String, String> parseStepsIntoGraph(Set<String> steps) {
        Multimap<String, String> graph = HashMultimap.create();
        String[] split;
        for (String step : steps) {
            split = step.split(" ");
            graph.put(split[1], split[7]);
        }
        return graph;
    }

    private String dfsWithReqs(Multimap<String, String> requirements, Multimap<String, String> graph) {
        Set<String> frontier = new TreeSet<>();
        Set<String> seen = new HashSet<>();
        Set<String> difference = Sets.difference(graph.keySet(), new HashSet<>(graph.values()));
        System.out.println("Differences: " + difference);

        StringBuilder sb = new StringBuilder();
        Iterator<String> iter = difference.iterator();
        while (iter.hasNext()) {
            String s = iter.next();
            sb.append(s);
            frontier.addAll(graph.get(s));
            seen.add(s);
        }

        String startKey = sb.toString();
        System.out.println("Start key is: " + startKey);
        return dfsWithReqs(requirements, graph, frontier, seen, startKey, startKey);
    }

    private String dfsWithReqs(Multimap<String, String> requirements,
                               Multimap<String, String> graph,
                               Set<String> frontier,
                               Set<String> seen,
                               String curKey,
                               String path) {

        System.out.println("===================\nSeen nodes: " + seen.toString());
        System.out.println("Frontier A: " + frontier);
        frontier.addAll(graph.get(curKey)); //  Add curKey.nextNodes
//        System.out.println("Frontier B: " + frontier);
        frontier = new HashSet<>(Sets.difference(frontier, seen)); //  Eliminate all seen keys.
        System.out.println("Frontier C: " + frontier);

        if (frontier.size() == 0) {
            System.out.println("Found the end, returning steps: " + path);
            return path;
        }

        System.out.println("Steps: " + path + " curKey: " + curKey + " nextKeys: " + Joiner.on(",").join(frontier));

        String key = null;
        TreeSet<String> nextKeys = new TreeSet<>(frontier); //  Order the frontier
        for (String nextKey : nextKeys) {
//            System.out.println("Evaluation next key: " + nextKey);
            //  Next key must have all requirements met.
            Collection<String> reqs = requirements.get(nextKey);
            if (seen.containsAll(reqs)) {
                key = nextKey;
                break;
            }
        }
        if (key == null) {
            System.out.println("Key was null, returning path: " + path);
            return path;
        }
        seen.add(key);
        return dfsWithReqs(requirements, graph, frontier, seen, key, path + key);
    }

    private String timedPathConstruction(Multimap<String, String> requirements,
                                         Multimap<String, String> graph) {
        Set<String> difference = Sets.difference(graph.keySet(), new HashSet<>(graph.values()));
        Set<String> frontier = new TreeSet<>(difference);
        Set<String> seen = new TreeSet<>();
        String path = "";
        Multimap<Integer, String> workers = HashMultimap.create();
        System.out.println("Starting frontier is: " + frontier.toString());

        boolean working = true;
        while (working) {
            //  1. Assign work
            //  2. Tick up
            //  3. Check for completed work
            //  4. Expand frontier, filter for seen & completed work
            //  5. Check for done condition

//            System.out.println("[TICK: " + tick + " ]==============");
//            System.out.println("Path: " + path);
//            System.out.println("Seen nodes: " + seen.toString());
//            System.out.println("Frontier A: " + frontier);

            //  1. Assign work to free workers
            //  Copy frontier keys so we can remove them
            TreeSet<String> nextKeys = new TreeSet<>(frontier);
            for (String nextKey : nextKeys) {
                //  Must have free workers & met all required steps.
                boolean isWorkerFree = workers.values().size() < this.maxWorkers;
                boolean reqsAreMet = seen.containsAll(requirements.get(nextKey));
                if (isWorkerFree && reqsAreMet) {
                    int completeTime = tick + time(nextKey);
                    workers.put(completeTime, nextKey);
                    frontier.remove(nextKey);
//                    System.out.println("Worker " + workers.values().size() + " began work on step " + nextKey + " and will complete at tick " + completeTime);
                }
            }

            //  1.5 Print output
            String status = getStatusString(workers, path, frontier);
            System.out.println(status);

            //  2. Tick up
            this.tick++;

            //  3. Check for completed work
//            printWorkers(workers);
            TreeSet<String> completed = new TreeSet<>(workers.removeAll(tick));
            if (completed.size() > 0) {
//                System.out.println("Work completed on step(s): " + completed.toString());
                seen.addAll(completed);
                for (String complete : completed) {
                    path += complete;
                }
            }

            //  4. Expand frontier and filter down.
            //  Add all possible next states to the frontier
            for (String seenKey : seen) {
                frontier.addAll(graph.get(seenKey));
            }
            //  Filter out all seen keys and current working keys
            frontier = new TreeSet<>(Sets.difference(frontier, seen));
            frontier = new TreeSet<>(Sets.difference(frontier, new HashSet<>(workers.values())));


            //  5. Check for stop work.
            if (workers.values().size() == 0 && frontier.size() == 0 && seen.size() == path.length()) {
                working = false;
//                break;
            }
        }
        System.out.println("Path construction took " + tick + " ticks.");
        return path;
    }

    private String getStatusString(Multimap<Integer, String> workers, String path, Set<String> frontier) {
        StringBuilder sb = new StringBuilder();
        sb.append(tick).append("\t");
        for (int key : workers.keySet()) {
            for (String value : new TreeSet<>(workers.get(key))) {
                sb.append(value).append("\t");
            }
        }
        for (int ii = workers.values().size(); ii < maxWorkers; ii++) {
            sb.append(".\t");
        }
        sb.append(path).append("\t").append(frontier.toString());
        return sb.toString();
    }

    private int time(String s) {
//        return timeIndex.indexOf(s);
        return 60 + timeIndex.indexOf(s);
    }
}
