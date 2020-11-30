package advent2017;

import advent2017.day02.CorruptionChecksum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CorruptionChecksumTest {

    @Test
    public void testExample1() {
        String input = "5 1 9 5";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(8, checksum.checksumV1());
    }

    @Test
    public void testExample2() {
        String input = "7 5 3";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(4, checksum.checksumV1());
    }

    @Test
    public void testExample3() {
        String input = "2 4 6 8";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(6, checksum.checksumV1());
    }

    @Test
    public void testExample4() {
        String input = "5 1 9 5\n" +
                "7 5 3\n" +
                "2 4 6 8";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(18, checksum.checksumV1());
    }

    @Test
    public void testPartOne() {
        String input = "790 99 345 1080 32 143 1085 984 553 98 123 97 197 886 125 947\n" +
                "302 463 59 58 55 87 508 54 472 63 469 419 424 331 337 72\n" +
                "899 962 77 1127 62 530 78 880 129 1014 93 148 239 288 357 424\n" +
                "2417 2755 254 3886 5336 3655 5798 3273 5016 178 270 6511 223 5391 1342 2377\n" +
                "68 3002 3307 166 275 1989 1611 364 157 144 3771 1267 3188 3149 156 3454\n" +
                "1088 1261 21 1063 1173 278 1164 207 237 1230 1185 431 232 660 195 1246\n" +
                "49 1100 136 1491 647 1486 112 1278 53 1564 1147 1068 809 1638 138 117\n" +
                "158 3216 1972 2646 3181 785 2937 365 611 1977 1199 2972 201 2432 186 160\n" +
                "244 86 61 38 58 71 243 52 245 264 209 265 308 80 126 129\n" +
                "1317 792 74 111 1721 252 1082 1881 1349 94 891 1458 331 1691 89 1724\n" +
                "3798 202 3140 3468 1486 2073 3872 3190 3481 3760 2876 182 2772 226 3753 188\n" +
                "2272 6876 6759 218 272 4095 4712 6244 4889 2037 234 223 6858 3499 2358 439\n" +
                "792 230 886 824 762 895 99 799 94 110 747 635 91 406 89 157\n" +
                "2074 237 1668 1961 170 2292 2079 1371 1909 221 2039 1022 193 2195 1395 2123\n" +
                "8447 203 1806 6777 278 2850 1232 6369 398 235 212 992 7520 7304 7852 520\n" +
                "3928 107 3406 123 2111 2749 223 125 134 146 3875 1357 508 1534 4002 4417";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(46402, checksum.checksumV1());
    }

    @Test
    public void testExample5() {
        String input = "5 9 2 8";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(4, checksum.checksumV2());
    }

    @Test
    public void testExample6() {
        String input = "9 4 7 3";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(3, checksum.checksumV2());
    }

    @Test
    public void testExample7() {
        String input = "3 8 6 5";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(2, checksum.checksumV2());
    }

    @Test
    public void testExample8() {
        String input = "5 9 2 8\n" +
                "9 4 7 3\n" +
                "3 8 6 5";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(9, checksum.checksumV2());
    }

    @Test
    public void testPartTwo() {
        String input = "790 99 345 1080 32 143 1085 984 553 98 123 97 197 886 125 947\n" +
                "302 463 59 58 55 87 508 54 472 63 469 419 424 331 337 72\n" +
                "899 962 77 1127 62 530 78 880 129 1014 93 148 239 288 357 424\n" +
                "2417 2755 254 3886 5336 3655 5798 3273 5016 178 270 6511 223 5391 1342 2377\n" +
                "68 3002 3307 166 275 1989 1611 364 157 144 3771 1267 3188 3149 156 3454\n" +
                "1088 1261 21 1063 1173 278 1164 207 237 1230 1185 431 232 660 195 1246\n" +
                "49 1100 136 1491 647 1486 112 1278 53 1564 1147 1068 809 1638 138 117\n" +
                "158 3216 1972 2646 3181 785 2937 365 611 1977 1199 2972 201 2432 186 160\n" +
                "244 86 61 38 58 71 243 52 245 264 209 265 308 80 126 129\n" +
                "1317 792 74 111 1721 252 1082 1881 1349 94 891 1458 331 1691 89 1724\n" +
                "3798 202 3140 3468 1486 2073 3872 3190 3481 3760 2876 182 2772 226 3753 188\n" +
                "2272 6876 6759 218 272 4095 4712 6244 4889 2037 234 223 6858 3499 2358 439\n" +
                "792 230 886 824 762 895 99 799 94 110 747 635 91 406 89 157\n" +
                "2074 237 1668 1961 170 2292 2079 1371 1909 221 2039 1022 193 2195 1395 2123\n" +
                "8447 203 1806 6777 278 2850 1232 6369 398 235 212 992 7520 7304 7852 520\n" +
                "3928 107 3406 123 2111 2749 223 125 134 146 3875 1357 508 1534 4002 4417";
        CorruptionChecksum checksum = new CorruptionChecksum(input);
        assertEquals(265, checksum.checksumV2());
    }
}
