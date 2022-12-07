package advent2022.day07;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NoSpaceLeftOnDeviceTest {

    @Test
    public void exampleOne() {
        List<String> input = InputUtil.toList("advent2022/NoSpaceLeftOnDeviceExample.txt");
        NoSpaceLeftOnDevice noSpace = new NoSpaceLeftOnDevice();
        noSpace.withInput(input);
        assertEquals(584, noSpace.calculateDirSize("//a/e"));
        assertEquals(94853, noSpace.calculateDirSize("//a"));
        assertEquals(24933642, noSpace.calculateDirSize("//d"));
        assertEquals(48381165, noSpace.calculateDirSize("/"));
        assertEquals(95437, noSpace.sumDirsUnderMaxSize(100000));
    }

    @Test
    public void partOne() {
        List<String> input = InputUtil.toList("advent2022/NoSpaceLeftOnDevice.txt");
        NoSpaceLeftOnDevice noSpace = new NoSpaceLeftOnDevice();
        noSpace.withInput(input);
        //  1619941 - too high
        //  1306611
        assertEquals(1306611, noSpace.sumDirsUnderMaxSize(100000));
    }

    @Test
    public void exampleTwo() {
        List<String> input = InputUtil.toList("advent2022/NoSpaceLeftOnDeviceExample.txt");
        NoSpaceLeftOnDevice noSpace = new NoSpaceLeftOnDevice();
        noSpace.withInput(input);
        noSpace.setFsSize(70000000);
        noSpace.setTargetFreeSpace(30000000);
        assertEquals(24933642, noSpace.findDirectoryToDelete());
    }

    @Test
    public void partTwo() {
        List<String> input = InputUtil.toList("advent2022/NoSpaceLeftOnDevice.txt");
        NoSpaceLeftOnDevice noSpace = new NoSpaceLeftOnDevice();
        noSpace.withInput(input);
        noSpace.setFsSize(70000000);
        noSpace.setTargetFreeSpace(30000000);
        assertEquals(13210366, noSpace.findDirectoryToDelete());
    }

}