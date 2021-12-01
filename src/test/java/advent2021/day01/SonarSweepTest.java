package advent2021.day01;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SonarSweepTest {

    @Test
    public void testExamplePartOne() {
        List<Integer> inputs = Lists.newArrayList(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
        SonarSweep sonarSweep = new SonarSweep();
        Assert.assertEquals(7, sonarSweep.partOne(inputs));
    }

    @Test
    public void testPartOne() throws FileNotFoundException {
        List<Integer> inputs = getProblemData();
        SonarSweep sonarSweep = new SonarSweep();
        Assert.assertEquals(1292, sonarSweep.partOne(inputs));
    }

    @Test
    public void testExamplePartTwo() {
        List<Integer> inputs = Lists.newArrayList(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
        SonarSweep sonarSweep = new SonarSweep();
        Assert.assertEquals(5, sonarSweep.partTwo(inputs));
    }

    @Test
    public void testPartTwo() throws FileNotFoundException {
        List<Integer> inputs = getProblemData();
        SonarSweep sonarSweep = new SonarSweep();
        Assert.assertEquals(1262, sonarSweep.partTwo(inputs));
    }

    private List<Integer> getProblemData() throws FileNotFoundException {
        List<Integer> depths = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/advent2021/SonarSweep.txt"));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            depths.add(Integer.parseInt(line));
        }
        return depths;
    }
}
