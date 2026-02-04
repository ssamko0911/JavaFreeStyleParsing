package services;

import entities.LibraryBookRecord;
import enums.BookRecordLabel;
import managers.LibraryBookRecordManager;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileLoadService {
    public FileLoadResult loadFile(File file) throws FileNotFoundException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            LibraryBookRecordManager libraryBookRecordManager = new LibraryBookRecordManager();
            LibraryBookRecord currentRecord = new LibraryBookRecord();
            LibraryBookRecordParser parser = new LibraryBookRecordParser();

            String line;
            while ((line = fileReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());

                if (line.startsWith(BookRecordLabel.OBJECT_SEPARATOR.getLabel())) {
                    if (currentRecord.getOclcNumber() != null || currentRecord.getTitle() != null) {
                        libraryBookRecordManager.addRecord(currentRecord);
                    }

                    currentRecord = new LibraryBookRecord();
                } else if (parser.isFieldLabel(line.trim())) {
                    String fieldLabel = line;
                    String value = fileReader.readLine();
                    if (value != null) {
                        parser.setField(currentRecord, fieldLabel, value);
                        content.append(value).append(System.lineSeparator());
                    }
                }
            }

            if (currentRecord.getOclcNumber() != null || currentRecord.getTitle() != null) {
                libraryBookRecordManager.addRecord(currentRecord);
            }

            return new FileLoadResult(content.toString(), libraryBookRecordManager);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
