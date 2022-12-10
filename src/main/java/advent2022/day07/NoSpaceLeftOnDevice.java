package advent2022.day07;

import com.google.common.base.Joiner;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static core.Constants.CD;
import static core.Constants.DIR;
import static core.Constants.DOLLAR_SIGN;
import static core.Constants.DOTS;
import static core.Constants.LS;
import static core.Constants.SLASH;
import static core.Constants.SLASH_CHAR;
import static core.Constants.SPACE;

public class NoSpaceLeftOnDevice {

    private List<String> lines;
    private final Deque<String> dirs;
    private final Multimap<String, String> dirMap;
    private final TreeMap<String, Integer> fileSizes;
    private final TreeMap<String, Integer> dirSizes;

    private int fsSize;
    private int targetFreeSpace;

    public NoSpaceLeftOnDevice() {
        this.dirs = new ArrayDeque<>();
        this.dirMap = TreeMultimap.create();
        this.fileSizes = new TreeMap<>();
        this.dirSizes = new TreeMap<>();
    }

    public void withInput(List<String> input) {
        this.lines = input;
        process();
        calculateDirSizes();
    }

    private void process() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(DOLLAR_SIGN)) {
                i = processCmd(i);
            } else {
                throw new IllegalStateException("should never get here");
            }
        }
    }

    private int processCmd(int index) {
        String[] parts = lines.get(index).split(SPACE);
        switch (parts[1]) {
            case CD:
                handleChangeDirectory(parts[2]);
                return index;
            case LS:
                return handleList(index);
            default:
                throw new IllegalArgumentException("unhandled cmd: " + parts[1]);
        }
    }

    private void handleChangeDirectory(String target) {
        switch (target) {
            case SLASH:
                dirs.clear();
                dirs.push(target);
                break;
            case DOTS:
                if (dirs.size() > 1) {
                    dirs.pop();
                } else {
                    throw new IllegalStateException("invalid pop");
                }
                break;
            default:
                if (dirMap.get(makeKey()).contains(makeKey(target))) {
                    dirs.push(target);
                } else {
                    throw new IllegalStateException("invalid cd");
                }
                break;
        }
    }

    private int handleList(int index) {
        String dirKey = makeKey();
        while ((lines.size() > index + 1) && !lines.get(index + 1).startsWith(DOLLAR_SIGN)) {
            String[] splits = lines.get(index + 1).split(SPACE);
            if (splits[0].equals(DIR)) {
                dirMap.put(dirKey, makeKey(splits[1]));
            } else {
                int size = Integer.parseInt(splits[0]);
                String fileKey = makeKey(splits[1] + size);
                dirMap.put(dirKey, fileKey);
                fileSizes.put(fileKey, size);
            }
            index++;
        }
        return index;
    }

    private String makeKey() {
        return Joiner.on(SLASH_CHAR).join(dirs.descendingIterator());
    }

    private String makeKey(String ext) {
        return makeKey() + SLASH_CHAR + ext;
    }

    private void calculateDirSizes() {
        for (String k : dirMap.keySet()) {
            dirSizes.put(k, calculateDirSize(k));
        }
    }

    public int sumDirsUnderMaxSize(int maxSize) {
        int sum = 0;
        for (Integer v : dirSizes.values()) {
            if (v <= maxSize) {
                sum += v;
            }
        }
        return sum;
    }

    public int calculateDirSize(String key) {
        int size = 0;
        for (String k : dirMap.get(key)) {
            if (fileSizes.containsKey(k)) {
                size += fileSizes.get(k);
            } else if (dirMap.containsKey(k)) {
                int dirSize;
                if (dirSizes.containsKey(k)) {
                    dirSize = dirSizes.get(k);
                } else {
                    dirSize = calculateDirSize(k);
                    dirSizes.put(k, dirSize);
                }
                size += dirSize;
            } else {
                throw new IllegalStateException("key not found");
            }
        }
        return size;
    }

    public int findDirectoryToDelete() {
        int unused = fsSize - calculateDirSize(SLASH);
        int target = targetFreeSpace - unused;

        SortedSet<Integer> set = new TreeSet<>(dirSizes.values());
        for (Integer i : set) {
            if (i >= target) {
                return i;
            }
        }
        return -1;
    }

    public void setFsSize(int fsSize) {
        this.fsSize = fsSize;
    }

    public void setTargetFreeSpace(int targetFreeSpace) {
        this.targetFreeSpace = targetFreeSpace;
    }
}
