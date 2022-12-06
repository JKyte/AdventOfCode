package advent2022.day06;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TuningTroubleTest {

    @Test
    public void exampleOne() {
        TuningTrouble tuning = new TuningTrouble();
        assertEquals(7, tuning.givenInput("mjqjpqmgbljsphdztnvjfqwrcgsmlb").findFirstMarker());
        assertEquals(5, tuning.givenInput("bvwbjplbgvbhsrlpgdmjqwftvncz").findFirstMarker());
        assertEquals(6, tuning.givenInput("nppdvjthqldpwncqszvftbrmjlhg").findFirstMarker());
        assertEquals(10, tuning.givenInput("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").findFirstMarker());
        assertEquals(11, tuning.givenInput("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").findFirstMarker());
    }

    @Test
    public void partOne() {
        List<String> inputs = InputUtil.toList("advent2022/TuningTrouble.txt");
        TuningTrouble tuning = new TuningTrouble();
        assertEquals(1651, tuning.givenInput(inputs.get(0)).findFirstMarker());
    }

    @Test
    public void exampleTwo() {
        TuningTrouble tuning = new TuningTrouble();
        tuning.setMarkerLength(14);
        assertEquals(19, tuning.givenInput("mjqjpqmgbljsphdztnvjfqwrcgsmlb").findFirstMarker());
        assertEquals(23, tuning.givenInput("bvwbjplbgvbhsrlpgdmjqwftvncz").findFirstMarker());
        assertEquals(23, tuning.givenInput("nppdvjthqldpwncqszvftbrmjlhg").findFirstMarker());
        assertEquals(29, tuning.givenInput("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").findFirstMarker());
        assertEquals(26, tuning.givenInput("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").findFirstMarker());
    }

    @Test
    public void partTwo() {
        List<String> inputs = InputUtil.toList("advent2022/TuningTrouble.txt");
        TuningTrouble tuning = new TuningTrouble();
        tuning.setMarkerLength(14);
        assertEquals(3837, tuning.givenInput(inputs.get(0)).findFirstMarker());
    }
}