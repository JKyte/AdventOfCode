package advent2020.day12;

import org.apache.commons.lang3.StringUtils;

public class RainRisk {

    private int x = 0;
    private int y = 0;
    private char dir = 'E';

    private String[] instructions;


    public void loadInstructions(String input) {
        this.instructions = StringUtils.split(input, '\n');
    }

    public int getDistance() {
        for (String instruction : instructions) {
            char c = instruction.charAt(0);
            int i = Integer.parseInt(instruction.substring(1));

            switch (c) {
                case 'N':
                case 'S':
                case 'E':
                case 'W':
                    moveForward(c, i);
                    break;
                case 'L':
                case 'R':
                    rotate(c, i);
                    break;
                case 'F':
                    moveForward(i);
                    break;
            }
//            System.out.println(instruction + " pos: (" + x + "," + y + ") dir: " + dir);
        }

        return Math.abs(x) + Math.abs(y);
    }

    private void moveForward(int i) {
        moveForward(dir, i);
    }

    private void moveForward(char dir, int i) {
        switch (dir) {
            case 'N':
                y += i;
                break;
            case 'S':
                y -= i;
                break;
            case 'E':
                x += i;
                break;
            case 'W':
                x -= i;
                break;
        }
    }

    private void rotate(char c, int i) {
        String dirs = "NESW";
        int index = dirs.indexOf(dir);
        int dist = 0;
        if (i == 90) dist = 1;
        if (i == 180) dist = 2;
        if (i == 270) dist = 3;

        if (c == 'L') {
            index -= dist;
        } else if (c == 'R') {
            index += dist;
        }

        if (index < 0) index += 4;
        if (index > 3) index -= 4;

        this.dir = dirs.charAt(index);
    }

    private int waypointX = 10;
    private int waypointY = 1;

    public int getWaypointDistance() {
        for (String instruction : instructions) {
            char c = instruction.charAt(0);
            int i = Integer.parseInt(instruction.substring(1));

            switch (c) {
                case 'N':
                case 'S':
                case 'E':
                case 'W':
                    moveWaypoint(c, i);
                    break;
                case 'L':
                case 'R':
                    rotateWaypoint(c, i);
                    break;
                case 'F':
                    moveToWaypoint(i);
                    break;
            }
//            System.out.println(instruction + " pos: (" + x + "," + y + ") waypoint: (" + waypointX + "," + waypointY + ")");
        }
        return Math.abs(x) + Math.abs(y);
    }

    public void moveToWaypoint(int num) {
        for (int i = 0; i < num; i++) {
            this.x += waypointX;
            this.y += waypointY;
        }
    }

    public void moveWaypoint(char c, int i) {
        switch (c) {
            case 'N':
                waypointY += i;
                break;
            case 'S':
                waypointY -= i;
                break;
            case 'E':
                waypointX += i;
                break;
            case 'W':
                waypointX -= i;
                break;

        }
    }

    //  If rotate 180 flip values and signs
    //
    private void rotateWaypoint(char c, int i) {
        String log = "Rotate " + c + "" + i + " from (" + waypointX + "," + waypointY + ") to ";
        if (i == 180) {
            waypointX *= -1;
            waypointY *= -1;
        } else if ((c == 'L' && i == 90) || c == 'R' && i == 270) {
            int tmp = waypointX;
            waypointX = waypointY * -1;
            waypointY = tmp;
        } else if ((c == 'L' && i == 270) || (c == 'R' && i == 90)) {
            int tmp = waypointX * -1;
            waypointX = waypointY;
            waypointY = tmp;
        }
//        System.out.println(log + " (" + waypointX + "," + waypointY + ")");
    }
}
