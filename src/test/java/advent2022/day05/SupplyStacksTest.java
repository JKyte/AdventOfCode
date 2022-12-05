package advent2022.day05;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SupplyStacksTest {

    @Test
    public void exampleOne() {
        List<String> inputs = InputUtil.toList("advent2022/SupplyStacksExample.txt");
        SupplyStacks stacks = new SupplyStacks();
        stacks.givenInput(inputs);
        assertEquals("CMZ", stacks.reportTopStacks());
    }

    @Test
    public void partOne() {
        List<String> inputs = InputUtil.toList("advent2022/SupplyStacks.txt");
        SupplyStacks stacks = new SupplyStacks();
        stacks.givenInput(inputs);
        assertEquals("HBTMTBSDC", stacks.reportTopStacks());
    }

    @Test
    public void exampleTwo() {
        List<String> inputs = InputUtil.toList("advent2022/SupplyStacksExample.txt");
        SupplyStacks stacks = new SupplyStacks();
        stacks.setPartTwo();
        stacks.givenInput(inputs);
        assertEquals("MCD", stacks.reportTopStacks());
    }

    @Test
    public void partTwo() {
        List<String> inputs = InputUtil.toList("advent2022/SupplyStacks.txt");
        SupplyStacks stacks = new SupplyStacks();
        stacks.setPartTwo();
        stacks.givenInput(inputs);
        assertEquals("PQTJRSHWS", stacks.reportTopStacks());
    }

}