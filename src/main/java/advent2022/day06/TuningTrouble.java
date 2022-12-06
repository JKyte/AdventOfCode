package advent2022.day06;

import java.util.HashSet;
import java.util.Set;

public class TuningTrouble {

    private int markerLength = 4;
    private String data;

    public TuningTrouble() {
        //  empty constructor
    }

    public TuningTrouble givenInput(String input) {
        this.data = input;
        return this;
    }

    public void setMarkerLength(int len) {
        this.markerLength = len;
    }

    public int findFirstMarker() {
        for (int i = 0; i < data.length() - markerLength; i++) {
            if (isMarker(data.substring(i, i + markerLength))) {
                return i + markerLength;
            }
        }
        return -1;
    }

    private boolean isMarker(String substring) {
        Set<String> set = new HashSet<>();
        for (char c : substring.toCharArray()) {
            set.add("" + c);
        }
        return set.size() == substring.length();
    }
}
