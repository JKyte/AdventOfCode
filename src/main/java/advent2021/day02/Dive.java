package advent2021.day02;

import java.util.List;

public class Dive {

    private long horizontal = 0;
    private long depth = 0;
    private long aim = 0;

    public Dive() {

    }

    public Dive givenStart(int horizontal, int depth) {
        this.horizontal = horizontal;
        this.depth = depth;
        return this;
    }

    public Dive givenCommands(List<String> cmds) {
        String[] parts;
        long num;
        for (String cmd : cmds) {
            parts = cmd.split(" ");
            num = Long.parseLong(parts[1]);

            switch (parts[0]) {
                case "forward":
                    horizontal += num;
                    break;
                case "down":
                    depth += num;
                    break;
                case "up":
                    depth -= num;
                    break;
                default:
                    throw new IllegalStateException("Cannot handle cmd: " + cmd);
            }
        }
        return this;
    }

    public Dive givenAim(List<String> cmds) {
        String[] parts;
        long num;
        for (String cmd : cmds) {
            parts = cmd.split(" ");
            num = Long.parseLong(parts[1]);

            switch (parts[0]) {
                case "forward":
                    horizontal += num;
                    depth += aim * num;
                    break;
                case "down":
                    aim += num;
                    break;
                case "up":
                    aim -= num;
                    break;
                default:
                    throw new IllegalStateException("Cannot handle cmd: " + cmd);
            }
        }
        return this;
    }

    public long getHorizontalTimesDepth() {
        return horizontal * depth;
    }
}
