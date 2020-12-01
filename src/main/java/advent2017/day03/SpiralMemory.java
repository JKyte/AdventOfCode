package advent2017.day03;

public class SpiralMemory {

    public SpiralMemory() {
    }

    public int getSteps(int data) {

        //  1. Find the ring
        int ring = getRing(data);
        int midpointOffset = getMidpointOffset(ring, data);

        //  Distance is ring + data distance from midpoint.
        return ring + midpointOffset;
    }

    private int getRing(int data) {
        int ring = -1;
        for (int i = 0; i < 500; i++) {
            int lengthOfSize = getLengthOfSide(i);
            int size = lengthOfSize * lengthOfSize;
            if (size >= data) {
                ring = i;
                break;
            }
        }
        return ring;
    }

    private int getMidpointOffset(int ring, int data) {
        int lengthOfSide = getLengthOfSide(ring);
        int midpoint = midPoint(lengthOfSide);

        //  First, check to see if the data is at a midpoint
        if (data % midpoint == 0) {
            return 0;
        }

        int startData = lengthOfSide * lengthOfSide;
        int delta = startData - data;

        int offset = offset(data, lengthOfSide);
        return offset;
    }

    private int getLengthOfSide(int ring) {
        return ring * 2 + 1;
    }

    private int midPoint(int len) {
        return len / 2 + 1;
    }

    private int offset(int data, int lengthOfSide) {
        //  +1 adjusts the zero indexed to the logical one indexed side length
        return data % lengthOfSide + 1;
    }

}
