package advent2021.day06;

import java.util.HashMap;
import java.util.Map;

public class LanternFish {

    private LongMultiset fish = new LongMultiset();

    public LanternFish() {

    }

    public LanternFish givenInitialState(String state) {
        String[] parts = state.split(",");
        for (String part : parts) {
            fish.add(Integer.parseInt(part));
        }
//        System.out.println("Initial state: " + fish.toString());
        return this;
    }

    public LanternFish after(int days) {
        for (int i = 0; i < days; i++) {
            simulateDay(i);
        }
        return this;
    }

    private void simulateDay(int day) {
        long newFish = 0;
        long newParents = 0;

        for (int i = 0; i <= 8; i++) {
            if (fish.contains(i)) {
                long count = fish.count(i);
                fish.remove(i);

                if (i == 0) {
                    newFish = count;
                    newParents = count;
                } else {
                    fish.add(i - 1, count);
                }
            }
        }

        fish.add(6, newParents);
        fish.add(8, newFish);
//        System.out.println("Day " + day + ": " + fish.size());
    }

    public Long countFish() {
        return fish.size();
    }

    class LongMultiset {
        private Map<Integer, Long> map = new HashMap<>();

        public boolean contains(int key) {
            return map.containsKey(key);
        }

        public boolean add(int key) {
            if (map.containsKey(key)) {
                long existingCount = map.get(key);
                map.put(key, ++existingCount);
            } else {
                map.put(key, 1L);
            }
            return true;
        }

        public boolean add(int key, long count) {
            if (map.containsKey(key)) {
                long existingCount = map.get(key);
                map.put(key, existingCount + count);
                return true;
            } else {
                map.put(key, count);
                return true;
            }
        }

        public Long count(int key) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
            return 0L;
        }

        public Long size() {
            long count = 0;
            for (int key : map.keySet()) {
                count += map.get(key);
            }
            return count;
        }

        public void remove(int key) {
            map.remove(key);
        }
    }
}
