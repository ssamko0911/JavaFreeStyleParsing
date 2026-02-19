package managers.bookRecordManager.impl;

import entities.libraryItems.LibraryBookRecord;
import entities.libraryItems.LibraryItem;
import managers.bookRecordManager.LibraryItemManageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LibraryItemManager implements LibraryItemManageable {
    private final List<LibraryBookRecord> bookRecords = new ArrayList<>();

    public void addRecord(LibraryBookRecord libraryBookRecord) {
        bookRecords.add(libraryBookRecord);
    }

    public Collection<LibraryBookRecord> getBookRecords() {
        return bookRecords;
    }

    public int getBookRecordsCount() {
        return bookRecords.size();
    }

    public void clearRecords() {
        bookRecords.clear();
    }

    public boolean supportsLookupByKey() {
        return false;
    }

    public boolean supportsReportByGenre() {
        return false;
    }

    public boolean hasLibraryRecord(LibraryBookRecord libraryItem) {
        return this.bookRecords.contains(libraryItem);
    }
}
