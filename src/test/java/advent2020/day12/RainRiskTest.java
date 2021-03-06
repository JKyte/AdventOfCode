package advent2020.day12;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RainRiskTest {

    @Test
    public void testExample1() {
        RainRisk rain = new RainRisk();
        rain.loadInstructions(exampleInput);
        assertEquals(25, rain.getDistance());
    }

    @Test
    public void testPartOne() {
        RainRisk rain = new RainRisk();
        rain.loadInstructions(puzzleInput);
        assertEquals(362, rain.getDistance());
    }

    @Test
    public void testExample2() {
        RainRisk rain = new RainRisk();
        rain.loadInstructions(exampleInput);
        assertEquals(286, rain.getWaypointDistance());
    }

    @Test
    public void testPartTwo() {
        RainRisk rain = new RainRisk();
        rain.loadInstructions(puzzleInput);
        assertEquals(29895, rain.getWaypointDistance());
    }

    private String exampleInput = "F10\nN3\nF7\nR90\nF11";

    private String puzzleInput = "F93\nR90\nF81\nE3\nF80\nR90\nW4\nR90\nF64\nN1\nR90\nW4\nF11\nW1\nL180\nF28\nR90\nW4\nL180\nF95\nN3\nF71\nL90\nW1\nF1\nE2\nL90\nF85\nW3\nL90\nF28\nE3\nN5\nF47\nS2\nR90\nN2\nL90\nW4\nL180\nW3\nF51\nF77\nL90\nW5\nN5\nW3\nS5\nW5\nR180\nN1\nW3\nS5\nF36\nW1\nF34\nN4\nF40\nE2\nS3\nR90\nW5\nS2\nN4\nR90\nS2\nL90\nN4\nL90\nS2\nE1\nF2\nN4\nF65\nN1\nF46\nR180\nF60\nN4\nF45\nR90\nS3\nL90\nF70\nW4\nL270\nN4\nF49\nE3\nF52\nR180\nF5\nE5\nR90\nF43\nL90\nS4\nF54\nN1\nF7\nS2\nF91\nS4\nN3\nE3\nS4\nL180\nF15\nS2\nF90\nW5\nS2\nF80\nN4\nL90\nR90\nN4\nE2\nR180\nE5\nF62\nL90\nN5\nF77\nN5\nF75\nE2\nL90\nS4\nF55\nE1\nN5\nF57\nR90\nF6\nL90\nN3\nE5\nL180\nN2\nL270\nN5\nF8\nE2\nF88\nE3\nN3\nE4\nR90\nN1\nW5\nR90\nN4\nE3\nS3\nR90\nN1\nE2\nF4\nN4\nR90\nN3\nW5\nL180\nE1\nF2\nN4\nW5\nF80\nS4\nF7\nL180\nF96\nL90\nF16\nL90\nE4\nF78\nW2\nR90\nS3\nF29\nW4\nR90\nE4\nF39\nN1\nF48\nE4\nL180\nE1\nR180\nE2\nR90\nN3\nR180\nW2\nN5\nW5\nE3\nF25\nE1\nL180\nN5\nF44\nL90\nN4\nW4\nL90\nF72\nL90\nS1\nE5\nN5\nR90\nN1\nW3\nS5\nR90\nW1\nF14\nL90\nW2\nR90\nW3\nF76\nS5\nW5\nF93\nW4\nR90\nF57\nE3\nR90\nS3\nW1\nR90\nS3\nF8\nR90\nN2\nF46\nW4\nS1\nL90\nE4\nW5\nL90\nW2\nF69\nN5\nW5\nF80\nN3\nE4\nL90\nE4\nF25\nS3\nR180\nF77\nR90\nW2\nF19\nE4\nL180\nW2\nF37\nS2\nF68\nL90\nE2\nF66\nS1\nR90\nF66\nE2\nL180\nF97\nN3\nW4\nF43\nS4\nR180\nN1\nR270\nE3\nN2\nN3\nF65\nL90\nS2\nL90\nN3\nL90\nS3\nF23\nL270\nW3\nS5\nE2\nR90\nS1\nF85\nN3\nR90\nW4\nF58\nE1\nL90\nN3\nL90\nE2\nS1\nF14\nE2\nN5\nW1\nN3\nE1\nL90\nE3\nF43\nE3\nN3\nF21\nE4\nF53\nE2\nL180\nE4\nF20\nE2\nN2\nE5\nL90\nN4\nW3\nN4\nS2\nL90\nW3\nF96\nL90\nS3\nR90\nN1\nE3\nS5\nL270\nF41\nN5\nW5\nS1\nW5\nR90\nF79\nW3\nL90\nE3\nF22\nN1\nL90\nE2\nL90\nR90\nF20\nL90\nW3\nR90\nW2\nL180\nW4\nF57\nR180\nN3\nL90\nF36\nL90\nE2\nR90\nN2\nE3\nN1\nW4\nW4\nN3\nE5\nF54\nR180\nF98\nW1\nR90\nF21\nS1\nL90\nS2\nL90\nS2\nF90\nE4\nS2\nR90\nN5\nF25\nN4\nW3\nN2\nF27\nS3\nE3\nN3\nF15\nL180\nS4\nF62\nW2\nL180\nE2\nN2\nL90\nR90\nF97\nR90\nS1\nR90\nE2\nF16\nW2\nE1\nF89\nW1\nL180\nS3\nW2\nS3\nE1\nF92\nF30\nN1\nE2\nS1\nF76\nE1\nS5\nW5\nF28\nW4\nL90\nF44\nE4\nN5\nF25\nR90\nF59\nS4\nF58\nS2\nF19\nW5\nS4\nE5\nN3\nF37\nE1\nL90\nF40\nE5\nF56\nS2\nW5\nF73\nN5\nF2\nL90\nF18\nE2\nN5\nL90\nF56\nR90\nF18\nW1\nS5\nE1\nN1\nL90\nW2\nE4\nN4\nE1\nW4\nN4\nL90\nN3\nR90\nW5\nS5\nF2\nR180\nF96\nR90\nW3\nF26\nL90\nS4\nE2\nF43\nS2\nR90\nF61\nW5\nF93\nR90\nF95\nL90\nE4\nR90\nN4\nF47\nR90\nW5\nL90\nF42\nL90\nW5\nF87\nR90\nN1\nN2\nE4\nF64\nS5\nL270\nF86\nS4\nW3\nS1\nL90\nF72\nR270\nW1\nF17\nS1\nE2\nR90\nW1\nN1\nF42\nN5\nL90\nF87\nF66\nL90\nN2\nW2\nL90\nS5\nF7\nR90\nN4\nL270\nF2\nW1\nN4\nF94\nW5\nR180\nS4\nF15\nE4\nF76\nN1\nE2\nF68\nS3\nF50\nR270\nE5\nF77\nR90\nS3\nE2\nN3\nS4\nF39\nS1\nE5\nS3\nL90\nE4\nS2\nW3\nF54\nR90\nF44\nL90\nW3\nF59\nR90\nN3\nF37\nE1\nF75\nR90\nF31\nW3\nF70\nS5\nL90\nE1\nF67\nW4\nL90\nS1\nW5\nS1\nS4\nW2\nE2\nS3\nR90\nS2\nE5\nL90\nF43\nR90\nE1\nS5\nF42\nL90\nW1\nN2\nE1\nL180\nS4\nW1\nL90\nF81\nE1\nR180\nN5\nR180\nN5\nL180\nF65\nR90\nF64\nW4\nS4\nR90\nF70\nE1\nS1\nF50\nE2\nS4\nE4\nN2\nS1\nR90\nE4\nR90\nF70\nR90\nN4\nF71\nR180\nF80\nS3\nL90\nN5\nL180\nF11\nN1\nR90\nW2\nN1\nR90\nW4\nR90\nF67\nW3\nR90\nW5\nL90\nE4\nF90\nL90\nN4\nL180\nF48\nR90\nW2\nF94\nR90\nN4\nL90\nW2\nF2\nL90\nW1\nE5\nS2\nW5\nS5\nE2\nN3\nW5\nN1\nF98\nS3\nW3\nL90\nS3\nW4\nR90\nW1\nF64\nN5\nR90\nS5\nW5\nF84\nS1\nE3\nL90\nS3\nE5\nF6\nN4\nW1\nR90\nE4\nF14\nN1\nR90\nF31\nL90\nF24\nF4\nN4\nF54\nS3\nR270\nF98\nE1\nL180\nF2\nE4\nF70\nW1\nR180\nN5\nF23";
}
