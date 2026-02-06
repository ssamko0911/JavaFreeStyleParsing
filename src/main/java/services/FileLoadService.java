package services;

import entities.LibraryBookRecord;
import enums.BookRecordLabel;
import managers.LibraryBookRecordManager;
import util.AppLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileLoadService {
    private static final AppLogger logger = AppLogger.getInstance();

    // TODO: fix catching FileNotFound e;
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
                    this.addValidLibraryBookRecord(currentRecord, libraryBookRecordManager);

                    currentRecord = new LibraryBookRecord();
                } else if (parser.isFieldLabel(line.trim())) {
                    String value = fileReader.readLine();
                    if (value != null) {
                        parser.setField(currentRecord, line, value);
                        content.append(value).append(System.lineSeparator());
                    }
                }
            }

            this.addValidLibraryBookRecord(currentRecord, libraryBookRecordManager);

            FileLoadResult result = new FileLoadResult(content.toString(), libraryBookRecordManager);
            FileLoadService.logger.info(String.format(BookRecordLabel.TOTAL_RECORDS_LOG.getLabel(), result.getLibraryBookRecordManager().getBookRecordsCount()));

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addValidLibraryBookRecord(LibraryBookRecord libraryBookRecord, LibraryBookRecordManager libraryBookRecordManager) {
        if (libraryBookRecord.getOclcNumber() != null || libraryBookRecord.getIsbn() != null) {
            libraryBookRecordManager.addRecord(libraryBookRecord);
            FileLoadService.logger.info(BookRecordLabel.NEW_RECORD_LOG.getLabel() + libraryBookRecord);
        }
    }
}
