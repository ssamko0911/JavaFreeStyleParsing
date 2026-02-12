package managers.bookRecordManager;

import entities.libraryItems.LibraryBookRecord;

import java.util.Collection;

public interface LibraryBookRecordManageable {
    void addRecord(LibraryBookRecord libraryBookRecord);

    Collection<LibraryBookRecord> getBookRecords();

    int getBookRecordsCount();

    void clearRecords();

    boolean supportsLookupByKey();

    boolean supportsReportByGenre();

    default LibraryBookRecord findByKey(String key){
        return null;
    }
}
