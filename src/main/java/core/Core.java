package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Core implements Runnable {

    public String inputPath = "src/main/resources/";

    protected String readLine() {
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            return scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
