package core;

import org.junit.Test;

import java.util.Arrays;

public class PermutationTest {

    @Test
    public void testPermutationSortedInput() {
        int[] input = {0, 1, 2};
        Permutation permutation = new Permutation(input);
        while (permutation.hasNext()) {
            System.out.println(Arrays.toString(permutation.next()));
        }
    }
}
