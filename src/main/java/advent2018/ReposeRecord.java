package advent2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Solution for https://adventofcode.com/2018/day/4
 * <p>
 * Part 1: Guard 1021 was asleep most often in minute 30, hash is 30630
 * <p>
 * Part 2: Worst guard 3331 on minute 41 with hash 136571
 */
public class ReposeRecord implements Runnable {

    public static void main(String[] args) {
        ReposeRecord rr = new ReposeRecord();
        rr.run();
    }

    private String inputPath = "src/main/resources/advent2018/ReposeRecord.txt";
    private boolean partTwo = true;

    public ReposeRecord() {

    }

    @Override
    public void run() {
        Map<String, String> lines = readLines();
        Map<Integer, Guard> guards = parseLinesToGuards(lines);
        int guardId = findGuardWithMostTime(guards);
        int worstMinute = findWorstMinute(guards.get(guardId));

        System.out.println("Number of guards: " + guards.size());
        System.out.println("Sleepiest Guard: " + guardId);
        System.out.println("Worst minute: " + worstMinute);
        System.out.println("Hash: " + guardId * worstMinute);

        //  Part 2
        findWorstGuard(guards, worstMinute);
    }

    private Map<String, String> readLines() {
        Map<String, String> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            int splitIndex;
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                splitIndex = line.indexOf(']');
                String timestamp = line.substring(0, splitIndex);
                String event = line.substring(splitIndex + 2);
                map.put(timestamp, event);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    private Map<Integer, Guard> parseLinesToGuards(Map<String, String> lines) {
        Map<Integer, Guard> guards = new HashMap<>();
        Set<String> sortedKeys = new TreeSet<>(lines.keySet());
        int curKey = -1;
        for (String key : sortedKeys) {
            String event = lines.get(key);
            if (event.contains("Guard")) {
                curKey = Integer.parseInt(event.split(" ")[1].substring(1));
                if (guards.containsKey(curKey)) {
                    guards.get(curKey).shifts.add(key + "::" + event);
                } else {
                    Guard guard = new Guard(curKey);
                    guard.shifts.add(key + "::" + event);
                    guards.put(curKey, guard);
                }
            } else {
                guards.get(curKey).shifts.add(key + "::" + event);
            }
        }
        return guards;
    }

    private int findGuardWithMostTime(Map<Integer, Guard> guards) {
        int longestTimeAsleep = 0;
        int longestId = 0;
        for (int i : guards.keySet()) {
            int timeAsleep = calculateGuardTimeAsleep(guards, i);
            if (timeAsleep > longestTimeAsleep) {
                longestTimeAsleep = timeAsleep;
                longestId = i;
            }
        }
        System.out.println("Guard " + longestId + " was asleep for " + longestTimeAsleep + " minutes.");
        return longestId;
    }

    private int calculateGuardTimeAsleep(Map<Integer, Guard> guards, int key) {
        int timeAsleep = 0;
        int startAsleep = 0;
        for (String event : guards.get(key).shifts) {
            String[] splits = event.split("::");
            String hm = splits[0].split(" ")[1];
            int hr = Integer.parseInt(hm.split(":")[0]);
            int min = Integer.parseInt(hm.split(":")[1]);
            boolean asleep = splits[1].contains("asleep");
            boolean wakes = splits[1].contains("wakes");
            if (hr == 0) {
                if (asleep) {
                    startAsleep = min;
                }
                if (wakes) {
                    for (int ii = startAsleep; ii < min; ii++) {
                        guards.get(key).sleepSchedule[ii]++;
                        timeAsleep++;
                    }
                }
            }
        }
        System.out.println("Guard " + guards.get(key).guardId + " spent " + timeAsleep + " mins asleep");
        return timeAsleep;
    }

    private int findWorstMinute(Guard guard) {
        int worstMinute = 0;
        int worstCount = 0;
        for (int ii = 0; ii < guard.sleepSchedule.length; ii++) {
            if (guard.sleepSchedule[ii] > worstCount) {
                worstCount = guard.sleepSchedule[ii];
                worstMinute = ii;
            }
        }
        return worstMinute;
    }

    private void findWorstGuard(Map<Integer, Guard> guards, int worstMinute) {
        int worstCount = 0;
        int worstMin = 0;
        int worstGuardId = 0;
        for (Guard guard : guards.values()) {
            int curWorstMin = findWorstMinute(guard);
            int curWorstCount = guard.sleepSchedule[curWorstMin];
            if (curWorstCount > worstCount) {
                worstCount = curWorstCount;
                worstMin = curWorstMin;
                worstGuardId = guard.guardId;
            }
        }
        System.out.println("Worst guard " + worstGuardId + " on minute " + worstMin + " with hash " + worstGuardId * worstMin);
    }

    public class Guard {

        int guardId;
        List<String> shifts;
        int[] sleepSchedule;

        public Guard(int guardId) {
            this.guardId = guardId;
            this.shifts = new LinkedList<>();
            this.sleepSchedule = new int[60];
        }
    }
}
