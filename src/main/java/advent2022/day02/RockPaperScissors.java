package advent2022.day02;

import java.util.List;

import static core.Constants.SPACE;

public class RockPaperScissors {

    public RockPaperScissors() {
        //  empty constructor
    }

    public int processGame(List<String> rounds) {
        return processGame(rounds, false);
    }

    public int processGame(List<String> rounds, boolean isPartTwo) {
        int score = 0;
        for (String round : rounds) {
            if (isPartTwo) {
                score += partTwo(round);
            } else {
                score += processRound(round);
            }
        }
        return score;
    }

    private int processRound(String round) {
        int roundScore = 0;
        String[] parts = round.split(SPACE);
        switch (parts[1]) {
            case "X":   //  Rock
                roundScore += 1;
                break;
            case "Y":   //  Paper
                roundScore += 2;
                break;
            case "Z":   //  Scissors
                roundScore += 3;
                break;
            default:
                throw new IllegalArgumentException("Unexpected round. Expected X,Y or Z but was: " + parts[1]);
        }

        if (isWin(parts[0], parts[1])) {
            roundScore += 6;
        } else if (isDraw(parts[0], parts[1])) {
            roundScore += 3;
        }
        return roundScore;
    }

    private boolean isDraw(String opponent, String player) {
        return (opponent.equals("A") && player.equals("X")) ||
                (opponent.equals("B") && player.equals("Y")) ||
                (opponent.equals("C") && player.equals("Z"));
    }

    private boolean isWin(String opponent, String player) {
        return (opponent.equals("A") && player.equals("Y")) ||
                (opponent.equals("B") && player.equals("Z")) ||
                (opponent.equals("C") && player.equals("X"));
    }

    private int partTwo(String round) {
        int roundScore = 0;
        String[] parts = round.split(SPACE);
        switch (parts[1]) {
            case "X":   //  need to lose
                roundScore += scoreLossGiven(parts[0]);
                break;
            case "Y":   //  need to draw
                roundScore += 3;
                roundScore += scoreDrawGiven(parts[0]);
                break;
            case "Z":   //  need to win
                roundScore += 6;
                roundScore += scoreWinGiven(parts[0]);
                break;
            default:
                throw new IllegalArgumentException("Unexpected move. Expected X,Y or Z but was: " + parts[1]);
        }
        return roundScore;
    }

    private int scoreWinGiven(String given) {
        switch (given) {
            case "A":
                //  A (rock) is beaten by Y (paper)
                return 2;
            case "B":
                //  B (paper) is beaten by Z (scissors)
                return 3;
            case "C":
            default:
                //  C (scissors) is beaten by X (rock)
                return 1;
        }
    }

    private int scoreDrawGiven(String given) {
        switch (given) {
            case "A":
                //  A (rock) draws with X (rock)
                return 1;
            case "B":
                //  B (paper) draws with Y (paper)
                return 2;
            case "C":
            default:
                //  C (scissors) draws with Z (paper)
                return 3;
        }
    }

    private int scoreLossGiven(String given) {
        switch (given) {
            case "A":
                //  A (rock) beats Z (scissors)
                return 3;
            case "B":
                //  B (paper) beats X (rock)
                return 1;
            case "C":
            default:
                //  C (scissors) beats Y (paper)
                return 2;
        }
    }

}
