package managers.bookRecordManager;

import entities.libraryItems.LibraryBookRecord;
import entities.libraryItems.LibraryItem;

import java.util.Collection;

public interface LibraryItemManageable {
    void addRecord(LibraryBookRecord libraryBookRecord);

    Collection<LibraryBookRecord> getBookRecords();

    int getBookRecordsCount();

    void clearRecords();

    boolean supportsLookupByKey();

    boolean supportsReportByGenre();

    default LibraryBookRecord findByKey(String key){
        return null;
    }

    boolean hasLibraryRecord(LibraryBookRecord libraryItem);
}
