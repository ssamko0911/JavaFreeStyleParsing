package services;

import entities.LibraryBookRecord;
import managers.LibraryBookRecordManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoadService {
    public FileLoadResult loadFile(File file) throws FileNotFoundException {
        try (Scanner fileReader = new Scanner(file)) {
            StringBuilder content = new StringBuilder();
            LibraryBookRecordManager libraryBookRecordManager = new LibraryBookRecordManager();
            LibraryBookRecord currentRecord = new LibraryBookRecord();
            LibraryBookRecordParser parser = new LibraryBookRecordParser();

            while (fileReader.hasNextLine()) {
                // TODO: fix populating objects
                String line = fileReader.nextLine();
                content.append(line).append(System.lineSeparator());

                if (line.startsWith("-")) {
                    libraryBookRecordManager.addRecord(currentRecord);
                    currentRecord = new LibraryBookRecord();
                } else if (parser.isFieldLabel(line.trim()) && fileReader.hasNextLine()) {
                    String value = fileReader.nextLine();
                    parser.setField(currentRecord, line, value);
                    content.append(value).append(System.lineSeparator());
                }
            }

            libraryBookRecordManager.addRecord(currentRecord);

            return new FileLoadResult(content.toString(), libraryBookRecordManager);
        }
    }
}
