package core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iteratively generates permutations for the array provided at initialization time.
 * <p>
 * Solution derived from here https://stackoverflow.com/questions/2920315/permutation-of-array
 */
public class Permutation implements Iterator<long[]> {

    private long[] backing;

    private boolean hasNext = false;

    public Permutation(long[] backing) {
        //  Work with a copy of the array
        this.backing = Arrays.copyOf(backing, backing.length);
        //  Sort the array first.
        Arrays.sort(this.backing);
        //  And away we go!
        hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public long[] next() {
        if (!hasNext)
            throw new NoSuchElementException();

        long[] output = Arrays.copyOf(backing, backing.length);

        //get next permutation
        hasNext = false;
        for (int tail = backing.length - 1; tail > 0; tail--) {
            if (backing[tail - 1] < backing[tail]) {//still increasing

                //find last element which does not exceed ind[tail-1]
                int s = backing.length - 1;
                while (backing[tail - 1] >= backing[s])
                    s--;

                swap(backing, tail - 1, s);

                //reverse order of elements in the tail
                for (int i = tail, j = backing.length - 1; i < j; i++, j--) {
                    swap(backing, i, j);
                }
                hasNext = true;
                break;
            }
        }
        return output;
    }

    //  Helper method to swap two elements
    private void swap(long[] a, long i, long j) {
        long tmp = a[(int) i];
        a[(int) i] = a[(int) j];
        a[(int) j] = tmp;
    }
}
