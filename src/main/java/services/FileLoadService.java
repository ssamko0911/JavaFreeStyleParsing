package services;

import entities.libraryItems.LibraryItem;
import enums.LogLabel;
import managers.bookRecordManager.LibraryItemManageable;
import util.AppLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileLoadService<T extends LibraryItem> {
    private static final String OBJECT_SEPARATOR = "---------";
    private final LibraryItemManageable manager;
    private final LibraryItemParser<T> parser;

    private static final AppLogger logger = AppLogger.getInstance();

    public FileLoadService(LibraryItemManageable manager, LibraryItemParser<T> parser) {
        this.manager = manager;
        this.parser = parser;
    }

    public LibraryItemManageable getManager() {
        return manager;
    }

    public FileLoadResult loadFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        List<T> records = this.parseRecords(file, content);

        records.forEach(this::addValidRecord);
        FileLoadService.logger.info(String.format(LogLabel.TOTAL_RECORDS_LOG.getLabel(), this.manager.getRecordsCount()));

        return new FileLoadResult(content.toString(), this.manager);
    }

    private List<T> parseRecords(File file, StringBuilder content) throws IOException {
        List<T> records = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            T currentRecord = parser.createRecord();
            String line;

            while ((line = fileReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());

                if (line.startsWith(OBJECT_SEPARATOR)) {
                    if (currentRecord.isValid()) {
                        records.add(currentRecord);
                    }

                    currentRecord = parser.createRecord();
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

    private void addValidRecord(T record) {
        this.manager.addRecord(record);
        FileLoadService.logger.info(LogLabel.NEW_RECORD_LOG.getLabel() + record);
    }
}
