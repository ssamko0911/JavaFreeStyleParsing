package managers.libraryRecordManager.impl;

import entities.libraryItems.LibraryItem;
import managers.libraryRecordManager.LibraryItemManageable;

import java.util.*;

public class LibraryItemManagerMapBased implements LibraryItemManageable {
    private final Map<String, LibraryItem> records = new HashMap<>();

    public void addRecord(LibraryItem libraryItem) {
        records.put(libraryItem.getOclcNumber(), libraryItem);
    }

    public Collection<LibraryItem> getRecords() {
        return records.values();
    }

    public int getRecordsCount() {
        return records.size();
    }

    public void clearRecords() {
        records.clear();
    }

    public boolean supportsLookupByKey() {
        return true;
    }

    @Override
    public boolean supportsReportByGenre() {
        return true;
    }

    public LibraryItem findByKey(String key) {
        return this.records.get(key);
    }

    @Override
    public boolean hasRecord(LibraryItem libraryItem) {
        return this.records.containsKey(libraryItem.getOclcNumber());
    }
}
