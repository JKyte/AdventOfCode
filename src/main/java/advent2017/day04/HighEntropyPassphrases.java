package advent2017.day04;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HighEntropyPassphrases {

    private String[] passes;

    public HighEntropyPassphrases(String input) {
        this.passes = inputToPasses(input);
    }

    private String[] inputToPasses(String input) {
        return StringUtils.split(input, '\n');
    }

    public int countValidPassphrases() {
        int valid = 0;
        for (String pass : passes) {
            if (isValid(pass)) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValid(String pass) {
        String[] tokens = StringUtils.split(pass, ' ');
        Set<String> seen = new HashSet<>();
        for (String token : tokens) {
            if (!seen.add(token)) {
                return false;
            }
        }
        return true;
    }

    public int countValidPassphrasesV2() {
        int valid = 0;
        for (String pass : passes) {
            if (isValidV2(pass)) {
                valid++;
            }
        }
        return valid;
    }

    private boolean isValidV2(String pass) {
        String[] tokens = StringUtils.split(pass, ' ');
        for (int i = 0; i < tokens.length - 1; i++) {
            String token = tokens[i];
            for (int j = i + 1; j < tokens.length; j++) {
                String other = tokens[j];

                if (isPalindrome(token, other)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPalindrome(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        ArrayList<String> ac = Lists.newArrayList(a.split(""));
        ArrayList<String> bc = Lists.newArrayList(b.split(""));

        Collections.sort(ac);
        Collections.sort(bc);
        return ac.equals(bc);
    }
}
