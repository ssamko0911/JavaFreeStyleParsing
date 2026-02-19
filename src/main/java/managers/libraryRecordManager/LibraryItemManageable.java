package managers.libraryRecordManager;

import entities.libraryItems.LibraryItem;

import java.util.Collection;

public interface LibraryItemManageable {
    void addRecord(LibraryItem libraryItem);

    Collection<LibraryItem> getRecords();

    int getRecordsCount();

    void clearRecords();

    boolean supportsLookupByKey();

    boolean supportsReportByGenre();

    default LibraryItem findByKey(String key) {
        return null;
    }

    boolean hasRecord(LibraryItem libraryItem);
}
