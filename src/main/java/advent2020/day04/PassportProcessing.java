package advent2020.day04;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PassportProcessing {

    private String[] datas;

    private Set<String> requiredFields = Sets.newHashSet("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
    private String countryId = "cid";

    public PassportProcessing(String input) {
        this.datas = input.split("\n\n");
    }

    public int countValidPassports() {
        return countValidPassports(false);
    }

    public int countValidPassports(boolean validateValues) {
        int valid = 0;
        for (String data : datas) {
            Map<String, String> passport = passportFromData(data);
            if (isPassportValid(passport, validateValues)) {
                valid++;
            }
        }
        return valid;
    }

    private Map<String, String> passportFromData(String data) {
        data = data.replace('\n', ' ');
        String[] parts = StringUtils.split(data, ' ');
        Map<String, String> passport = new HashMap<>();
        for (String part : parts) {
            String[] kv = StringUtils.split(part, ':');
            passport.put(kv[0], kv[1]);
        }
        return passport;
    }

    private boolean isPassportValid(Map<String, String> passport, boolean validateValues) {
        if (validateValues) {
            return validatePassportFields(passport) && validatePassportValues(passport);
        } else {
            return validatePassportFields(passport);
        }
    }

    public boolean validatePassportFields(Map<String, String> passport) {
        int numKeys = passport.keySet().size();
        if (numKeys == 8) {
            return passport.keySet().containsAll(requiredFields) && passport.containsKey(countryId);
        } else if (numKeys == 7) {
            return passport.keySet().containsAll(requiredFields);
        } else {
            return false;
        }
    }

    public boolean validatePassportValues(Map<String, String> passport) {
        for (Map.Entry<String, String> entry : passport.entrySet()) {
            if (!validateKeyValue(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public boolean validateKeyValue(String k, String v) {
        switch (k) {
            case "byr":
                return validateBirthYear(v);
            case "iyr":
                return validateIssueYear(v);
            case "eyr":
                return validateExpirationYear(v);
            case "hgt":
                return validateHeight(v);
            case "hcl":
                return validateHairColor(v);
            case "ecl":
                return validateEyeColor(v);
            case "pid":
                return validatePassportId(v);
            case "cid":
                return true;
            default:
                return false;
        }
    }

    private boolean validateBirthYear(String v) {
        if (v.length() == 4 && StringUtils.isNumeric(v)) {
            int year = Integer.parseInt(v);
            return year >= 1920 && year <= 2002;
        }
        return false;
    }

    private boolean validateIssueYear(String v) {
        if (v.length() == 4 && StringUtils.isNumeric(v)) {
            int year = Integer.parseInt(v);
            return year >= 2010 && year <= 2020;
        }
        return false;
    }

    private boolean validateExpirationYear(String v) {
        if (v.length() == 4 && StringUtils.isNumeric(v)) {
            int year = Integer.parseInt(v);
            return year >= 2020 && year <= 2030;
        }
        return false;
    }

    private boolean validateHeight(String v) {
        if (v.endsWith("cm")) {
            v = v.substring(0, v.length() - 2);
            if (StringUtils.isNumeric(v)) {
                int height = Integer.parseInt(v);
                return height >= 150 && height <= 193;
            }
        } else if (v.endsWith("in")) {
            v = v.substring(0, v.length() - 2);
            if (StringUtils.isNumeric(v)) {
                int height = Integer.parseInt(v);
                return height >= 59 && height <= 76;
            }
        }
        return false;
    }

    private boolean validateHairColor(String v) {
        if (v.length() == 7 && v.charAt(0) == '#') {
            v = v.substring(1);
            return StringUtils.containsOnly(v, "0123456789abcdef");
        }
        return false;
    }

    private Set<String> validEyeColors = Sets.newHashSet("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    private boolean validateEyeColor(String v) {
        return validEyeColors.contains(v);
    }

    private boolean validatePassportId(String v) {
        return v.length() == 9 && StringUtils.isNumeric(v);
    }
}
