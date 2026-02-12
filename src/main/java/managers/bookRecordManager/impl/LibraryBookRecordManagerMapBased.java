package managers.bookRecordManager.impl;

import entities.libraryItems.LibraryBookRecord;
import managers.bookRecordManager.LibraryBookRecordManageable;

import java.util.*;

public class LibraryBookRecordManagerMapBased implements LibraryBookRecordManageable {
    private final Map<String, LibraryBookRecord> bookRecords = new HashMap<>();

    public void addRecord(LibraryBookRecord libraryBookRecord) {
        bookRecords.put(libraryBookRecord.getOclcNumber(), libraryBookRecord);
    }

    public Collection<LibraryBookRecord> getBookRecords() {
        return bookRecords.values();
    }

    public int getBookRecordsCount() {
        return bookRecords.size();
    }

    public void clearRecords() {
        bookRecords.clear();
    }

    public boolean supportsLookupByKey() {
        return true;
    }

    @Override
    public boolean supportsReportByGenre() {
        return true;
    }

    public LibraryBookRecord findByKey(String key) {
        return this.bookRecords.get(key);
    }
}
