package advent2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Solution for https://adventofcode.com/2018/day/3
 * <p>
 * Part 1: There are 110546 inches with 2 or more claims
 * <p>
 * Part 2: Square with no claims: claimId: 819 widthOffset: 882 heightOffset: 366 width: 17 height: 20
 */
public class NoMatterHowYouSliceIt implements Runnable {

    public static void main(String[] args) {
        NoMatterHowYouSliceIt sliceIt = new NoMatterHowYouSliceIt();
        sliceIt.run();
    }

    private String inputPath = "src/main/resources/advent2018/NoMatterHowYouSliceIt.txt";
    private boolean partTwo = false;

    public NoMatterHowYouSliceIt() {

    }

    @Override
    public void run() {
        List<Claim> claims = new LinkedList<>();
        int maxWidth = 0;
        int maxHeight = 0;
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            while (scanner.hasNext()) {
                Claim claim = new Claim(scanner.nextLine());
                int tmpWidth = claim.widthOffset + claim.width;
                int tmpHeight = claim.heightOffset + claim.width;
                if (tmpWidth > maxWidth) {
                    maxWidth = tmpWidth;
                }
                if (tmpHeight > maxHeight) {
                    maxHeight = tmpHeight;
                }
                claims.add(claim);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Max H: " + maxHeight + " Max W: " + maxWidth);

        int[][] fabric = new int[maxWidth][maxHeight];
        for (Claim claim : claims) {
            stakeClaim(fabric, claim);
        }

        System.out.println("Overlapping inches: " + countOverlaps(fabric, 2));

        for (Claim claim : claims) {
            if (validateClaim(fabric, claim)) {
                System.out.println("Square with no claims: " + claim.toString());
            }
        }
    }

    /**
     * Return the number of squares with claims equal to or greater than the claim count
     *
     * @param fabric
     * @param claimCount
     * @return
     */
    private int countOverlaps(int[][] fabric, int claimCount) {
        int overlaps = 0;
        for (int ii = 0; ii < fabric.length; ii++) {
            for (int jj = 0; jj < fabric[0].length; jj++) {
                if (fabric[ii][jj] >= claimCount) {
                    overlaps++;
                }
            }
        }
        return overlaps;
    }

    private boolean validateClaim(int[][] fabric, Claim claim) {
        for (int ii = claim.widthOffset; ii < claim.widthOffset + claim.width; ii++) {
            for (int jj = claim.heightOffset; jj < claim.heightOffset + claim.height; jj++) {
                if (fabric[ii][jj] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Stake a claim and increment the count of all squares
     *
     * @param fabric
     * @param claim
     */
    private void stakeClaim(int[][] fabric, Claim claim) {
        System.out.println("Staking claim: " + claim.toString());
        for (int ii = claim.widthOffset; ii < claim.widthOffset + claim.width; ii++) {
            for (int jj = claim.heightOffset; jj < claim.heightOffset + claim.height; jj++) {
                fabric[ii][jj]++;
            }
        }
    }

    private class Claim {
        public int claimId;
        public int widthOffset;
        public int heightOffset;
        public int width;
        public int height;

        public Claim(String claimStr) {
            String[] parts = claimStr.split(" ");
            String[] offsets = parts[2].replaceFirst(":", "").split(",");
            String[] area = parts[3].split("x");

            this.claimId = Integer.parseInt(parts[0].substring(1));
            this.widthOffset = Integer.parseInt(offsets[0]);
            this.heightOffset = Integer.parseInt(offsets[1]);
            this.width = Integer.parseInt(area[0]);
            this.height = Integer.parseInt(area[1]);
        }

        public Claim(int a, int b, int c, int d, int e) {
            this.claimId = a;
            this.widthOffset = b;
            this.heightOffset = c;
            this.width = d;
            this.height = e;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("claimId: ").append(this.claimId);
            sb.append(" widthOffset: ").append(this.widthOffset);
            sb.append(" heightOffset: ").append(this.heightOffset);
            sb.append(" width: ").append(this.width);
            sb.append(" height: ").append(this.height);
            return sb.toString();
        }
    }
}
