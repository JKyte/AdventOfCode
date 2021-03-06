package advent2020.day08;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HandHeldHaltingTest {

    @Test
    public void testExample1() {
        HandheldHalting halt = new HandheldHalting();
        halt.loadInstructions(exampleOneInput);
        halt.runProgram();
        assertEquals(0, halt.getErrorCode());
        assertEquals(5, halt.getAccumulator());
    }

    @Test
    public void testPartOne() {
        HandheldHalting halt = new HandheldHalting();
        halt.loadInstructions(puzzleInput);
        halt.runProgram();
        assertEquals(0, halt.getErrorCode());
        assertEquals(1941, halt.getAccumulator());
    }

    @Test
    public void testExample2() {
        HandheldHalting halt = new HandheldHalting();
        halt.loadInstructions(exampleOneInput);
        ArrayList<ArrayList<String>> permutations = halt.permuteInstructions();

//        for (int i = 0; i < permutations.get(0).size(); i++) {
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < permutations.size(); j++) {
//                sb.append(permutations.get(j).get(i)).append('\t');
//            }
//            System.out.println(sb.toString());
//        }

        for (ArrayList<String> instructions : permutations) {
            halt.loadInstructions(instructions);
            halt.runProgram();
            if (halt.getErrorCode() == 1) {
                break;
            }
        }
        assertEquals(1, halt.getErrorCode());
        assertEquals(8, halt.getAccumulator());
    }

    @Test
    public void testPartTwo() {
        HandheldHalting halt = new HandheldHalting();
        halt.loadInstructions(puzzleInput);
        ArrayList<ArrayList<String>> permutations = halt.permuteInstructions();

//        for (int i = 0; i < permutations.get(0).size(); i++) {
//            StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < permutations.size(); j++) {
//                sb.append(permutations.get(j).get(i)).append('\t');
//            }
//            System.out.println(sb.toString());
//        }

        for (int i = 0; i < permutations.size(); i++) {

            ArrayList<String> instructions = permutations.get(i);
            halt.loadInstructions(instructions);
            halt.runProgram();
            if (halt.getErrorCode() == 1) {
                break;
            }
//            System.out.println("running " + i + "/" + permutations.size() + " acc: " + halt.getAccumulator());
        }
        assertEquals(1, halt.getErrorCode());
        assertEquals(2096, halt.getAccumulator());
    }

    private String exampleOneInput = "nop +0\n" +
            "acc +1\n" +
            "jmp +4\n" +
            "acc +3\n" +
            "jmp -3\n" +
            "acc -99\n" +
            "acc +1\n" +
            "jmp -4\n" +
            "acc +6";

    private String puzzleInput = "jmp +265\n" +
            "jmp +326\n" +
            "acc +41\n" +
            "acc +21\n" +
            "nop +255\n" +
            "jmp +104\n" +
            "jmp +563\n" +
            "jmp +568\n" +
            "acc -12\n" +
            "acc -7\n" +
            "jmp +9\n" +
            "jmp +3\n" +
            "acc -8\n" +
            "jmp +360\n" +
            "acc -10\n" +
            "acc +35\n" +
            "jmp +527\n" +
            "acc +27\n" +
            "jmp +176\n" +
            "jmp +511\n" +
            "acc +27\n" +
            "acc -18\n" +
            "acc +7\n" +
            "jmp +272\n" +
            "jmp +1\n" +
            "acc +45\n" +
            "jmp -24\n" +
            "acc +47\n" +
            "jmp -26\n" +
            "jmp +344\n" +
            "acc -17\n" +
            "acc +26\n" +
            "acc -7\n" +
            "jmp +193\n" +
            "acc +45\n" +
            "jmp +238\n" +
            "acc +13\n" +
            "acc +0\n" +
            "acc -13\n" +
            "acc +33\n" +
            "jmp +381\n" +
            "acc +16\n" +
            "acc -11\n" +
            "acc +14\n" +
            "acc +21\n" +
            "jmp +194\n" +
            "acc +48\n" +
            "acc +14\n" +
            "nop +271\n" +
            "jmp -8\n" +
            "acc +24\n" +
            "acc -6\n" +
            "acc +36\n" +
            "jmp +501\n" +
            "jmp -35\n" +
            "jmp +1\n" +
            "jmp +294\n" +
            "acc -3\n" +
            "jmp +181\n" +
            "nop +371\n" +
            "acc -12\n" +
            "jmp +198\n" +
            "nop +120\n" +
            "jmp +108\n" +
            "acc +45\n" +
            "acc +46\n" +
            "nop +193\n" +
            "jmp +346\n" +
            "acc +30\n" +
            "acc +26\n" +
            "jmp +200\n" +
            "acc +0\n" +
            "nop +187\n" +
            "acc +31\n" +
            "acc +30\n" +
            "jmp +40\n" +
            "acc +39\n" +
            "acc +2\n" +
            "acc +40\n" +
            "jmp +316\n" +
            "jmp +279\n" +
            "acc +0\n" +
            "acc +11\n" +
            "jmp +120\n" +
            "acc +32\n" +
            "nop +336\n" +
            "acc +13\n" +
            "jmp +178\n" +
            "acc -11\n" +
            "jmp +144\n" +
            "jmp +136\n" +
            "acc -3\n" +
            "nop +245\n" +
            "acc +34\n" +
            "jmp -23\n" +
            "acc -5\n" +
            "acc -11\n" +
            "jmp +217\n" +
            "acc +28\n" +
            "acc +22\n" +
            "acc +18\n" +
            "jmp +329\n" +
            "jmp +1\n" +
            "nop +350\n" +
            "nop -45\n" +
            "acc +13\n" +
            "jmp -87\n" +
            "acc -13\n" +
            "jmp +479\n" +
            "acc +31\n" +
            "acc -19\n" +
            "nop +342\n" +
            "jmp -78\n" +
            "acc +18\n" +
            "jmp +1\n" +
            "jmp +1\n" +
            "acc +18\n" +
            "jmp +278\n" +
            "nop +328\n" +
            "acc +6\n" +
            "jmp +1\n" +
            "acc -11\n" +
            "jmp +77\n" +
            "jmp +4\n" +
            "acc +32\n" +
            "acc +48\n" +
            "jmp +188\n" +
            "acc +14\n" +
            "acc +32\n" +
            "jmp +122\n" +
            "acc -6\n" +
            "acc -16\n" +
            "jmp -42\n" +
            "acc +32\n" +
            "acc +26\n" +
            "acc +33\n" +
            "jmp +48\n" +
            "acc +3\n" +
            "jmp +1\n" +
            "jmp +163\n" +
            "acc +34\n" +
            "acc +17\n" +
            "jmp -58\n" +
            "nop +254\n" +
            "acc +26\n" +
            "jmp +223\n" +
            "acc +7\n" +
            "nop +94\n" +
            "acc +12\n" +
            "jmp +433\n" +
            "acc +30\n" +
            "acc -17\n" +
            "acc +3\n" +
            "acc +50\n" +
            "jmp -95\n" +
            "jmp +1\n" +
            "acc +42\n" +
            "jmp -146\n" +
            "acc +12\n" +
            "acc +33\n" +
            "jmp -101\n" +
            "acc +18\n" +
            "jmp +244\n" +
            "nop +243\n" +
            "jmp -130\n" +
            "acc -8\n" +
            "jmp +55\n" +
            "acc +39\n" +
            "acc +45\n" +
            "nop +2\n" +
            "jmp +239\n" +
            "acc -19\n" +
            "acc +23\n" +
            "acc +36\n" +
            "jmp +59\n" +
            "acc -14\n" +
            "acc +29\n" +
            "jmp -158\n" +
            "acc +31\n" +
            "acc +6\n" +
            "jmp +223\n" +
            "jmp +126\n" +
            "jmp +306\n" +
            "jmp +214\n" +
            "acc -16\n" +
            "jmp +102\n" +
            "acc -6\n" +
            "acc +19\n" +
            "jmp -174\n" +
            "jmp +283\n" +
            "acc -13\n" +
            "acc +12\n" +
            "acc -8\n" +
            "jmp +72\n" +
            "jmp +252\n" +
            "acc +16\n" +
            "acc +26\n" +
            "nop -19\n" +
            "jmp +377\n" +
            "jmp -15\n" +
            "acc -7\n" +
            "acc +34\n" +
            "jmp +352\n" +
            "jmp -101\n" +
            "jmp -154\n" +
            "acc +32\n" +
            "nop -1\n" +
            "acc +49\n" +
            "jmp -167\n" +
            "jmp +110\n" +
            "jmp +127\n" +
            "acc -3\n" +
            "acc +17\n" +
            "jmp +330\n" +
            "acc +27\n" +
            "jmp -50\n" +
            "acc +25\n" +
            "acc +8\n" +
            "acc +21\n" +
            "acc +4\n" +
            "jmp +189\n" +
            "jmp -157\n" +
            "nop +231\n" +
            "acc +27\n" +
            "acc +27\n" +
            "jmp +77\n" +
            "acc +6\n" +
            "jmp -198\n" +
            "nop +274\n" +
            "acc -16\n" +
            "acc +31\n" +
            "acc +5\n" +
            "jmp +122\n" +
            "jmp -30\n" +
            "nop -79\n" +
            "acc +43\n" +
            "acc +24\n" +
            "acc -1\n" +
            "jmp +349\n" +
            "jmp +80\n" +
            "jmp +352\n" +
            "acc +15\n" +
            "acc +6\n" +
            "acc +46\n" +
            "acc -11\n" +
            "jmp -35\n" +
            "acc -9\n" +
            "acc -16\n" +
            "acc +22\n" +
            "nop -71\n" +
            "jmp +280\n" +
            "acc +28\n" +
            "acc +17\n" +
            "jmp +127\n" +
            "acc -15\n" +
            "acc +14\n" +
            "acc +0\n" +
            "acc +45\n" +
            "jmp +311\n" +
            "acc -19\n" +
            "jmp +309\n" +
            "acc +36\n" +
            "acc +7\n" +
            "nop +102\n" +
            "jmp -31\n" +
            "nop +278\n" +
            "nop +259\n" +
            "acc +35\n" +
            "jmp -86\n" +
            "jmp +1\n" +
            "jmp +84\n" +
            "acc +30\n" +
            "jmp -111\n" +
            "jmp +263\n" +
            "acc -14\n" +
            "acc -8\n" +
            "acc +7\n" +
            "jmp -263\n" +
            "jmp -259\n" +
            "jmp -14\n" +
            "acc +26\n" +
            "acc +4\n" +
            "jmp -56\n" +
            "acc +31\n" +
            "acc +49\n" +
            "acc +42\n" +
            "jmp +263\n" +
            "acc -15\n" +
            "acc -13\n" +
            "acc -7\n" +
            "acc +35\n" +
            "jmp +270\n" +
            "acc -3\n" +
            "acc +31\n" +
            "jmp -148\n" +
            "acc +8\n" +
            "acc +14\n" +
            "jmp -247\n" +
            "acc -1\n" +
            "nop +255\n" +
            "acc -15\n" +
            "jmp +140\n" +
            "acc +38\n" +
            "nop +106\n" +
            "acc +29\n" +
            "jmp +244\n" +
            "jmp +62\n" +
            "acc +5\n" +
            "nop -218\n" +
            "acc +47\n" +
            "acc -18\n" +
            "jmp +208\n" +
            "nop +47\n" +
            "jmp +46\n" +
            "acc +27\n" +
            "jmp +126\n" +
            "acc +50\n" +
            "nop +129\n" +
            "jmp -147\n" +
            "nop -278\n" +
            "jmp +1\n" +
            "acc +37\n" +
            "acc -17\n" +
            "jmp +17\n" +
            "acc +18\n" +
            "acc +21\n" +
            "jmp -121\n" +
            "acc +12\n" +
            "acc +37\n" +
            "acc +48\n" +
            "acc +24\n" +
            "jmp -176\n" +
            "acc +18\n" +
            "acc -3\n" +
            "nop -169\n" +
            "acc -4\n" +
            "jmp +23\n" +
            "acc +42\n" +
            "jmp +30\n" +
            "jmp +15\n" +
            "acc +33\n" +
            "acc +33\n" +
            "acc +36\n" +
            "acc -7\n" +
            "jmp +262\n" +
            "acc -16\n" +
            "jmp -27\n" +
            "acc -14\n" +
            "jmp +17\n" +
            "jmp -79\n" +
            "jmp +242\n" +
            "acc +1\n" +
            "acc -12\n" +
            "jmp -3\n" +
            "acc +11\n" +
            "acc +44\n" +
            "nop -254\n" +
            "jmp +52\n" +
            "jmp -294\n" +
            "acc -9\n" +
            "acc +50\n" +
            "acc -9\n" +
            "jmp -229\n" +
            "acc +6\n" +
            "jmp +211\n" +
            "nop -132\n" +
            "jmp +136\n" +
            "jmp +74\n" +
            "acc +39\n" +
            "acc +18\n" +
            "jmp +51\n" +
            "nop -281\n" +
            "jmp -211\n" +
            "nop -19\n" +
            "jmp +114\n" +
            "nop -97\n" +
            "jmp +1\n" +
            "nop -282\n" +
            "acc +45\n" +
            "jmp +30\n" +
            "jmp -191\n" +
            "acc -13\n" +
            "acc -4\n" +
            "acc -8\n" +
            "jmp +159\n" +
            "acc +36\n" +
            "acc +21\n" +
            "acc -13\n" +
            "acc +3\n" +
            "jmp -266\n" +
            "acc +45\n" +
            "acc +29\n" +
            "nop -55\n" +
            "acc +39\n" +
            "jmp +121\n" +
            "jmp +58\n" +
            "jmp -101\n" +
            "acc -17\n" +
            "acc +44\n" +
            "jmp -319\n" +
            "acc -15\n" +
            "acc -7\n" +
            "jmp -132\n" +
            "acc +31\n" +
            "jmp +165\n" +
            "jmp -191\n" +
            "jmp +87\n" +
            "acc +23\n" +
            "jmp +54\n" +
            "acc +6\n" +
            "nop -330\n" +
            "jmp +26\n" +
            "jmp -9\n" +
            "acc +43\n" +
            "acc +50\n" +
            "acc +49\n" +
            "jmp +63\n" +
            "jmp +1\n" +
            "acc -6\n" +
            "acc +17\n" +
            "jmp -311\n" +
            "acc +50\n" +
            "acc -13\n" +
            "acc -15\n" +
            "acc +33\n" +
            "jmp -279\n" +
            "acc +7\n" +
            "acc -7\n" +
            "acc +40\n" +
            "jmp -374\n" +
            "acc +18\n" +
            "acc -14\n" +
            "acc +42\n" +
            "jmp -106\n" +
            "acc +49\n" +
            "acc +50\n" +
            "jmp -156\n" +
            "jmp -314\n" +
            "acc +28\n" +
            "acc +49\n" +
            "jmp +114\n" +
            "acc +15\n" +
            "jmp -12\n" +
            "acc +11\n" +
            "acc +9\n" +
            "jmp -386\n" +
            "jmp +1\n" +
            "jmp -376\n" +
            "acc +6\n" +
            "acc -9\n" +
            "acc -2\n" +
            "acc +49\n" +
            "jmp +36\n" +
            "acc -2\n" +
            "jmp +1\n" +
            "acc -2\n" +
            "jmp -361\n" +
            "acc -14\n" +
            "acc -16\n" +
            "nop -452\n" +
            "acc +40\n" +
            "jmp -107\n" +
            "nop -378\n" +
            "acc -17\n" +
            "acc +26\n" +
            "acc -11\n" +
            "jmp -272\n" +
            "acc +9\n" +
            "acc +8\n" +
            "acc +20\n" +
            "acc -19\n" +
            "jmp -106\n" +
            "acc -13\n" +
            "jmp -466\n" +
            "acc +40\n" +
            "acc +43\n" +
            "acc +28\n" +
            "acc +24\n" +
            "jmp +15\n" +
            "acc +21\n" +
            "nop -456\n" +
            "acc +7\n" +
            "jmp -97\n" +
            "acc +46\n" +
            "jmp +1\n" +
            "acc -5\n" +
            "acc +49\n" +
            "jmp +38\n" +
            "acc +42\n" +
            "jmp -470\n" +
            "acc +33\n" +
            "acc -10\n" +
            "jmp +57\n" +
            "acc -19\n" +
            "acc +10\n" +
            "acc +29\n" +
            "jmp -218\n" +
            "acc +2\n" +
            "acc +19\n" +
            "acc -4\n" +
            "acc -16\n" +
            "jmp -187\n" +
            "acc +41\n" +
            "acc +16\n" +
            "jmp -414\n" +
            "acc +30\n" +
            "acc +1\n" +
            "jmp -229\n" +
            "acc -2\n" +
            "acc +42\n" +
            "jmp -269\n" +
            "acc +39\n" +
            "acc -2\n" +
            "acc +7\n" +
            "jmp -300\n" +
            "jmp -301\n" +
            "acc -4\n" +
            "jmp +1\n" +
            "jmp -357\n" +
            "acc +22\n" +
            "acc +47\n" +
            "jmp +4\n" +
            "acc +45\n" +
            "nop -428\n" +
            "jmp -115\n" +
            "nop -402\n" +
            "jmp -312\n" +
            "acc -3\n" +
            "acc +2\n" +
            "jmp -345\n" +
            "acc +49\n" +
            "acc -12\n" +
            "acc +30\n" +
            "acc +21\n" +
            "jmp -335\n" +
            "jmp -440\n" +
            "acc -8\n" +
            "acc +24\n" +
            "jmp -30\n" +
            "acc -14\n" +
            "acc +32\n" +
            "acc +11\n" +
            "jmp -188\n" +
            "nop -7\n" +
            "acc +15\n" +
            "acc -14\n" +
            "jmp +53\n" +
            "acc +5\n" +
            "jmp -366\n" +
            "acc -13\n" +
            "acc +24\n" +
            "jmp -492\n" +
            "acc +38\n" +
            "jmp -258\n" +
            "acc +47\n" +
            "jmp -40\n" +
            "nop -485\n" +
            "acc -13\n" +
            "acc -2\n" +
            "acc +0\n" +
            "jmp -154\n" +
            "acc +25\n" +
            "acc +38\n" +
            "acc +47\n" +
            "jmp -257\n" +
            "acc +0\n" +
            "acc +37\n" +
            "acc +32\n" +
            "jmp -549\n" +
            "acc +15\n" +
            "acc +29\n" +
            "acc +29\n" +
            "acc +5\n" +
            "jmp -111\n" +
            "jmp -392\n" +
            "acc +15\n" +
            "acc +24\n" +
            "acc +38\n" +
            "jmp +9\n" +
            "nop -299\n" +
            "nop -381\n" +
            "jmp -552\n" +
            "acc +50\n" +
            "nop -488\n" +
            "acc +45\n" +
            "jmp -305\n" +
            "jmp -404\n" +
            "acc +34\n" +
            "jmp -410\n" +
            "acc +15\n" +
            "acc +25\n" +
            "jmp -332\n" +
            "acc +2\n" +
            "jmp -388\n" +
            "acc +31\n" +
            "acc +45\n" +
            "nop -555\n" +
            "nop -247\n" +
            "jmp -248\n" +
            "acc +3\n" +
            "jmp -576\n" +
            "acc +22\n" +
            "nop -420\n" +
            "acc +36\n" +
            "acc +33\n" +
            "jmp -372\n" +
            "nop -551\n" +
            "acc +27\n" +
            "nop -567\n" +
            "nop -554\n" +
            "jmp +1";
}
