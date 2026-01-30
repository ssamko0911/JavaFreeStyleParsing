package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoadService {
    public String loadFile(File file) throws FileNotFoundException {
        try (Scanner fileReader = new Scanner(file)) {
            StringBuilder content = new StringBuilder();

            while (fileReader.hasNextLine()) {
                content.append(fileReader.nextLine()).append(System.lineSeparator());
            }

            return content.toString();
        }
    }
}
