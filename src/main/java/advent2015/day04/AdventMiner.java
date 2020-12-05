package advent2015.day04;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdventMiner {

    private MessageDigest digest;

    public AdventMiner() {
        try {
            this.digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public int findLowestHash(String key, String validator) {
        int count = 0;
        String compositeKey = key + count;
        String hash = hashCompositeKey(compositeKey);

        while (!hash.startsWith(validator)) {
            count++;
            compositeKey = key + count;
            hash = hashCompositeKey(compositeKey);
        }
        return count;
    }

    private String hashCompositeKey(String compositeKey) {
        digest.update(compositeKey.getBytes());
        return DatatypeConverter.printHexBinary(digest.digest());
    }
}
