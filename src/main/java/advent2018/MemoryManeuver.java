package advent2018;

import com.google.common.base.Joiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Solution for https://adventofcode.com/2018/day/8
 * <p>
 * Part 1: Test -- 138
 * Part 1: Production -- 36566
 * <p>
 * Part 2: Test = 66
 * Part 2: Production = 30548
 */
public class MemoryManeuver implements Runnable {

    public static void main(String[] args) {
        MemoryManeuver mm = new MemoryManeuver();
        mm.run();
    }

    private String inputPath = "src/main/resources/advent2018/MemoryManeuver.txt";

    private int nodeId = 0;

    public MemoryManeuver() {

    }

    @Override
    public void run() {
        String line = readLine();
        LinkedList<Integer> nums = lineToList(line);
        Node root = processNumbers(nums);
        System.out.println("Metadata sum: " + sumMetadata(root));
        System.out.println("Root value: " + sumNodeValue(root));
        System.out.println("Root = " + root.toString());
    }

    private String readLine() {
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LinkedList<Integer> lineToList(String line) {
        String[] elements = line.split(" ");
        LinkedList<Integer> nums = new LinkedList<>();
        for (String element : elements) {
            nums.add(Integer.parseInt(element));
        }
        return nums;
    }

    private Node processNumbers(LinkedList<Integer> nums) {
        System.out.println("Nums: " + nums.toString());
        Node node = addNode(nums);
        return node;
    }

    private Node addNode(LinkedList<Integer> nums) {
//        System.out.println("Add Node with remaining numbers: " + nums.toString());
        //  Parse header
        int numChildren = nums.removeFirst();
        int numMetadata = nums.removeFirst();
        Node node = new Node(nodeId++, numChildren, numMetadata);

        for (int i = 0; i < numChildren; i++) {
            node.children.add(addNode(nums));
        }

        for (int i = 0; i < numMetadata; i++) {
            node.metadata[i] = nums.removeFirst();
        }
        return node;
    }


    private int sumMetadata(Node node) {
        int sum = 0;
        for (int i = 0; i < node.metadata.length; i++) {
            sum += node.metadata[i];
        }
        for (Node child : node.children) {
            sum += sumMetadata(child);
        }
        return sum;
    }

    private int sumNodeValue(Node node) {
        int value = 0;
        //  If no children, value is the sum of the metadata entries.
        if (node.children.size() == 0) {
            for (int i = 0; i < node.metadata.length; i++) {
                value += node.metadata[i];
            }
            return value;
        }

        //  If children, metadata become indices to child nodes.
        for (int i = 0; i < node.metadata.length; i++) {
            //  Index must be valid
            if (node.metadata[i] <= node.children.size()) {
                value += sumNodeValue(node.children.get(node.metadata[i]-1));
            }
        }
        return value;
    }

    public class Node {
        int id;
        int numChildren;
        int numMetadata;
        ArrayList<Node> children;
        int[] metadata;

        public Node(int id, int numChildren, int numMetadata) {
            this.id = id;
            this.numChildren = numChildren;
            this.numMetadata = numMetadata;
            this.children = new ArrayList<>();
            this.metadata = new int[numMetadata];
        }

        public String toString() {
            List<Integer> metadatas = new ArrayList<>();
            for (int ii = 0; ii < metadata.length; ii++) {
                metadatas.add(metadata[ii]);
            }
            return "Id: " + id + "\nChildren: " + numChildren + "\nMetatadata: " + numChildren
                    + "\n Metadata: " + Joiner.on(", ").join(metadatas);
        }
    }
}
