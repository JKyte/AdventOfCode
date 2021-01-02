package advent2020.day13;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ShuttleSearch {

    int start;
    int[] shuttles;

    public void loadInputs(String input) {
        String[] parts = input.split("\n");

        this.start = Integer.parseInt(parts[0]);

        List<Integer> ints = new ArrayList<>();
        for (String s : StringUtils.split(parts[1], ',')) {
            if (!s.equals("x")) {
                ints.add(Integer.parseInt(s));
            } else {
                ints.add(-1);
            }
        }

        shuttles = new int[ints.size()];
        for (int i = 0; i < ints.size(); i++) {
            shuttles[i] = ints.get(i);
        }
    }

    //  Return the shuttle ID of the first shuttle to arrive after the start.
    public int findFirstShuttle() {
        int lowIndex = -1;
        long low = Integer.MAX_VALUE;

        for (int i = 0; i < shuttles.length; i++) {
            if (shuttles[i] == -1) continue;
            long deltaT = getNextShuttle(start, shuttles[i]);
            if (deltaT < low) {
                low = deltaT;
                lowIndex = i;
            }
        }
        return shuttles[lowIndex];
    }

    public long getNextShuttle(long time, int shuttleId) {
        long multiple = (time / shuttleId) + 1L;
        long nextStop = multiple * shuttleId;
        return nextStop - time;
    }

    public long getTimeToWait(int shuttleId) {
        return getTimeToWait(start, shuttleId);
    }

    public long getTimeToWait(long current, int shuttleId) {
        return getNextShuttle(current, shuttleId);
    }

    public long getShuttleHash() {
        int nextShuttle = findFirstShuttle();
        long waitTime = getTimeToWait(start, nextShuttle);
        return nextShuttle * waitTime;
    }

    /**
     * knew we were headed this way, almost had it but used comment from reddit as a guide
     * <p>
     * https://www.reddit.com/r/adventofcode/comments/kc4njx/2020_day_13_solutions/ghf18ej/?utm_source=reddit&utm_medium=web2x&context=3
     */
    public long crt() {
        long delta = 1L;
        long timestamp = 0L;
        for (int offset = 0; offset < shuttles.length; offset++) {
            if (shuttles[offset] != -1) {
                while (true) {
                    timestamp += delta;
                    if ((timestamp + offset) % shuttles[offset] == 0) {
                        //  Because the solution is a pairwise modulo we can increment the
                        //  jump delta by the shuttle value
                        delta *= shuttles[offset];
                        break;
                    }
                }
            }
        }
        return timestamp;
    }
}
