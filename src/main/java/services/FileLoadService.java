package services;

import entities.LibraryBookRecord;
import enums.BookRecordLabel;
import managers.LibraryBookRecordManager;
import util.AppLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileLoadService {
    private LibraryBookRecordManager manager;
    private LibraryBookRecordParser parser;

    private static final AppLogger logger = AppLogger.getInstance();

    public FileLoadService(LibraryBookRecordManager manager, LibraryBookRecordParser parser) {
        this.manager = manager;
        this.parser = parser;
    }

    // TODO: fix catching FileNotFound e;
    public FileLoadResult loadFile(File file) throws FileNotFoundException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            LibraryBookRecord currentRecord = new LibraryBookRecord();

            String line;
            while ((line = fileReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());

                if (line.startsWith(BookRecordLabel.OBJECT_SEPARATOR.getLabel())) {
                    this.addValidLibraryBookRecord(currentRecord);

                    currentRecord = new LibraryBookRecord();
                } else if (this.parser.isFieldLabel(line.trim())) {
                    String value = fileReader.readLine();
                    if (value != null) {
                        this.parser.setField(currentRecord, line, value);
                        content.append(value).append(System.lineSeparator());
                    }
                }
            }

            this.addValidLibraryBookRecord(currentRecord);

            FileLoadResult result = new FileLoadResult(content.toString(), this.manager);
            FileLoadService.logger.info(String.format(BookRecordLabel.TOTAL_RECORDS_LOG.getLabel(), result.getLibraryBookRecordManager().getBookRecordsCount()));

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addValidLibraryBookRecord(LibraryBookRecord libraryBookRecord) {
        if (libraryBookRecord.isValid()) {
            this.manager.addRecord(libraryBookRecord);
            FileLoadService.logger.info(BookRecordLabel.NEW_RECORD_LOG.getLabel() + libraryBookRecord);
        }
    }
}
