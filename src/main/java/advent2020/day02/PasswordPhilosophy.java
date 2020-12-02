package advent2020.day02;

import org.apache.commons.lang3.StringUtils;

public class PasswordPhilosophy {


    private String[] passwords;

    public PasswordPhilosophy(String input) {
        this.passwords = StringUtils.split(input, '\n');
    }

    public int countValidPasswords() {
        int valid = 0;
        for (String password : passwords) {
            if (isValidPassword(password)) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValidPassword(String password) {
        String[] parts = StringUtils.split(password, ':');
        String pass = parts[1];

        String[] policyParts = StringUtils.split(parts[0], ' ');
        String letter = policyParts[1];

        String[] counts = StringUtils.split(policyParts[0], '-');
        int min = Integer.parseInt(counts[0]);
        int max = Integer.parseInt(counts[1]);

        //  Now apply the policy to the pass
        int count = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (pass.charAt(i) == letter.charAt(0)) {
                count++;
            }
        }

        return count >= min && count <= max;
    }

    public int countValidPasswordsV2() {
        int valid = 0;
        for (String password : passwords) {
            if (isValidPasswordV2(password)) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValidPasswordV2(String password) {
        String[] parts = StringUtils.split(password, ':');
        String pass = parts[1].substring(1);

        String[] policyParts = StringUtils.split(parts[0], ' ');
        String letter = policyParts[1];

        String[] counts = StringUtils.split(policyParts[0], '-');
        int min = Integer.parseInt(counts[0]) - 1;
        int max = Integer.parseInt(counts[1]) - 1;

        boolean first = pass.charAt(min) == letter.charAt(0);
        boolean second = pass.charAt(max) == letter.charAt(0);

        return (first && !second) || (!first && second);
    }
}
