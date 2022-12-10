package advent2022.day08;

import core.InputUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TreetopTreeHouseTest {

    @Test
    public void exampleOne() {
        List<String> input = InputUtil.toList("advent2022/TreetopTreeHouseExample.txt");
        TreetopTreeHouse treeHouse = new TreetopTreeHouse();
        treeHouse.withInput(input);
        assertEquals(21, treeHouse.countVisibleTrees());
    }

    @Test
    public void partOne() {
        List<String> input = InputUtil.toList("advent2022/TreetopTreeHouse.txt");
        TreetopTreeHouse treeHouse = new TreetopTreeHouse();
        treeHouse.withInput(input);
        assertEquals(1832, treeHouse.countVisibleTrees());
    }

    @Test
    public void exampleTwo() {
        List<String> input = InputUtil.toList("advent2022/TreetopTreeHouseExample.txt");
        TreetopTreeHouse treeHouse = new TreetopTreeHouse();
        treeHouse.withInput(input);
        assertEquals(4, treeHouse.scoreTree(1, 2));
        assertEquals(8, treeHouse.scoreTree(3, 2));
        assertEquals(8, treeHouse.findHighestScenicScore());
    }

    @Test
    public void partTwo() {
        List<String> input = InputUtil.toList("advent2022/TreetopTreeHouse.txt");
        TreetopTreeHouse treeHouse = new TreetopTreeHouse();
        treeHouse.withInput(input);
        assertEquals(157320, treeHouse.findHighestScenicScore());
    }

}