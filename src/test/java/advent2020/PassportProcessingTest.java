package advent2020;

import advent2020.day04.PassportProcessing;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PassportProcessingTest {

    @Test
    public void testExample1() {
        String input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(1, processing.countValidPassports());
    }

    @Test
    public void testExample2() {
        String input = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(0, processing.countValidPassports());
    }

    @Test
    public void testExample3() {
        String input = "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(1, processing.countValidPassports());
    }

    @Test
    public void testExample4() {
        String input = "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(0, processing.countValidPassports());
    }

    @Test
    public void testExample5() {
        String input = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
                "\n" +
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
                "hcl:#cfa07d byr:1929\n" +
                "\n" +
                "hcl:#ae17e1 iyr:2013\n" +
                "eyr:2024\n" +
                "ecl:brn pid:760753108 byr:1931\n" +
                "hgt:179cm\n" +
                "\n" +
                "hcl:#cfa07d eyr:2025 pid:166559648\n" +
                "iyr:2011 ecl:brn hgt:59in";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(2, processing.countValidPassports());
    }

    @Test
    public void testPartOne() {
        PassportProcessing processing = new PassportProcessing(problemInput);
        assertEquals(239, processing.countValidPassports());
    }

    //  Test key validation
    @Test
    public void testExample6() {
        PassportProcessing processing = new PassportProcessing("");
        //  Validate birth year
        assertTrue(processing.validateKeyValue("byr", "2002"));
        assertFalse(processing.validateKeyValue("byr", "2003"));
        //  Validate height
        assertTrue(processing.validateKeyValue("hgt", "60in"));
        assertTrue(processing.validateKeyValue("hgt", "190cm"));
        assertFalse(processing.validateKeyValue("hgt", "190in"));
        assertFalse(processing.validateKeyValue("hgt", "190"));
        //  Validate hair color
        assertTrue(processing.validateKeyValue("hcl", "#123abc"));
        assertFalse(processing.validateKeyValue("hcl", "#123abz"));
        assertFalse(processing.validateKeyValue("hcl", "123abc"));
        //  Validate eye color
        assertTrue(processing.validateKeyValue("ecl", "brn"));
        assertFalse(processing.validateKeyValue("ecl", "wat"));
        //  Validate passport id
        assertTrue(processing.validateKeyValue("pid", "000000001"));
        assertFalse(processing.validateKeyValue("pid", "0123456789"));
    }

    //  Test invalid passports
    @Test
    public void testExample7() {
        String input = "eyr:1972 cid:100\n" +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926\n" +
                "\n" +
                "iyr:2019\n" +
                "hcl:#602927 eyr:1967 hgt:170cm\n" +
                "ecl:grn pid:012533040 byr:1946\n" +
                "\n" +
                "hcl:dab227 iyr:2012\n" +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277\n" +
                "\n" +
                "hgt:59cm ecl:zzz\n" +
                "eyr:2038 hcl:74454a iyr:2023\n" +
                "pid:3556412378 byr:2007";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(0, processing.countValidPassports(true));
    }

    //  All valid passports
    @Test
    public void testExample8() {
        String input = "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\n" +
                "hcl:#623a2f\n" +
                "\n" +
                "eyr:2029 ecl:blu cid:129 byr:1989\n" +
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm\n" +
                "\n" +
                "hcl:#888785\n" +
                "hgt:164cm byr:2001 iyr:2015 cid:88\n" +
                "pid:545766238 ecl:hzl\n" +
                "eyr:2022\n" +
                "\n" +
                "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719";
        PassportProcessing processing = new PassportProcessing(input);
        assertEquals(4, processing.countValidPassports(true));
    }

    @Test
    public void testPartTwo() {
        PassportProcessing processing = new PassportProcessing(problemInput);
        assertEquals(188, processing.countValidPassports(true));
    }

    private String problemInput = "byr:1983 iyr:2017\n" +
            "pid:796082981 cid:129 eyr:2030\n" +
            "ecl:oth hgt:182cm\n" +
            "\n" +
            "iyr:2019\n" +
            "cid:314\n" +
            "eyr:2039 hcl:#cfa07d hgt:171cm ecl:#0180ce byr:2006 pid:8204115568\n" +
            "\n" +
            "byr:1991 eyr:2022 hcl:#341e13 iyr:2016 pid:729933757 hgt:167cm ecl:gry\n" +
            "\n" +
            "hcl:231d64 cid:124 ecl:gmt eyr:2039\n" +
            "hgt:189in\n" +
            "pid:#9c3ea1\n" +
            "\n" +
            "ecl:#1f58f9\n" +
            "pid:#758e59\n" +
            "iyr:2022\n" +
            "hcl:z\n" +
            "byr:2016 hgt:68 eyr:1933\n" +
            "\n" +
            "hcl:#fffffd ecl:gry eyr:2022\n" +
            "hgt:172cm pid:781914826 byr:1930 iyr:2018\n" +
            "\n" +
            "hcl:#08df7e ecl:grn byr:1942\n" +
            "eyr:2028 iyr:2011 cid:141 pid:319110455\n" +
            "hgt:186cm\n" +
            "\n" +
            "pid:991343040 hgt:179cm\n" +
            "hcl:#a97842 iyr:2020\n" +
            "eyr:2024\n" +
            "byr:1984 cid:181\n" +
            "\n" +
            "pid:188cm byr:2005\n" +
            "hgt:170cm cid:163 ecl:#a08502 hcl:2964fb eyr:1994\n" +
            "iyr:2005\n" +
            "\n" +
            "ecl:grn hcl:#fffffd iyr:2013\n" +
            "pid:705547886\n" +
            "byr:1928 hgt:168cm eyr:2030\n" +
            "\n" +
            "cid:219\n" +
            "pid:016251942 hcl:#602927 hgt:163cm\n" +
            "byr:1943 eyr:2029 ecl:oth iyr:2019\n" +
            "\n" +
            "ecl:gry hgt:184cm eyr:2026\n" +
            "iyr:2010\n" +
            "pid:117647952 hcl:#efcc98\n" +
            "byr:1942\n" +
            "\n" +
            "cid:243 hcl:#888785 ecl:blu eyr:2027 pid:362697676\n" +
            "iyr:2011 byr:1962 hgt:154cm\n" +
            "\n" +
            "hgt:154cm byr:1965 ecl:blu eyr:2030\n" +
            "pid:779104554 iyr:2016 hcl:#435634\n" +
            "\n" +
            "hcl:z eyr:1996 iyr:1993\n" +
            "pid:#50f768\n" +
            "ecl:zzz hgt:62cm byr:2017\n" +
            "\n" +
            "ecl:grn byr:1988 iyr:2016\n" +
            "hgt:167cm\n" +
            "hcl:#cfa07d\n" +
            "eyr:2030 pid:951967790\n" +
            "\n" +
            "pid:320348494 iyr:2018 cid:281\n" +
            "byr:2004\n" +
            "hcl:#06a58b\n" +
            "eyr:2033\n" +
            "ecl:zzz\n" +
            "hgt:76cm\n" +
            "\n" +
            "cid:83 ecl:brn eyr:2028\n" +
            "byr:1941 iyr:2016\n" +
            "hcl:#341e13 pid:806979833\n" +
            "hgt:179cm\n" +
            "\n" +
            "ecl:brn\n" +
            "byr:1982 iyr:2010 eyr:2029 pid:535752324 hcl:#efcc98\n" +
            "\n" +
            "ecl:oth\n" +
            "hgt:70in hcl:#866857 eyr:2025 pid:203320330 iyr:2018 byr:2000\n" +
            "\n" +
            "hgt:70cm byr:2015 pid:#218eb5 hcl:#0ec4fe iyr:2014 cid:228 ecl:#c8533a\n" +
            "eyr:2035\n" +
            "\n" +
            "hcl:#6b5442\n" +
            "eyr:2020 ecl:hzl iyr:2017 hgt:173cm\n" +
            "cid:330 byr:1988 pid:173148327\n" +
            "\n" +
            "iyr:2011 byr:1964 hgt:83 ecl:grn hcl:#c0946f pid:931162400 eyr:2028\n" +
            "\n" +
            "cid:239\n" +
            "byr:1960 ecl:hzl\n" +
            "hgt:164cm\n" +
            "hcl:#51040b iyr:2018 eyr:2025\n" +
            "\n" +
            "cid:163 hgt:154cm\n" +
            "iyr:2015 eyr:2027 pid:838964596\n" +
            "byr:1972 ecl:oth hcl:#efcc98\n" +
            "\n" +
            "hgt:181cm\n" +
            "eyr:2028 ecl:blu\n" +
            "pid:853714682 hcl:#623a2f byr:1976 iyr:2020\n" +
            "\n" +
            "cid:225 byr:1957\n" +
            "hcl:#a97842 iyr:2013 eyr:2025\n" +
            "pid:511588647 hgt:173cm ecl:blu\n" +
            "\n" +
            "hcl:#efcc98\n" +
            "byr:1993\n" +
            "ecl:oth\n" +
            "pid:871652492 eyr:2028 hgt:177cm iyr:2016\n" +
            "cid:220\n" +
            "\n" +
            "ecl:hzl\n" +
            "hgt:165cm\n" +
            "hcl:#733820 eyr:2028 cid:57 byr:1973 iyr:2018 pid:018982018\n" +
            "\n" +
            "pid:491710153 iyr:2012 ecl:#c85046 hcl:#b6652a\n" +
            "eyr:2040 hgt:175cm byr:1981\n" +
            "\n" +
            "pid:917105765 eyr:2021 hgt:181cm iyr:2019 cid:159 byr:1995\n" +
            "ecl:gry\n" +
            "\n" +
            "hcl:#9d2ec4 iyr:2011\n" +
            "eyr:2028 pid:149288934 hgt:63in ecl:blu byr:1960\n" +
            "\n" +
            "byr:1923 pid:705818464 eyr:2024 cid:221 ecl:oth hcl:#7d3b0c hgt:193cm iyr:2014\n" +
            "\n" +
            "pid:117111015 eyr:2030\n" +
            "byr:1967 hcl:#ceb3a1 ecl:blu\n" +
            "hgt:157cm\n" +
            "iyr:2011\n" +
            "\n" +
            "iyr:2019 ecl:oth\n" +
            "hcl:#fffffd hgt:172cm pid:215010680\n" +
            "eyr:2025\n" +
            "\n" +
            "pid:157cm cid:277\n" +
            "iyr:1976 hgt:159in hcl:#341e13 ecl:#6c7644 eyr:2029 byr:1965\n" +
            "\n" +
            "pid:787186482 ecl:brn\n" +
            "byr:1980 hcl:#f5dfb9 eyr:2020\n" +
            "iyr:2018 hgt:188cm\n" +
            "\n" +
            "cid:168\n" +
            "eyr:2023 hcl:#07c809\n" +
            "iyr:2013\n" +
            "hgt:169cm pid:250679100 byr:1945 ecl:gry\n" +
            "\n" +
            "hcl:#6b5442 pid:683134187 iyr:2013 eyr:2023 byr:1965 hgt:171cm ecl:hzl\n" +
            "\n" +
            "eyr:2028 hgt:180cm ecl:blu byr:1952 cid:314 iyr:2016\n" +
            "pid:720794393 hcl:#602927\n" +
            "\n" +
            "byr:1982 iyr:2016\n" +
            "ecl:brn eyr:2027\n" +
            "hgt:156cm pid:185583837 hcl:#ddbf30\n" +
            "\n" +
            "hcl:#ceb3a1 pid:987624973\n" +
            "eyr:2026\n" +
            "iyr:2013 byr:1988 hgt:175cm ecl:grn\n" +
            "\n" +
            "eyr:2028 byr:1974 pid:350988773 hcl:#a97842 iyr:2015\n" +
            "ecl:oth\n" +
            "hgt:160cm\n" +
            "\n" +
            "hcl:#b6652a\n" +
            "eyr:2028\n" +
            "pid:717504683 byr:1970\n" +
            "iyr:2013\n" +
            "ecl:gry\n" +
            "hgt:156cm\n" +
            "\n" +
            "pid:453874703 iyr:2015 hcl:#a97842 ecl:hzl byr:1986 hgt:175cm cid:132 eyr:2025\n" +
            "\n" +
            "hcl:#7d3b0c\n" +
            "eyr:2026\n" +
            "ecl:brn hgt:154cm\n" +
            "byr:1959 pid:580659686 iyr:2015\n" +
            "\n" +
            "ecl:amb hgt:191cm iyr:2018\n" +
            "pid:681417707 byr:1994 eyr:2023 hcl:#c0946f\n" +
            "\n" +
            "eyr:2022 pid:302326561 iyr:2018 ecl:blu byr:1987 cid:89 hcl:#a97842 hgt:184cm\n" +
            "\n" +
            "eyr:2020 pid:457081226\n" +
            "ecl:blu hcl:#866857 iyr:2011\n" +
            "hgt:159cm byr:1959\n" +
            "\n" +
            "eyr:2024 cid:322 byr:1991 pid:210415503\n" +
            "hgt:69in ecl:grn\n" +
            "hcl:#623a2f\n" +
            "\n" +
            "ecl:blu iyr:2012 pid:524745721 hcl:#c0946f eyr:2026 hgt:176cm byr:1964\n" +
            "\n" +
            "hgt:189cm iyr:2014 pid:679155617 hcl:#efcc98 eyr:2027\n" +
            "cid:88 byr:1974\n" +
            "ecl:blu\n" +
            "\n" +
            "byr:1935 eyr:2029\n" +
            "iyr:2020\n" +
            "hcl:#733820 ecl:blu hgt:190cm pid:509009432\n" +
            "\n" +
            "hcl:#fffffd pid:446558583 byr:1931 ecl:brn iyr:2019\n" +
            "hgt:159cm cid:146\n" +
            "eyr:2024\n" +
            "\n" +
            "eyr:2028 hcl:#efcc98 pid:330588516 hgt:65cm\n" +
            "byr:1972 iyr:2014 ecl:oth\n" +
            "\n" +
            "ecl:blu hgt:175cm cid:197 pid:068138358 eyr:2023 iyr:2017 hcl:#0441c6 byr:1999\n" +
            "\n" +
            "byr:1992 hgt:193cm\n" +
            "ecl:brn iyr:2018 hcl:#866857 pid:905992465\n" +
            "eyr:2022\n" +
            "\n" +
            "hgt:95 byr:1965\n" +
            "pid:810311252 eyr:2034 hcl:z iyr:1985 cid:254\n" +
            "\n" +
            "hcl:#c0946f byr:1985 eyr:2030 hgt:161cm iyr:2010 ecl:gry pid:616639221\n" +
            "\n" +
            "iyr:2011 eyr:2023 hgt:172cm cid:260 ecl:hzl\n" +
            "pid:594747312\n" +
            "hcl:#a97842 byr:1937\n" +
            "\n" +
            "eyr:2028 pid:134536806 cid:83\n" +
            "ecl:brn byr:1928\n" +
            "iyr:2015\n" +
            "hcl:#fffffd hgt:157cm\n" +
            "\n" +
            "iyr:2016\n" +
            "pid:035433923 hgt:180cm ecl:amb eyr:2027 byr:1934\n" +
            "cid:195 hcl:#583d02\n" +
            "\n" +
            "eyr:1936 cid:130 byr:1969 hgt:74cm hcl:50e1a7 ecl:gmt iyr:2010 pid:82008848\n" +
            "\n" +
            "hcl:#733820\n" +
            "eyr:2020\n" +
            "hgt:174cm iyr:2018 ecl:hzl byr:1983 pid:087167304\n" +
            "\n" +
            "byr:1972 hcl:#888785 eyr:2020 pid:593301831 iyr:2013 ecl:hzl hgt:188cm\n" +
            "\n" +
            "cid:282 hcl:#888785 hgt:170cm ecl:oth eyr:2029\n" +
            "byr:1942 pid:014356555 iyr:2020\n" +
            "\n" +
            "byr:1966 hcl:#623a2f ecl:oth hgt:165cm\n" +
            "eyr:2028 iyr:2012 pid:558908746\n" +
            "\n" +
            "pid:#4f5b92\n" +
            "hcl:#6b5442 hgt:188cm\n" +
            "byr:1994 iyr:2014 cid:127 eyr:2020\n" +
            "ecl:oth\n" +
            "\n" +
            "hgt:153cm\n" +
            "ecl:brn iyr:2020\n" +
            "eyr:2026 hcl:#18171d\n" +
            "pid:101990935\n" +
            "byr:1932\n" +
            "\n" +
            "iyr:2011 byr:1981 hgt:157cm hcl:#c0946f\n" +
            "eyr:2029 pid:545992967\n" +
            "ecl:utc\n" +
            "\n" +
            "byr:1929 hcl:#602927 iyr:2018 ecl:grn\n" +
            "eyr:2027\n" +
            "pid:256056759\n" +
            "hgt:178cm\n" +
            "\n" +
            "iyr:2016 pid:813526512 eyr:2027 hcl:#20799c\n" +
            "ecl:blu\n" +
            "byr:1971 hgt:185cm\n" +
            "\n" +
            "iyr:2021 eyr:2040\n" +
            "pid:5135078781 byr:2013 hcl:#7d3b0c hgt:62cm ecl:#dedf53\n" +
            "\n" +
            "iyr:2013\n" +
            "byr:1979 cid:269 hgt:179cm pid:871628606 eyr:2026 hcl:#53b66c\n" +
            "ecl:grn\n" +
            "\n" +
            "eyr:2020\n" +
            "hcl:#ceb3a1 byr:1988 ecl:oth iyr:2012\n" +
            "pid:558371571\n" +
            "\n" +
            "pid:908462345 byr:1972 hgt:183cm ecl:gry cid:51 hcl:#af82df eyr:2023 iyr:2019\n" +
            "\n" +
            "pid:106942710 ecl:hzl\n" +
            "hgt:157cm hcl:z eyr:2025 iyr:2016\n" +
            "byr:1998 cid:271\n" +
            "\n" +
            "iyr:2011 ecl:oth pid:191542220\n" +
            "byr:1951 eyr:2027 hcl:#c0946f hgt:190cm\n" +
            "\n" +
            "eyr:2028 hgt:193cm pid:235913726 iyr:2012 hcl:#325350\n" +
            "ecl:amb\n" +
            "\n" +
            "iyr:2018 hcl:#a97842 ecl:hzl byr:1988 pid:481250123\n" +
            "cid:328 hgt:165cm eyr:2028\n" +
            "\n" +
            "ecl:#a51d9c hcl:91236c pid:2538922220 byr:2017 eyr:2004\n" +
            "iyr:2026 hgt:174\n" +
            "\n" +
            "pid:959660262 eyr:2022 cid:339 hgt:170cm iyr:2012\n" +
            "hcl:#cfa07d ecl:brn\n" +
            "\n" +
            "hcl:#866857\n" +
            "ecl:dne hgt:70cm eyr:2013 iyr:1980 pid:780067045\n" +
            "byr:1950\n" +
            "\n" +
            "iyr:2011\n" +
            "byr:1981\n" +
            "pid:902964474 ecl:gry eyr:2021\n" +
            "hgt:154cm\n" +
            "hcl:#602927 cid:156\n" +
            "\n" +
            "iyr:2016\n" +
            "hgt:156cm ecl:brn cid:315 eyr:2023 byr:1997\n" +
            "hcl:#623a2f pid:339892714\n" +
            "\n" +
            "ecl:brn hgt:73in cid:184 byr:1960 eyr:2024 iyr:2014 hcl:#888785\n" +
            "pid:561655785\n" +
            "\n" +
            "pid:579663338\n" +
            "eyr:1977\n" +
            "hcl:#ceb3a1 ecl:grt hgt:188cm\n" +
            "byr:2017\n" +
            "iyr:2018\n" +
            "\n" +
            "byr:1941 eyr:2029 pid:252436521\n" +
            "hgt:170cm ecl:hzl\n" +
            "\n" +
            "hcl:#888785\n" +
            "pid:378073052\n" +
            "hgt:185cm\n" +
            "cid:343 byr:2001\n" +
            "ecl:oth iyr:1988 eyr:2029\n" +
            "\n" +
            "pid:286459107 byr:1973 eyr:2023 ecl:oth cid:273\n" +
            "hgt:184cm\n" +
            "\n" +
            "pid:406853460 eyr:2028 hcl:#b6652a\n" +
            "hgt:179cm\n" +
            "iyr:2020 cid:346\n" +
            "ecl:brn byr:1973\n" +
            "\n" +
            "hcl:#ceb3a1 eyr:2026 pid:001798001 ecl:gry hgt:157cm\n" +
            "cid:235\n" +
            "byr:1968\n" +
            "iyr:2013\n" +
            "\n" +
            "hcl:#b6652a hgt:151cm pid:504383643 iyr:2018\n" +
            "ecl:brn byr:1990\n" +
            "eyr:2021\n" +
            "\n" +
            "hgt:164cm iyr:2015 hcl:#888785 byr:1998 pid:045813631 cid:237\n" +
            "ecl:grn\n" +
            "eyr:2026\n" +
            "\n" +
            "hcl:#733820 hgt:172cm\n" +
            "eyr:2030 iyr:2015 ecl:gry pid:507769390 byr:1979 cid:212\n" +
            "\n" +
            "cid:138 hgt:176cm hcl:#efcc98\n" +
            "eyr:2024 byr:1984\n" +
            "ecl:brn iyr:2015 pid:968062470\n" +
            "\n" +
            "hcl:#733820 iyr:2015 ecl:oth\n" +
            "eyr:2028 pid:431922630 hgt:160cm byr:1941\n" +
            "\n" +
            "iyr:2017\n" +
            "eyr:2023 ecl:grn cid:294 byr:1969\n" +
            "hcl:#602927 pid:720065302\n" +
            "hgt:67in\n" +
            "\n" +
            "ecl:gry byr:2002 eyr:2024 hcl:#7d3b0c\n" +
            "hgt:174cm\n" +
            "iyr:2012 pid:296913847\n" +
            "\n" +
            "pid:42057861 hcl:bb7ace eyr:2023 byr:2013 hgt:188 ecl:#312118 iyr:2024\n" +
            "\n" +
            "eyr:2021\n" +
            "byr:1958\n" +
            "hgt:170cm ecl:brn iyr:2012\n" +
            "pid:064919306 cid:349 hcl:#602927\n" +
            "\n" +
            "eyr:2022\n" +
            "pid:248168906\n" +
            "iyr:2013 byr:1996 hcl:#cfa07d\n" +
            "hgt:151cm ecl:hzl\n" +
            "\n" +
            "cid:301 ecl:brn hcl:#fffffd\n" +
            "hgt:183cm\n" +
            "pid:806659387\n" +
            "eyr:2021\n" +
            "byr:1970 iyr:2013\n" +
            "\n" +
            "cid:192\n" +
            "iyr:2013\n" +
            "ecl:#5ad460 hgt:64cm\n" +
            "pid:001255475 byr:1984 eyr:2027 hcl:#cfa07d\n" +
            "\n" +
            "iyr:2012 pid:194936105 eyr:2028 byr:2000 ecl:oth hcl:#733820 hgt:158cm\n" +
            "\n" +
            "cid:323\n" +
            "hcl:#a97842 eyr:2027 pid:625573908\n" +
            "iyr:2019 byr:1987 ecl:grn\n" +
            "hgt:191cm\n" +
            "\n" +
            "pid:997956152 hgt:193cm ecl:hzl eyr:2024 byr:1983\n" +
            "cid:113 hcl:#888785\n" +
            "iyr:2013\n" +
            "\n" +
            "iyr:2017 hgt:153cm hcl:#733820 byr:1984 eyr:2025 pid:138608494 ecl:blu\n" +
            "\n" +
            "ecl:brn\n" +
            "byr:1987 hgt:174cm\n" +
            "iyr:2013 pid:459148475 eyr:2023 hcl:#623a2f cid:266\n" +
            "\n" +
            "byr:2000 iyr:2017 ecl:brn pid:469155516 hcl:#b6652a\n" +
            "eyr:2027 hgt:193cm\n" +
            "\n" +
            "byr:1967 eyr:2028 pid:064940030\n" +
            "iyr:2016\n" +
            "ecl:gry hcl:#18171d hgt:74in\n" +
            "\n" +
            "iyr:2020 hcl:#efcc98\n" +
            "byr:1968 hgt:164cm ecl:hzl pid:834180009 eyr:2022\n" +
            "\n" +
            "pid:021397352\n" +
            "iyr:2018 hcl:#341e13 byr:1978 eyr:2022 ecl:oth hgt:67in\n" +
            "\n" +
            "hgt:160cm cid:213 ecl:#a46ef7 pid:157cm eyr:2020 iyr:2020 byr:1923\n" +
            "\n" +
            "iyr:2016\n" +
            "cid:235 pid:454188395 eyr:2022 hgt:73in ecl:hzl\n" +
            "hcl:#7d3b0c byr:1964\n" +
            "\n" +
            "iyr:1930 eyr:2033 hgt:76cm pid:41117341 byr:2028 ecl:utc\n" +
            "hcl:#6b5442\n" +
            "\n" +
            "pid:41316572\n" +
            "hcl:#cfa07d byr:1965 eyr:2027 hgt:179cm iyr:2010\n" +
            "ecl:grn\n" +
            "\n" +
            "hgt:152cm pid:886168412 iyr:2027\n" +
            "eyr:1989 hcl:9993d6 byr:2005 ecl:zzz\n" +
            "\n" +
            "pid:661569613 hgt:166cm\n" +
            "hcl:#18171d iyr:2010 byr:1922 eyr:2030 ecl:brn\n" +
            "\n" +
            "byr:1958\n" +
            "ecl:blu pid:978855125\n" +
            "eyr:2020 iyr:2019\n" +
            "hgt:190cm hcl:#18171d\n" +
            "\n" +
            "hgt:68in iyr:2012 hcl:#ceb3a1 eyr:2028 ecl:oth pid:067088299\n" +
            "byr:1975\n" +
            "\n" +
            "eyr:2020\n" +
            "pid:507464869 hcl:#fffffd hgt:156cm iyr:2016\n" +
            "byr:1957 ecl:blu\n" +
            "\n" +
            "cid:259 eyr:2025 byr:1954\n" +
            "ecl:gry hgt:167cm pid:832017347 iyr:2020 hcl:#623a2f\n" +
            "\n" +
            "hgt:69in hcl:#a97842\n" +
            "pid:426496916 byr:1947 eyr:2021 iyr:2015 ecl:oth\n" +
            "\n" +
            "eyr:2025 ecl:blu pid:543125976 cid:192 iyr:2017\n" +
            "byr:1920 hgt:154cm hcl:#a7ecdc\n" +
            "\n" +
            "hgt:69in iyr:2017\n" +
            "byr:1932 hcl:#6b5442\n" +
            "ecl:hzl cid:349 pid:494399909 eyr:2029\n" +
            "\n" +
            "eyr:2030 ecl:gry hcl:#6b5442\n" +
            "iyr:2010 byr:1938 cid:100 pid:477259022 hgt:67in\n" +
            "\n" +
            "hgt:145 byr:2009 hcl:#b6652a iyr:2015\n" +
            "pid:180cm ecl:dne cid:315 eyr:1920\n" +
            "\n" +
            "byr:1930 hgt:65in\n" +
            "eyr:2022 ecl:blu\n" +
            "pid:671271699\n" +
            "iyr:2010\n" +
            "hcl:#b6652a\n" +
            "\n" +
            "byr:1989 eyr:2020\n" +
            "ecl:hzl\n" +
            "hcl:#341e13\n" +
            "pid:625435489\n" +
            "hgt:189cm cid:72\n" +
            "iyr:2013\n" +
            "\n" +
            "hgt:184\n" +
            "byr:2025 ecl:#a1368a eyr:2038 cid:111\n" +
            "iyr:2025 hcl:z pid:7952164402\n" +
            "\n" +
            "pid:165478949\n" +
            "hcl:453b30 ecl:amb hgt:75cm eyr:1987 iyr:2015\n" +
            "byr:1960\n" +
            "\n" +
            "eyr:2022 ecl:blu\n" +
            "cid:100\n" +
            "hcl:ead803 iyr:2025 byr:2018\n" +
            "\n" +
            "eyr:2024\n" +
            "ecl:gry hgt:167cm\n" +
            "hcl:#623a2f cid:259\n" +
            "byr:1932 iyr:2014 pid:360279704\n" +
            "\n" +
            "hgt:191cm\n" +
            "ecl:oth pid:070592110 cid:275 eyr:2027\n" +
            "iyr:2011 hcl:#4a4252 byr:1937\n" +
            "\n" +
            "ecl:blu cid:256 iyr:2017 eyr:2027 hcl:#341e13 hgt:150cm\n" +
            "pid:152140902 byr:1923\n" +
            "\n" +
            "eyr:1972 iyr:2020 ecl:gry hcl:#888098 byr:1974 hgt:188cm\n" +
            "pid:586853292\n" +
            "\n" +
            "iyr:2014\n" +
            "ecl:brn hcl:#866857 eyr:2020\n" +
            "hgt:184cm pid:422142863 byr:1999\n" +
            "\n" +
            "iyr:2025 ecl:amb eyr:1928 hcl:#18171d byr:2008 hgt:62cm pid:42120034\n" +
            "\n" +
            "byr:1923 cid:85 iyr:2017\n" +
            "hcl:#602927 eyr:2026 pid:922322363\n" +
            "hgt:68in ecl:amb\n" +
            "\n" +
            "cid:97 hcl:#602927\n" +
            "pid:436567964\n" +
            "eyr:2028 iyr:2016\n" +
            "byr:1994\n" +
            "\n" +
            "hcl:#9c166d\n" +
            "eyr:2025 pid:834335216 iyr:2011 ecl:blu byr:1946 hgt:174cm\n" +
            "\n" +
            "byr:2018 iyr:2027 hgt:187in\n" +
            "cid:118 eyr:2038\n" +
            "ecl:lzr hcl:z\n" +
            "\n" +
            "ecl:blu\n" +
            "byr:1998 pid:186cm eyr:2026 hcl:z iyr:2027 hgt:70in\n" +
            "\n" +
            "hcl:#623a2f eyr:2020 ecl:amb iyr:2010 pid:743059641 cid:240 hgt:169cm byr:1957\n" +
            "\n" +
            "ecl:oth pid:089778639 cid:305 eyr:2027 iyr:2012 byr:1935\n" +
            "hcl:#efcc98\n" +
            "\n" +
            "hgt:151cm hcl:#602927 cid:97 byr:1968 iyr:2014 pid:447599233\n" +
            "ecl:oth\n" +
            "eyr:2030\n" +
            "\n" +
            "pid:621084188 byr:1941 ecl:gry cid:188 iyr:2012 hgt:75in eyr:2028 hcl:#6b5442\n" +
            "\n" +
            "hcl:#c0946f\n" +
            "ecl:amb\n" +
            "hgt:66cm\n" +
            "pid:185cm byr:2022 eyr:2039 iyr:2024\n" +
            "cid:321\n" +
            "\n" +
            "hgt:177cm byr:1954 ecl:amb pid:445374119 cid:137 hcl:#341e13 iyr:2010\n" +
            "eyr:2020\n" +
            "\n" +
            "hgt:160cm\n" +
            "byr:1923\n" +
            "ecl:grn\n" +
            "eyr:2021 iyr:2012\n" +
            "pid:286304911\n" +
            "hcl:#18171d\n" +
            "\n" +
            "hgt:153cm byr:1933\n" +
            "iyr:2015\n" +
            "ecl:gry\n" +
            "pid:365430749 eyr:2029\n" +
            "\n" +
            "cid:294 pid:817081355 byr:1969\n" +
            "eyr:2030 ecl:oth iyr:2014 hgt:181cm hcl:#623a2f\n" +
            "\n" +
            "iyr:2011\n" +
            "ecl:gry hgt:177cm eyr:2025 pid:446342686 hcl:#b6652a byr:1991\n" +
            "cid:241\n" +
            "\n" +
            "byr:1999\n" +
            "iyr:2018\n" +
            "cid:306 hcl:#18171d eyr:2021\n" +
            "hgt:188cm ecl:gry pid:473752814\n" +
            "\n" +
            "byr:2002 hcl:#733820\n" +
            "pid:867697169\n" +
            "ecl:gry hgt:165cm eyr:2020\n" +
            "cid:316\n" +
            "\n" +
            "eyr:2026 cid:59 hgt:175cm byr:1993 pid:531385722\n" +
            "ecl:hzl hcl:#733820\n" +
            "\n" +
            "eyr:2027\n" +
            "cid:50 pid:433963708\n" +
            "byr:1969\n" +
            "iyr:2011 ecl:hzl hgt:164cm\n" +
            "hcl:#b6652a\n" +
            "\n" +
            "eyr:2020 ecl:gry hgt:186cm pid:917147781 hcl:#341e13\n" +
            "iyr:2016 cid:68\n" +
            "\n" +
            "pid:857547233 hgt:64in\n" +
            "cid:274\n" +
            "eyr:2020 ecl:hzl iyr:2019 hcl:#866857 byr:1948\n" +
            "\n" +
            "eyr:2022 hgt:183cm pid:557280094\n" +
            "byr:1936 hcl:#602927 iyr:2019 ecl:oth\n" +
            "\n" +
            "byr:1933 eyr:2023\n" +
            "iyr:2020\n" +
            "ecl:blu hgt:72in\n" +
            "\n" +
            "pid:682285472\n" +
            "ecl:blu hgt:166cm eyr:2021\n" +
            "byr:1993\n" +
            "hcl:#ceb3a1 iyr:2011 cid:266\n" +
            "\n" +
            "iyr:2012 cid:172 ecl:#04ce29 eyr:2021 hgt:160cm byr:1926 pid:2235389773\n" +
            "\n" +
            "eyr:2029 hcl:#cfa07d pid:387564370 cid:276 hgt:74in\n" +
            "ecl:amb\n" +
            "byr:1926 iyr:2019\n" +
            "\n" +
            "eyr:2026\n" +
            "hcl:#733820\n" +
            "pid:230583200 byr:1997\n" +
            "ecl:brn\n" +
            "iyr:2010\n" +
            "hgt:179cm\n" +
            "\n" +
            "byr:1946 hcl:#866857 ecl:#87b6f4 hgt:150cm pid:298537901\n" +
            "eyr:2024 iyr:2011\n" +
            "\n" +
            "hcl:#cfa07d\n" +
            "byr:1961\n" +
            "eyr:2022\n" +
            "hgt:167cm\n" +
            "pid:230816154 ecl:oth iyr:2018\n" +
            "cid:164\n" +
            "\n" +
            "pid:167899852 hcl:#18171d eyr:2023 hgt:173cm ecl:amb byr:1960 iyr:2010\n" +
            "\n" +
            "hcl:#866857\n" +
            "hgt:165cm\n" +
            "ecl:hzl pid:325078465 byr:2002\n" +
            "cid:61 eyr:2025 iyr:2020\n" +
            "\n" +
            "cid:268\n" +
            "hcl:#a97842 iyr:2011 byr:1966 pid:450468785\n" +
            "eyr:2030 hgt:173cm\n" +
            "ecl:gry\n" +
            "\n" +
            "hgt:181cm\n" +
            "eyr:2026 cid:77 pid:229016136 ecl:grn byr:1929\n" +
            "\n" +
            "ecl:#ad9ae9 hcl:z iyr:2012\n" +
            "byr:2029\n" +
            "cid:77 pid:#b1f685 eyr:2015\n" +
            "\n" +
            "ecl:amb byr:1920\n" +
            "eyr:2026 hcl:#92e796 iyr:2011 pid:503853254 hgt:186cm\n" +
            "cid:101\n" +
            "\n" +
            "hcl:#7d3b0c eyr:2022 ecl:amb pid:536474715 hgt:64in\n" +
            "iyr:2026 byr:1924\n" +
            "\n" +
            "hgt:72in ecl:hzl hcl:#888785 eyr:2030 pid:048654766 byr:1977 iyr:2016\n" +
            "\n" +
            "hgt:171cm ecl:brn byr:1976 pid:844553043\n" +
            "eyr:2024\n" +
            "cid:241\n" +
            "\n" +
            "cid:243 eyr:2023 pid:998276626 iyr:2011 hcl:#623a2f ecl:oth hgt:183cm byr:1920\n" +
            "\n" +
            "eyr:2030\n" +
            "ecl:amb pid:896953299\n" +
            "hgt:157cm byr:1934 hcl:#9c12d8 iyr:2015\n" +
            "\n" +
            "hcl:#cfa07d iyr:2011 byr:1974 pid:451819357 hgt:168cm ecl:grn eyr:2024\n" +
            "\n" +
            "iyr:2018\n" +
            "pid:908304519 hcl:#fffffd byr:1936 cid:203\n" +
            "ecl:amb hgt:76in\n" +
            "eyr:2029\n" +
            "\n" +
            "byr:1967\n" +
            "hgt:186cm\n" +
            "eyr:2026\n" +
            "hcl:#ceb3a1 ecl:grn\n" +
            "pid:594830518 iyr:2017\n" +
            "\n" +
            "pid:20921789 iyr:2024 hcl:z byr:2026 ecl:zzz hgt:153cm eyr:2037\n" +
            "\n" +
            "hcl:#888785 iyr:2016 cid:323 byr:1958 ecl:gry pid:118638859 eyr:2029\n" +
            "hgt:163cm\n" +
            "\n" +
            "hgt:167cm ecl:brn eyr:2020\n" +
            "pid:557999801\n" +
            "byr:1988\n" +
            "cid:273 iyr:2011\n" +
            "hcl:#fffffd\n" +
            "\n" +
            "ecl:gry pid:206008517 eyr:2022\n" +
            "hcl:#ceb3a1\n" +
            "byr:1983 hgt:187cm\n" +
            "\n" +
            "eyr:2020\n" +
            "byr:1931 cid:78\n" +
            "hcl:#6b5442 ecl:oth hgt:170cm pid:039713280 iyr:2015\n" +
            "\n" +
            "eyr:2024 ecl:amb\n" +
            "byr:2002 hgt:162cm hcl:#866857\n" +
            "iyr:2012 pid:696390563 cid:184\n" +
            "\n" +
            "hgt:189cm byr:1992 pid:712592503 iyr:2012 ecl:oth hcl:#602927\n" +
            "eyr:2029\n" +
            "\n" +
            "ecl:hzl\n" +
            "byr:1965 hgt:182cm eyr:2023\n" +
            "iyr:2014 hcl:#a97842\n" +
            "\n" +
            "byr:1927 ecl:gry\n" +
            "hcl:#d91aa0 pid:082227536 eyr:2021\n" +
            "iyr:2011\n" +
            "\n" +
            "eyr:2003 iyr:1953 byr:1954\n" +
            "cid:327 hgt:62in ecl:utc\n" +
            "hcl:z pid:#97c11a\n" +
            "\n" +
            "cid:252 pid:98689392\n" +
            "iyr:2020 hgt:103\n" +
            "hcl:298df8 byr:1934\n" +
            "ecl:oth eyr:2012\n" +
            "\n" +
            "hcl:#fffffd eyr:2020\n" +
            "byr:1993 ecl:brn\n" +
            "pid:338398225 iyr:2015 hgt:159cm\n" +
            "\n" +
            "iyr:2017 pid:624798709 hgt:151cm eyr:2029\n" +
            "ecl:gry cid:111\n" +
            "hcl:#866857\n" +
            "\n" +
            "byr:2010\n" +
            "ecl:hzl eyr:1975 hgt:150cm iyr:1930 hcl:159a9a\n" +
            "\n" +
            "iyr:2010\n" +
            "hcl:#7d3b0c eyr:2024 cid:224 hgt:163cm byr:1971 pid:631469024 ecl:grn\n" +
            "\n" +
            "ecl:hzl iyr:2017 hgt:167cm\n" +
            "hcl:#623a2f pid:417970460 byr:1949 eyr:2020\n" +
            "\n" +
            "eyr:2030\n" +
            "hgt:84 byr:2007 ecl:xry cid:153 pid:9655548750 iyr:1957\n" +
            "\n" +
            "ecl:oth hcl:#733820 cid:336 byr:1996 iyr:2014 pid:736143470 eyr:2025 hgt:182cm\n" +
            "\n" +
            "hgt:69in hcl:#623a2f\n" +
            "cid:126 iyr:2019 pid:638479310 eyr:2022 ecl:grn byr:1935\n" +
            "\n" +
            "cid:240\n" +
            "pid:804066884 byr:1987 hcl:#049f0e\n" +
            "eyr:2023\n" +
            "hgt:174cm\n" +
            "ecl:brn\n" +
            "iyr:2020\n" +
            "\n" +
            "ecl:amb iyr:2010\n" +
            "pid:200411701\n" +
            "cid:70 eyr:2023\n" +
            "hcl:#341e13 byr:1974 hgt:61in\n" +
            "\n" +
            "eyr:2022 hgt:186cm hcl:#18171d ecl:hzl pid:613033358\n" +
            "iyr:2014\n" +
            "\n" +
            "hgt:189cm\n" +
            "iyr:2020 pid:874302209 byr:1928 ecl:blu\n" +
            "hcl:#1c52f4\n" +
            "eyr:2029\n" +
            "\n" +
            "byr:2026\n" +
            "eyr:2007 pid:166cm iyr:2030 ecl:utc\n" +
            "hgt:137 hcl:8e8916\n" +
            "\n" +
            "pid:781251989\n" +
            "eyr:2029 hgt:178cm iyr:2010 byr:1942 hcl:#cfa07d\n" +
            "\n" +
            "pid:671017167 eyr:2030 ecl:amb byr:2002 hgt:166cm\n" +
            "iyr:2011 hcl:#7d3b0c\n" +
            "\n" +
            "pid:369327568 byr:1955 ecl:hzl iyr:2013 hcl:#341e13 eyr:2020 cid:90 hgt:154cm\n" +
            "\n" +
            "pid:169149205 iyr:1947 ecl:amb eyr:1977\n" +
            "byr:2003\n" +
            "hcl:z\n" +
            "hgt:75cm\n" +
            "\n" +
            "hcl:#cfa07d iyr:2016\n" +
            "eyr:2022 pid:941218673 byr:1999 cid:186\n" +
            "ecl:brn hgt:173cm\n" +
            "\n" +
            "hgt:159cm eyr:2021 byr:1962 hcl:#efcc98\n" +
            "pid:792538993 iyr:2011 ecl:blu\n" +
            "cid:222\n" +
            "\n" +
            "pid:#994231 byr:2024 iyr:1977 hcl:b98ff6 eyr:2010 hgt:71\n" +
            "ecl:#875a67\n" +
            "\n" +
            "byr:2007\n" +
            "iyr:2023 hgt:141\n" +
            "eyr:2021 ecl:grt pid:22002588\n" +
            "\n" +
            "hgt:190cm\n" +
            "pid:524515058\n" +
            "cid:217\n" +
            "ecl:grn byr:1999\n" +
            "eyr:2027\n" +
            "iyr:2019\n" +
            "\n" +
            "ecl:dne byr:2019 eyr:1942 hgt:62cm pid:5866040917\n" +
            "iyr:2018 hcl:z\n" +
            "\n" +
            "pid:754032301 byr:1985 eyr:2029 hgt:185cm iyr:2016\n" +
            "ecl:oth\n" +
            "\n" +
            "ecl:amb byr:1948 iyr:2010 hgt:157cm pid:153547581 eyr:2027 hcl:#cfa07d\n" +
            "\n" +
            "eyr:2026 byr:1942 pid:368975422\n" +
            "hcl:#733820\n" +
            "cid:322 hgt:188cm\n" +
            "iyr:2019\n" +
            "ecl:blu\n" +
            "\n" +
            "ecl:brn\n" +
            "pid:535822939 byr:1994\n" +
            "eyr:2027 iyr:2020 hcl:#18171d hgt:193cm\n" +
            "\n" +
            "pid:706755664\n" +
            "hcl:#7d3b0c\n" +
            "ecl:grn cid:304\n" +
            "hgt:152cm byr:1972 iyr:2013 eyr:2021\n" +
            "\n" +
            "hgt:163cm\n" +
            "byr:1922 iyr:2014 eyr:2028 pid:852815945 cid:324\n" +
            "ecl:brn hcl:53b08b\n" +
            "\n" +
            "hcl:#888785\n" +
            "eyr:2023\n" +
            "iyr:2020 byr:1962 ecl:blu\n" +
            "pid:407158186\n" +
            "cid:269\n" +
            "\n" +
            "ecl:blu\n" +
            "eyr:2027 pid:567155642 hcl:#a97842 hgt:74in byr:1995\n" +
            "iyr:2016\n" +
            "\n" +
            "iyr:2017 eyr:2020\n" +
            "pid:782403650\n" +
            "byr:1952 ecl:gry hgt:193cm cid:273 hcl:#efcc98\n" +
            "\n" +
            "byr:1963 eyr:2021\n" +
            "pid:639445576 hcl:#c0946f iyr:2013\n" +
            "cid:306 ecl:blu hgt:154cm\n" +
            "\n" +
            "hgt:68in cid:191\n" +
            "hcl:#7d3b0c\n" +
            "iyr:2017 byr:1935 ecl:gry\n" +
            "\n" +
            "ecl:brn iyr:2019\n" +
            "eyr:2021\n" +
            "hcl:#733820\n" +
            "byr:2017\n" +
            "pid:714110829 hgt:155cm cid:178\n" +
            "\n" +
            "cid:203 pid:455383907\n" +
            "ecl:grn byr:1965\n" +
            "hcl:#866857 eyr:2024 hgt:172cm iyr:2012\n" +
            "\n" +
            "iyr:2018 eyr:2033\n" +
            "pid:462538213 byr:1974 hcl:#c0946f\n" +
            "ecl:amb hgt:160cm\n" +
            "\n" +
            "hcl:#623a2f\n" +
            "pid:116799148 cid:336\n" +
            "ecl:grn eyr:2027\n" +
            "iyr:2020\n" +
            "byr:1976\n" +
            "\n" +
            "pid:654088396\n" +
            "ecl:utc eyr:2021\n" +
            "byr:2016\n" +
            "hcl:#866857\n" +
            "iyr:2030 hgt:191cm\n" +
            "\n" +
            "byr:1939\n" +
            "eyr:2023\n" +
            "iyr:2011 hgt:168cm\n" +
            "cid:141 ecl:brn\n" +
            "hcl:#6b5442\n" +
            "\n" +
            "eyr:2025 hgt:61in\n" +
            "byr:1977\n" +
            "ecl:brn iyr:2016 cid:198 pid:401742648\n" +
            "\n" +
            "ecl:brn\n" +
            "iyr:2012 eyr:2027\n" +
            "byr:1990 hcl:#6b5442\n" +
            "pid:476691172\n" +
            "hgt:72in\n" +
            "\n" +
            "cid:176 ecl:oth iyr:2011 hcl:#c0946f\n" +
            "eyr:2028\n" +
            "byr:1957 pid:959615191\n" +
            "\n" +
            "byr:2027\n" +
            "iyr:2021 hcl:#733820 hgt:165cm pid:6155507056\n" +
            "\n" +
            "iyr:2012 ecl:blu\n" +
            "pid:397461435 eyr:2022 byr:1993 hgt:170cm\n" +
            "hcl:#b59662 cid:185\n" +
            "\n" +
            "ecl:hzl byr:2015\n" +
            "hcl:z hgt:185cm eyr:2036 iyr:2017\n" +
            "pid:172cm\n" +
            "\n" +
            "ecl:oth\n" +
            "hgt:181cm iyr:2019\n" +
            "cid:113 byr:2000\n" +
            "hcl:#866857 pid:045077916 eyr:2029\n" +
            "\n" +
            "iyr:2013 ecl:grn\n" +
            "pid:717028913 byr:1953 eyr:2025\n" +
            "hgt:191cm hcl:#6b5442\n" +
            "\n" +
            "pid:825834003 eyr:2027 byr:1941\n" +
            "hgt:163cm iyr:2010 hcl:#6b5442 ecl:amb\n" +
            "\n" +
            "eyr:2026 hgt:59in\n" +
            "hcl:#e9ebf8 byr:1966\n" +
            "iyr:2018 pid:677886127 ecl:grn\n" +
            "\n" +
            "hcl:#888785 pid:771218458 ecl:hzl eyr:2029\n" +
            "cid:153 byr:1991 iyr:2011\n" +
            "hgt:76in\n" +
            "\n" +
            "hgt:161cm hcl:#888785\n" +
            "ecl:brn byr:1928 pid:913959218 eyr:2020 iyr:2013\n" +
            "\n" +
            "hgt:188cm eyr:2026\n" +
            "byr:1964 ecl:blu hcl:#733820 iyr:2017 pid:874400552\n" +
            "\n" +
            "ecl:hzl iyr:2017\n" +
            "cid:59 pid:130750853 byr:1964 eyr:2028 hgt:177cm hcl:#602927\n" +
            "\n" +
            "pid:200888672 ecl:oth iyr:2016 eyr:2020 hcl:#efcc98 hgt:163cm\n" +
            "\n" +
            "eyr:2026\n" +
            "ecl:gry\n" +
            "hgt:189cm\n" +
            "hcl:#c0946f iyr:2019 pid:741121671 byr:1971\n" +
            "\n" +
            "ecl:amb eyr:2028 hcl:#888785 iyr:2017 pid:060053163 byr:1952 hgt:191cm\n" +
            "\n" +
            "hcl:#55c45a\n" +
            "eyr:2022 ecl:blu\n" +
            "iyr:2019 pid:326991534\n" +
            "hgt:158cm\n" +
            "cid:149\n" +
            "\n" +
            "hcl:#a97842 iyr:2013 ecl:hzl byr:1941 hgt:179cm\n" +
            "\n" +
            "hgt:68in hcl:#18171d\n" +
            "eyr:2021 byr:1938 ecl:oth iyr:2015\n" +
            "pid:888616887\n" +
            "\n" +
            "eyr:2026 iyr:2018 ecl:oth byr:1990\n" +
            "hcl:#efcc98\n" +
            "pid:472330538\n" +
            "hgt:192cm\n" +
            "\n" +
            "byr:1933 ecl:grn hcl:#7d3b0c hgt:74in iyr:2011\n" +
            "eyr:2028 cid:55\n" +
            "\n" +
            "iyr:2014 hgt:165cm ecl:blu hcl:#18171d byr:1998 pid:601177268 cid:64 eyr:2027\n" +
            "\n" +
            "iyr:2011 ecl:grn cid:188 pid:440822084 eyr:2028\n" +
            "hcl:#c0946f byr:1987 hgt:154cm\n" +
            "\n" +
            "hcl:#f29c57\n" +
            "cid:114 iyr:2010\n" +
            "byr:1989 eyr:2023 hgt:61in\n" +
            "pid:166071094\n" +
            "\n" +
            "hgt:71cm\n" +
            "iyr:2022 byr:1965\n" +
            "ecl:#bb3dce pid:88829820 eyr:2040 hcl:z\n" +
            "\n" +
            "hgt:62in hcl:#7d3b0c pid:585528668\n" +
            "eyr:2028 ecl:oth\n" +
            "byr:1941\n" +
            "\n" +
            "ecl:oth eyr:2030\n" +
            "byr:1952\n" +
            "iyr:2018 pid:422437243 hgt:185cm\n" +
            "\n" +
            "pid:054717793 byr:1989 hcl:#18171d\n" +
            "iyr:2014\n" +
            "ecl:grn\n" +
            "eyr:2025 hgt:151cm\n" +
            "\n" +
            "eyr:2027\n" +
            "hcl:#cfa07d pid:071196833\n" +
            "cid:297 byr:1932 hgt:173in\n" +
            "ecl:grn iyr:2016\n" +
            "\n" +
            "hcl:#6b1c3d eyr:2026 pid:963034490 iyr:2011\n" +
            "hgt:175cm byr:1961 ecl:oth\n" +
            "\n" +
            "hgt:69in\n" +
            "hcl:#b6652a ecl:oth\n" +
            "pid:771661551 iyr:2016 eyr:2023 byr:1960\n" +
            "\n" +
            "cid:63\n" +
            "pid:190cm byr:2021 ecl:#252d02 eyr:1931\n" +
            "iyr:1966 hgt:101 hcl:dc9531\n" +
            "\n" +
            "byr:1976 eyr:1925\n" +
            "ecl:grt cid:203\n" +
            "iyr:2019\n" +
            "pid:170cm hgt:181in\n" +
            "\n" +
            "iyr:2014 ecl:amb\n" +
            "hgt:182cm cid:283 byr:1983\n" +
            "pid:307867769 eyr:2024 hcl:#cfa07d\n" +
            "\n" +
            "hgt:157cm hcl:#ceb3a1 eyr:2026 pid:109243500\n" +
            "byr:1926\n" +
            "iyr:2015 ecl:oth cid:330\n" +
            "\n" +
            "hcl:#602927 byr:1940 pid:389818848\n" +
            "iyr:2016 ecl:brn\n" +
            "hgt:68in eyr:2023\n" +
            "\n" +
            "ecl:brn pid:340990019\n" +
            "eyr:2020 iyr:2011\n" +
            "hcl:#fffffd hgt:175cm byr:2001\n" +
            "\n" +
            "cid:264\n" +
            "hgt:154cm pid:128094068\n" +
            "hcl:#888785 iyr:2013 eyr:2027\n" +
            "byr:1929 ecl:amb\n" +
            "\n" +
            "cid:270 hcl:#602927 hgt:156cm iyr:2018\n" +
            "byr:1983\n" +
            "eyr:2020 pid:621875145\n" +
            "ecl:gry\n" +
            "\n" +
            "cid:345 pid:68093057 ecl:grt\n" +
            "iyr:2019 byr:1992 hgt:109 hcl:35d6e4 eyr:1976\n" +
            "\n" +
            "pid:714839913 ecl:grn hcl:#733820 iyr:2020 hgt:153cm\n" +
            "byr:1996 eyr:2027\n" +
            "\n" +
            "pid:820650878 eyr:2027\n" +
            "hcl:#866857 byr:1957\n" +
            "iyr:2015 ecl:grn hgt:167cm\n" +
            "\n" +
            "pid:600488426\n" +
            "hcl:#ceb3a1 hgt:151cm\n" +
            "ecl:amb eyr:2021 byr:1936 iyr:2015 cid:326\n" +
            "\n" +
            "cid:256 hgt:169cm\n" +
            "iyr:2014\n" +
            "pid:261369952 eyr:2028 byr:1982\n" +
            "ecl:brn\n" +
            "hcl:#733820\n" +
            "\n" +
            "eyr:2021 iyr:2011\n" +
            "pid:745066100 hcl:#3bbbd5 byr:1998 ecl:amb hgt:166cm\n" +
            "cid:257\n" +
            "\n" +
            "ecl:#a38be3 cid:256 hgt:154 eyr:2033\n" +
            "byr:2006 pid:5154675209 hcl:z\n" +
            "\n" +
            "hgt:160cm cid:103 ecl:gry byr:2000 hcl:#a97842 eyr:2026 pid:528503931\n" +
            "iyr:2010\n" +
            "\n" +
            "eyr:2025 cid:131 iyr:2011\n" +
            "byr:2001\n" +
            "pid:346722892\n" +
            "hcl:#cc0362\n" +
            "hgt:168cm\n" +
            "ecl:brn\n" +
            "\n" +
            "hcl:#ceb3a1 iyr:2012\n" +
            "hgt:188cm pid:760916817 byr:1985\n" +
            "eyr:2020 ecl:oth\n" +
            "\n" +
            "hgt:179cm\n" +
            "cid:317\n" +
            "ecl:amb pid:411265118 iyr:2018\n" +
            "byr:1982 hcl:#733820 eyr:2028\n" +
            "\n" +
            "byr:1927 hcl:#7d3b0c iyr:2020 ecl:gry\n" +
            "hgt:155cm pid:937138356 eyr:2021\n" +
            "\n" +
            "hcl:#efcc98 pid:793804751 eyr:2022 byr:1961 hgt:193cm iyr:2016 cid:222\n" +
            "\n" +
            "pid:715207875 hcl:#18171d eyr:2030 byr:1974 hgt:157cm ecl:blu\n" +
            "iyr:2019\n" +
            "\n" +
            "eyr:2022 pid:134624402 hgt:159cm cid:154\n" +
            "byr:1938 hcl:#cfa07d\n" +
            "iyr:2018 ecl:gry\n" +
            "\n" +
            "ecl:oth eyr:2021\n" +
            "cid:259 pid:0484880795 hcl:#cfa07d hgt:189cm iyr:2019 byr:1958\n" +
            "\n" +
            "byr:1960\n" +
            "pid:752967111 iyr:2010 hcl:#52a9af\n" +
            "hgt:151cm ecl:amb eyr:2025\n" +
            "\n" +
            "eyr:2028 byr:1974 ecl:oth cid:348\n" +
            "hcl:#b6652a hgt:164cm iyr:2018\n" +
            "\n" +
            "eyr:2029\n" +
            "byr:1942 cid:232 iyr:2016 hgt:193cm\n" +
            "hcl:#733820 pid:175cm ecl:oth\n" +
            "\n" +
            "byr:1990 hcl:#b6652a eyr:2028 iyr:2011 pid:054326137 hgt:153cm ecl:blu\n" +
            "\n" +
            "byr:1933\n" +
            "pid:659875882 hgt:181cm\n" +
            "eyr:2023 iyr:2012\n" +
            "ecl:grn hcl:#18171d\n" +
            "\n" +
            "ecl:grn iyr:2019 hcl:#866857 byr:1946\n" +
            "eyr:2023 hgt:193cm pid:494553757\n" +
            "\n" +
            "cid:331\n" +
            "ecl:blu eyr:2021 hcl:#733820 hgt:174cm\n" +
            "iyr:2010 byr:1950 pid:405416908";
}
