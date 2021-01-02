package core;

import java.util.Arrays;

public class MathUtil {

    public static long lcm(long... xyz) {
        long[] m = Arrays.copyOf(xyz, xyz.length);

        while (!allEquals(m)) {

            //  Iterate across each number, for each number pair...
            for (int ii = 0; ii < m.length - 1; ii++) {

                while (m[ii] != m[ii + 1]) {
                    if (m[ii] < m[ii + 1]) {
                        m[ii] += xyz[ii];
                    } else {
                        m[ii + 1] += xyz[ii + 1];
                    }
                }
            }
        }
        return m[0];
    }

    private static boolean allEquals(long... longs) {
        for (int ii = 0; ii < longs.length - 1; ii++) {
            if (longs[ii] != longs[ii + 1])
                return false;
        }
        return true;
    }
}
