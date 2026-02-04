package managers;

import entities.LibraryBookRecord;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookRecordManager {
    private final List<LibraryBookRecord> bookRecords =  new ArrayList<>();

    public void addRecord(LibraryBookRecord libraryBookRecord) {
        bookRecords.add(libraryBookRecord);
    }

    public List<LibraryBookRecord> getBookRecords() {
        return bookRecords;
    }

    public int getBookRecordsCount() {
        return bookRecords.size();
    }
}
