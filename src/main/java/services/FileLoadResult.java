package services;

import managers.bookRecordManager.LibraryBookRecordManageable;
import managers.bookRecordManager.impl.LibraryBookRecordManager;

public class FileLoadResult {
    private final String content;
    private final LibraryBookRecordManageable libraryBookRecordManager;

    public FileLoadResult(String content, LibraryBookRecordManageable libraryBookRecordManager) {
        this.content = content;
        this.libraryBookRecordManager = libraryBookRecordManager;
    }

    public String getContent() {
        return content;
    }

    public LibraryBookRecordManageable getLibraryBookRecordManager() {
        return libraryBookRecordManager;
    }
}
