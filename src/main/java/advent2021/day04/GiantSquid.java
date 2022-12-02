package advent2021.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class GiantSquid {

    //  the winning score, board number, and number called
    private int score = 0;
    private int board = 0;
    private int number = 0;

    //  game numbers
    private int[] numbers;
    private List<BingoBoard> boards = new ArrayList<>();

    public GiantSquid() {

    }

    public void playGame() {
        boolean playing = true;
        Set<Integer> numberSet = new HashSet<>();
        for (int number : numbers) {
            numberSet.add(number);

            if (numberSet.size() < 5) {
                continue;   //  not enough numbers called to complete a game yet
            }

            for (int i = 0; i < boards.size(); i++) {
                BingoBoard board = boards.get(i);
                if (board.bingo(numberSet)) {
                    playing = false;
                    this.number = number;
                    this.board = i;
                    this.score = board.calculateScore(numberSet, number);
                    break;
                }
            }

            if (!playing) {
                break;
            }
        }
    }

    public int playToLastBoard() {
        Set<Integer> numberSet = new HashSet<>();
        for (int number : numbers) {
            numberSet.add(number);

            if (numberSet.size() < 5) {
                continue;   //  not enough numbers called to complete a game yet
            }

            int size = boards.size();
            for (int i = size - 1; i >= 0; i--) {
                BingoBoard board = boards.get(i);
                if (board.bingo(numberSet)) {
                    this.number = number;
                    this.board = i;
                    this.score = board.calculateScore(numberSet, number);
                    boards.remove(board);
                }
            }

            if (boards.isEmpty()) break;
        }

        return this.score;
    }

    public int winningScore() {
        return score;
    }

    class BingoBoard {

        private int[][] board;

        public BingoBoard(List<String> lines) {
            this.board = new int[5][5];
            for (int i = 0; i < lines.size(); i++) {
                StringTokenizer tokenizer = new StringTokenizer(lines.get(i));
                int[] ints = new int[tokenizer.countTokens()];
                for (int j = 0; j < ints.length; j++) {
                    ints[j] = Integer.parseInt(tokenizer.nextToken());
                }
                board[i] = ints;
            }
        }

        public boolean bingo(Set<Integer> numberSet) {

            //  horizontal check
            for (int i = 0; i < board.length; i++) {
                boolean allMatched = true;
                for (int j = 0; j < board[0].length; j++) {
                    if (!numberSet.contains(board[i][j])) {
                        allMatched = false;
                        break;
                    }
                }
                if (allMatched) {
                    return true;
                }
            }

            //  vertical check
            for (int i = 0; i < board.length; i++) {
                boolean allMatched = true;
                for (int j = 0; j < board[0].length; j++) {
                    if (!numberSet.contains(board[j][i])) {
                        allMatched = false;
                        break;
                    }
                }
                if (allMatched) {
                    return true;
                }
            }

            return false;
        }

        public int calculateScore(Set<Integer> numberSet, int winningNumber) {
            int sum = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (!numberSet.contains(board[i][j])) {
                        sum += board[i][j];
                    }
                }
            }
            return sum * winningNumber;
        }

    }


    public void loadFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            String line = scanner.nextLine();
            this.numbers = lineToNumbers(line);

            while (scanner.hasNextLine()) {
                readGameBoard(scanner);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readGameBoard(Scanner scanner) {
        scanner.nextLine();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lines.add(scanner.nextLine());
        }

        BingoBoard board = new BingoBoard(lines);
        this.boards.add(board);
    }

    private int[] lineToNumbers(String line) {
        String[] parts = line.split(",");
        int[] ints = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ints[i] = Integer.parseInt(parts[i]);
        }
        return ints;
    }
}
