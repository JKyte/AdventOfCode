package advent2017.day01;

public class InverseCaptcha {

    private int[] numbers;

    public InverseCaptcha(String input) {
        this.numbers = inputToNumbers(input);
    }

    private int[] inputToNumbers(String input) {
        if (input.length() % 2 != 0) {
            throw new IllegalArgumentException("Input must be a list with an EVEN number of elements.");
        }

        int[] ints = new int[input.length()];
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            ints[i] = Integer.parseInt("" + charArray[i]);
        }
        return ints;
    }

    public int solution() {
        int matches = 0;

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                matches += numbers[i];
            }
        }

        //  Do the final check for END == START
        if (numbers[numbers.length - 1] == numbers[0]) {
            matches += numbers[0];
        }

        return matches;
    }

    public int solution2() {
        int half = numbers.length / 2;
        int matches = 0;

        for (int i = 0; i < numbers.length; i++) {
            int indexPlusHalf = getHalf(i, half);
            int a = numbers[i];
            int b = numbers[indexPlusHalf];
//            System.out.println("n[" + i + "] = " + numbers[i] + " == n[" + indexPlusHalf + "] = " + numbers[indexPlusHalf]);
            if (a == b) {
                matches += numbers[i];
            }
        }

        return matches;
    }

    private int getHalf(int index, int half) {
        int next = index + half;
        if (next > numbers.length - 1) {
            return next % numbers.length;
        }
        return next;
    }


}
