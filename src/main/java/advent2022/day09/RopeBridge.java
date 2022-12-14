package advent2022.day09;


import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RopeBridge {

    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final String RIGHT = "R";
    private static final String LEFT = "L";

    private final Set<Point> tailVisits;
    private final Point head;
    private final Point tail;

    public RopeBridge() {
        tailVisits = new HashSet<>();
        head = new Point(0, 0);
        tail = new Point(0, 0);
        tailVisits.add(new Point(tail));
    }

    public void withInput(List<String> inputs) {
        for (String input : inputs) {
            processMove(input);
        }
    }

    private void processMove(String input) {
        String dir = input.substring(0, 1);
        int num = Integer.parseInt(input.substring(2));
        processMove(dir, num);
    }

    private void processMove(String direction, int num) {
        for (int i = 0; i < num; i++) {
            switch (direction) {
                case UP:
                    head.translate(0, 1);
                    break;
                case DOWN:
                    head.translate(0, -1);
                    break;
                case RIGHT:
                    head.translate(1, 0);
                    break;
                case LEFT:
                default:
                    head.translate(-1, 0);
            }


            boolean moveX = Math.abs(head.x - tail.x) > 1;
            boolean moveY = Math.abs(head.y - tail.y) > 1;
            int deltaX = tail.x;
            int deltaY = tail.y;

            if (moveX) {
                if (head.x > tail.x) {
                    deltaX++;
                } else if (head.x < tail.x) {
                    deltaX--;
                }
            }

            if (moveY) {
                if (head.y > tail.y) {
                    deltaY++;
                } else if (head.y < tail.y) {
                    deltaY--;
                }
            }

            if (moveY || moveX) {
                tail.move(deltaX, deltaY);
                tailVisits.add(new Point(tail));
            }
        }
    }

    public int getUniqueVisits() {
        return tailVisits.size();
    }
}
