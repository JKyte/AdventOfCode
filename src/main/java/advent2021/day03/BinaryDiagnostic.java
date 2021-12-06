package advent2021.day03;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class BinaryDiagnostic {

    //  used for part 1
    private long gammaRate = 0;
    private long epsilonRate = 0;

    //  used for part 2
    private long o2rating;
    private long c02rating;

    public BinaryDiagnostic() {

    }

    public BinaryDiagnostic findGammaEpsilon(List<String> numbers) {
        int width = numbers.get(0).length();
        int[] ones = new int[width];
        int[] zeros = new int[width];
        int pos;
        for (String number : numbers) {
            pos = 0;
            for (char c : number.toCharArray()) {
                if (c == '0') {
                    zeros[pos]++;
                } else {
                    ones[pos]++;
                }
                pos++;
            }
        }

        int[] gamma = new int[width];
        int[] epsilon = new int[width];
        for (int i = 0; i < ones.length; i++) {
            if (ones[i] > zeros[i]) {
                gamma[i] = 1;
                epsilon[i] = 0;
            } else {
                gamma[i] = 0;
                epsilon[i] = 1;
            }
        }

        gammaRate = binaryToInt(gamma);
        epsilonRate = binaryToInt(epsilon);
        return this;
    }

    public BinaryDiagnostic findOxygenGeneratorRating(List<String> numbers) {
        int width = numbers.get(0).length();
        List<String> copy = Lists.newArrayList(numbers);
        for (int i = 0; i < width; i++) {
            int common = findMostCommonBit(copy, i);
            if (common == 0) {
                copy = filterList(copy, i, '0');
            } else if (common == 1 || common == -1) {
                copy = filterList(copy, i, '1');
            }

            if (copy.size() == 1) {
                int[] ints = stringToIntArray(copy.get(0));
                o2rating = binaryToInt(ints);
                break;
            }
        }

        if (copy.size() == 2) {
            //  keep the one with  a '1' in the final position
            String rating;
            if (copy.get(0).toCharArray()[width - 1] == '1') {
                rating = copy.get(0);
            } else {
                rating = copy.get(1);
            }
            int[] ints = stringToIntArray(rating);
            o2rating = binaryToInt(ints);
        }

        return this;
    }


    public BinaryDiagnostic findC02ScrubberRating(List<String> numbers) {
        int width = numbers.get(0).length();
        List<String> copy = Lists.newArrayList(numbers);
        for (int i = 0; i < width; i++) {
            int common = findMostCommonBit(copy, i);
            if (common == 0) {
                copy = filterList(copy, i, '1');
            } else if (common == 1 || common == -1) {
                copy = filterList(copy, i, '0');
            }

            if (copy.size() == 1) {
                int[] ints = stringToIntArray(copy.get(0));
                c02rating = binaryToInt(ints);
                break;
            }
        }

        if (copy.size() == 2) {
            //  keep the one with  a '1' in the final position
            String rating;
            if (copy.get(0).toCharArray()[width - 1] == '0') {
                rating = copy.get(0);
            } else {
                rating = copy.get(1);
            }
            int[] ints = stringToIntArray(rating);
            c02rating = binaryToInt(ints);
        }

        return this;
    }

    private int findMostCommonBit(List<String> numbers, int pos) {
        int zero = 0;
        int one = 0;

        for (String number : numbers) {
            char[] array = number.toCharArray();
            if (array[pos] == '0') {
                zero++;
            } else {
                one++;
            }
        }

        if (zero > one) {
            return 0;
        } else if (zero < one) {
            return 1;
        } else {
            return -1;  //  denotes that both were equally common
        }
    }

    private List<String> filterList(List<String> numbers, int pos, char c) {
        //  ooo fancy collect
        return numbers.stream().filter(number -> number.toCharArray()[pos] == c).collect(Collectors.toList());
    }

    private int binaryToInt(int[] num) {
        StringBuilder sb = new StringBuilder();
        for (int n : num) {
            sb.append(n);
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    private int[] stringToIntArray(String s) {
        int[] ints = new int[s.length()];
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '0') {
                ints[i] = 0;
            } else {
                ints[i] = 1;
            }
        }
        return ints;
    }


    public long getPowerConsumption() {
        return gammaRate * epsilonRate;
    }

    public long getLifeSupportRating() {
        return o2rating * c02rating;
    }

    public long getO2rating() {
        return o2rating;
    }

    public long getC02rating() {
        return c02rating;
    }
}
