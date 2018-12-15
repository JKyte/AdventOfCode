package advent2018;

import core.Core;

import java.util.LinkedList;
import java.util.List;

/**
 * Solution for https://adventofcode.com/2018/day/9
 * <p>
 * Part 1: 412127
 * <p>
 * Part 2: 3482394794
 * <p>
 * Note: used a python solution after proving the java solution would have worked.
 */
public class MarbleMania extends Core {

    public static void main(String[] args) {
        MarbleMania mm = new MarbleMania();
        mm.run();
    }

    public MarbleMania() {
        this.inputPath += "advent2018/MarbleMania.txt";
    }

    @Override
    public void run() {
        List<String> lines = readLines();
        for (String line : lines) {
            playGame(line.split(" "));
        }
    }

    private void playGame(String[] parts) {
        int numPlayers = Integer.parseInt(parts[0]);
        int numMarbles = Integer.parseInt(parts[6]);
        playGame(numPlayers, numMarbles);
    }

    private void playGame(int numPlayers, int numMarbles) {
        int[] playerScores = new int[numPlayers];
        LinkedList<Integer> circle = new LinkedList<>();
        int curIndex = 0;
        int orig = numMarbles;
        int percentComplete = 0;
        numMarbles *= 100;
        System.out.println("Simulating game with " + numMarbles + " marbles.");
        int removeIndex;
        int insertIndex;
        long start = System.currentTimeMillis();
        for (int ii = 0; ii < numMarbles; ii++) {
            if (ii % orig == 0) {
                System.out.println(percentComplete++ + "% in " + (System.currentTimeMillis() - start) / 1000 + " seconds.");
            }

            if (ii != 0 && ii % 23 == 0) {
                //  Player adds this marble's score
                playerScores[ii % playerScores.length] += ii;

                //  Remove marble 7 places to the left, adding value to the score
                removeIndex = curIndex - 7;
                if (removeIndex < 0) {
                    removeIndex += circle.size();
                }
                playerScores[ii % playerScores.length] += circle.remove(removeIndex);

                //  Cur marble set to 7 to the left
                curIndex = removeIndex;
                continue;
            }

            if (ii == 0 || ii == 1) {
                circle.add(ii);
                curIndex = circle.indexOf(ii);
            } else {
                insertIndex = (curIndex + 2) % circle.size();
                circle.add(insertIndex, ii);
                curIndex = insertIndex;
            }
//            printCircle(circle, curIndex, (ii % playerScores.length));
        }
        printHighScore(playerScores, circle.getLast());
    }

    private void printCircle(LinkedList<Integer> circle, int curIndex, int player) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(player).append("] ");
        for (int ii = 0; ii < circle.size(); ii++) {
            if (ii == curIndex) {
                sb.append("(").append(circle.get(ii)).append(")");
            } else {
                sb.append(" ").append(circle.get(ii)).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    private void printHighScore(int[] playerScores, int value) {
        int highScore = 0;
        for (int ii = 0; ii < playerScores.length; ii++) {
            if (playerScores[ii] > highScore) {
                highScore = playerScores[ii];
            }
        }
        System.out.println("Last Marble: " + value + " High Score: " + highScore);
    }
}
