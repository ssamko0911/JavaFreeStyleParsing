package managers.bookRecordManager.impl;

import entities.libraryItems.LibraryBookRecord;
import managers.bookRecordManager.LibraryBookRecordManageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LibraryBookRecordManager implements LibraryBookRecordManageable {
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
}
