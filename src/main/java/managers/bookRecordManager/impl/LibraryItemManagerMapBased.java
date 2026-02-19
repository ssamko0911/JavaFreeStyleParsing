package managers.bookRecordManager.impl;

import entities.libraryItems.LibraryBookRecord;
import managers.bookRecordManager.LibraryItemManageable;

import java.util.*;

public class LibraryItemManagerMapBased implements LibraryItemManageable {
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

    @Override
    public boolean hasLibraryRecord(LibraryBookRecord libraryItem) {
        return this.bookRecords.containsValue(libraryItem);
    }
}
