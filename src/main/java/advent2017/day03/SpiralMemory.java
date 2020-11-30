package advent2017.day03;

public class SpiralMemory {

    public SpiralMemory() {

    }

    public int getSteps(int data) {
        if (data == 1) {
            return 0;
        }

        //  Size of side
        int size = 1;
        int current = 1;
        int total = 1;
        do {
            size += 2;
            current = getCountForRing(size);
            total += current;

        } while (total < data);

        return calculateSteps(data, size, current, total);
    }

    private int getCountForRing(int size) {
        int smaller = size - 2;
        return size + size + smaller + smaller;
    }

    private int calculateSteps(int data, int size, int current, int total) {
        int start = total - current + 1;
        return 0;
    }
}
