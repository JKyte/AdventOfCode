package advent2015.day01;

public class NotQuiteLisp {

    public NotQuiteLisp() {

    }

    public int findFloor(String input) {
        return findFloor(input, false);
    }

    public int findFloor(String input, boolean findBasement) {
        int floor = 0;
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (c == '(') {
                floor++;
            } else if (c == ')') {
                floor--;
            }

            if (findBasement) {
                if (floor == -1) {
                    return i + 1;
                }
            }
        }
        return floor;
    }
}
