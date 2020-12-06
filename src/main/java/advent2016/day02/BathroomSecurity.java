package advent2016.day02;

import org.apache.commons.lang3.StringUtils;

public class BathroomSecurity {

    private int[][] keypad;
    private char[][] keypad2;

    public BathroomSecurity() {
        buildKeypad();
        buildKeypadV2();
    }

    private void buildKeypad() {
        keypad = new int[3][3];
        keypad[0][0] = 1;
        keypad[0][1] = 2;
        keypad[0][2] = 3;
        keypad[1][0] = 4;
        keypad[1][1] = 5;
        keypad[1][2] = 6;
        keypad[2][0] = 7;
        keypad[2][1] = 8;
        keypad[2][2] = 9;
    }

    private void buildKeypadV2() {
        keypad2 = new char[5][5];
        keypad2[0][0] = '0';
        keypad2[0][1] = '0';
        keypad2[0][2] = '1';
        keypad2[0][3] = '0';
        keypad2[0][4] = '0';
        keypad2[1][0] = '0';
        keypad2[1][1] = '2';
        keypad2[1][2] = '3';
        keypad2[1][3] = '4';
        keypad2[1][4] = '0';
        keypad2[2][0] = '5';
        keypad2[2][1] = '6';
        keypad2[2][2] = '7';
        keypad2[2][3] = '8';
        keypad2[2][4] = '9';
        keypad2[3][0] = '0';
        keypad2[3][1] = 'A';
        keypad2[3][2] = 'B';
        keypad2[3][3] = 'C';
        keypad2[3][4] = '0';
        keypad2[4][0] = '0';
        keypad2[4][1] = '0';
        keypad2[4][2] = 'D';
        keypad2[4][3] = '0';
        keypad2[4][4] = '0';
    }

    public String getCode(String input) {
        return getCode(StringUtils.split(input, '\n'));
    }

    private String getCode(String[] inputs) {
        int x = 1;
        int y = 1;
        System.out.println("Start on " + keypad[y][x]);
        StringBuilder sb = new StringBuilder();
        for (String input : inputs) {
            System.out.println("Finding from " + x + ", " + y);
            for (char c : input.toCharArray()) {
                switch (c) {
                    case 'U':
                        if (y > 0) {
                            y--;
                            System.out.println("U to " + keypad[y][x]);
                        }
                        break;
                    case 'D':
                        if (y < 2) {
                            y++;
                            System.out.println("D to " + keypad[y][x]);
                        }
                        break;
                    case 'L':
                        if (x > 0) {
                            x--;
                            System.out.println("L to " + keypad[y][x]);
                        }
                        break;
                    case 'R':
                        if (x < 2) {
                            x++;
                            System.out.println("R to " + keypad[y][x]);
                        }
                        break;
                }
            }
            System.out.println("-------------");
            sb.append(keypad[y][x]);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public String getCodeV2(String input) {
        return getCodeV2(StringUtils.split(input, '\n'));
    }

    private String getCodeV2(String[] inputs) {
        int x = 0;
        int y = 2;
        System.out.println("Start on " + keypad2[y][x]);
        StringBuilder sb = new StringBuilder();
        for (String input : inputs) {
            System.out.println("Finding from " + x + ", " + y);
            for (char c : input.toCharArray()) {
                switch (c) {
                    case 'U':
                        if (y > 0 && keypad2[y - 1][x] != '0') {
                            y--;
                            System.out.println("U to " + keypad2[y][x]);
                        }
                        break;
                    case 'D':
                        if (y < 4 && keypad2[y + 1][x] != '0') {
                            y++;
                            System.out.println("D to " + keypad2[y][x]);
                        }
                        break;
                    case 'L':
                        if (x > 0 && keypad2[y][x - 1] != '0') {
                            x--;
                            System.out.println("L to " + keypad2[y][x]);
                        }
                        break;
                    case 'R':
                        if (x < 4 && keypad2[y][x + 1] != '0') {
                            x++;
                            System.out.println("R to " + keypad2[y][x]);
                        }
                        break;
                }
            }
            System.out.println("-------------");
            sb.append(keypad2[y][x]);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
