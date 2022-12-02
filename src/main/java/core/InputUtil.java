package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputUtil {

    private static final String BASE_PATH = "src/main/resources/";

    private InputUtil() {
        //  empty constructor
    }

    public static List<String> toList(String filepath) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(BASE_PATH + filepath))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
