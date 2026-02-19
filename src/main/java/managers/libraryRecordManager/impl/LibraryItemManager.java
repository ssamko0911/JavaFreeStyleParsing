package managers.libraryRecordManager.impl;

import entities.libraryItems.LibraryItem;
import managers.libraryRecordManager.LibraryItemManageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LibraryItemManager implements LibraryItemManageable {
    private final List<LibraryItem> records = new ArrayList<>();

    public void addRecord(LibraryItem libraryItem) {
        records.add(libraryItem);
    }

    public Collection<LibraryItem> getRecords() {
        return records;
    }

    public int getRecordsCount() {
        return records.size();
    }

    public void clearRecords() {
        records.clear();
    }

    public boolean supportsLookupByKey() {
        return false;
    }

    public boolean supportsReportByGenre() {
        return false;
    }

    public boolean hasRecord(LibraryItem libraryItem) {
        return this.records.contains(libraryItem);
    }
}
