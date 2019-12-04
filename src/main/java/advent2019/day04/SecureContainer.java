package advent2019.day04;

/**
 * You arrive at the Venus fuel depot only to discover it's protected by a password. The Elves had written the password on a sticky note, but someone threw it out.
 * <p>
 * However, they do remember a few key facts about the password:
 * <p>
 * It is a six-digit number.
 * The value is within the range given in your puzzle input.
 * Two adjacent digits are the same (like 22 in 122345).
 * Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
 * Other than the range rule, the following are true:
 * <p>
 * 111111 meets these criteria (double 11, never decreases).
 * 223450 does not meet these criteria (decreasing pair of digits 50).
 * 123789 does not meet these criteria (no double).
 * How many different passwords within the range given in your puzzle input meet these criteria?
 * <p>
 * Your puzzle input is 264360-746325.
 */
public class SecureContainer {

    public static void main(String[] args) {
        //  Part 1 Examples
        System.out.println("valid: " + isPasswordValid(111111));    //  Valid. Has a duplicate value, never decreases
        System.out.println("invalid: " + isPasswordValid(223450));    //  Invalid, decreasing digit pair (50)
        System.out.println("invalid: " + isPasswordValid(123789));    //  Invalid, no double

        //  Part 2 Examples
        System.out.println("valid: " + isPasswordValid(112233));    //  valid
        System.out.println("invalid: " + isPasswordValid(123444));    //  invalid, 44 is part of 444
        System.out.println("valid: " + isPasswordValid(111122));    //  valid, 1 repeats but still contains a double 22


        System.out.println("Num valid pass codes: " + countValidPasswordsInRange(264360, 746325));
    }

    public static int countValidPasswordsInRange(int start, int end) {
        int valid = 0;
        for (int passcode = start; passcode < end; passcode++) {
            if (isPasswordValid(passcode)) {
                valid++;
            }
        }
        return valid;
    }

    public static boolean isPasswordValid(int password) {
        boolean duplicateAdjacentValues = false;
        char[] pass = Integer.toString(password).toCharArray();
        for (int ii = 0; ii < pass.length - 1; ii++) {

            if (pass[ii] > pass[ii + 1]) {
                return false;
            }

            if (pass[ii] == pass[ii + 1]) {

                //  Check for additional adjacent values to make sure it is not part of a larger group
                //  i.e., '22' is not part of '222'

                //  Check behind first
                if (ii > 0 && pass[ii - 1] == pass[ii]) {
                    continue;
                }

                //  Check ahead
                if (ii < pass.length - 2 && pass[ii + 1] == pass[ii + 2]) {
                    continue;
                }

                duplicateAdjacentValues = true;
            }
        }

        //  If there were dupes, then return true
        return duplicateAdjacentValues;
    }
}
