package managers;

import entities.LibraryBookRecord;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookRecordManager {
    //TODO: clean book records on reload;
    private final List<LibraryBookRecord> bookRecords = new ArrayList<>();

    public void addRecord(LibraryBookRecord libraryBookRecord) {
        bookRecords.add(libraryBookRecord);
    }

    public List<LibraryBookRecord> getBookRecords() {
        return bookRecords;
    }

    public int getBookRecordsCount() {
        return bookRecords.size();
    }

    public void clearRecords() {
        bookRecords.clear();
    }
}
