package advent2022.day08;

import java.util.List;

public class TreetopTreeHouse {

    int[][] map;

    public TreetopTreeHouse() {
        //  empty constructor
    }

    public void withInput(List<String> input) {
        int len = input.size();
        int width = input.get(0).length();
        map = new int[len][width];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map[i][j] = Integer.parseInt("" + input.get(i).charAt(j));
            }
        }
    }


    public int countVisibleTrees() {
        int visible = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (isVisible(i, j)) {
                    visible++;
                }
            }
        }
        return visible;
    }

    private boolean isVisible(int x, int y) {
        //  @formatter:off
        return isVisibleNorth(x, y) || isVisibleSouth(x, y) ||
                isVisibleEast(x, y) || isVisibleWest(x, y);
        //  @formatter:on
    }

    private boolean isVisibleNorth(int x, int y) {
        int center = map[x][y];
        while (x > 0) {
            if (map[--x][y] >= center) {
                return false;
            }
        }
        return true;
    }

    private boolean isVisibleSouth(int x, int y) {
        int center = map[x][y];
        while (x < map[0].length - 1) {
            if (map[++x][y] >= center) {
                return false;
            }
        }
        return true;
    }

    private boolean isVisibleEast(int x, int y) {
        int center = map[x][y];
        while (y < map.length - 1) {
            if (map[x][++y] >= center) {
                return false;
            }
        }
        return true;
    }

    private boolean isVisibleWest(int x, int y) {
        int center = map[x][y];
        while (y > 0) {
            if (map[x][--y] >= center) {
                return false;
            }
        }
        return true;
    }

    public int findHighestScenicScore() {
        int score = 0;
        int currentScore;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                currentScore = scoreTree(i, j);
                if (currentScore >= score) {
                    score = currentScore;
                }
            }
        }
        return score;
    }

    public int scoreTree(int x, int y) {
        int n = scoreNorth(x, y);
        int s = scoreSouth(x, y);
        int e = scoreEast(x, y);
        int w = scoreWest(x, y);
        return n * s * e * w;
    }

    private int scoreNorth(int x, int y) {
        int center = map[x][y];
        int n = 0;
        while (x > 0) {
            n++;
            if (map[--x][y] >= center) break;
        }
        return n;
    }

    private int scoreSouth(int x, int y) {
        int center = map[x][y];
        int s = 0;
        while (x < map[0].length - 1) {
            s++;
            if (map[++x][y] >= center) break;
        }
        return s;
    }

    private int scoreEast(int x, int y) {
        int center = map[x][y];
        int e = 0;
        while (y < map.length - 1) {
            e++;
            if (map[x][++y] >= center) break;
        }
        return e;
    }

    private int scoreWest(int x, int y) {
        int center = map[x][y];
        int w = 0;
        while (y > 0) {
            w++;
            if (map[x][--y] >= center) break;
        }
        return w;
    }
}
