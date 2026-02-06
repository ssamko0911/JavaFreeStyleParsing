package services;

import entities.LibraryBookRecord;
import enums.BookRecordLabel;
import managers.LibraryBookRecordManager;
import util.AppLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileLoadService {
    private LibraryBookRecordManager manager;
    private LibraryBookRecordParser parser;

    private static final AppLogger logger = AppLogger.getInstance();

    public FileLoadService(LibraryBookRecordManager manager, LibraryBookRecordParser parser) {
        this.manager = manager;
        this.parser = parser;
    }

    public FileLoadResult loadFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        List<LibraryBookRecord> records = this.parseRecords(file, content);

        records.forEach(this::addValidLibraryBookRecord);
        FileLoadService.logger.info(String.format(BookRecordLabel.TOTAL_RECORDS_LOG.getLabel(), this.manager.getBookRecordsCount()));

        return new FileLoadResult(content.toString(), this.manager);
    }

    private List<LibraryBookRecord> parseRecords(File file, StringBuilder content) throws IOException {
        List<LibraryBookRecord> records = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            LibraryBookRecord currentRecord = new LibraryBookRecord();
            String line;

            while ((line = fileReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());

                if (line.startsWith(BookRecordLabel.OBJECT_SEPARATOR.getLabel())) {
                    if (currentRecord.isValid()) {
                        records.add(currentRecord);
                    }

                    currentRecord = new LibraryBookRecord();
                } else if (this.parser.isFieldLabel(line.trim())) {
                    String value = fileReader.readLine();

                    if (value != null) {
                        try {
                            this.parser.setField(currentRecord, line, value);
                        } catch (IllegalArgumentException e) {
                            FileLoadService.logger.warn(e.getMessage());
                        }
                        content.append(value).append(System.lineSeparator());
                    }
                }
            }

            if (currentRecord.isValid()) {
                records.add(currentRecord);
            }

            return records;
        }
    }

    private void addValidLibraryBookRecord(LibraryBookRecord libraryBookRecord) {
        this.manager.addRecord(libraryBookRecord);
        FileLoadService.logger.info(BookRecordLabel.NEW_RECORD_LOG.getLabel() + libraryBookRecord);
    }
}
