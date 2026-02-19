package services;

import managers.libraryRecordManager.LibraryItemManageable;

public class FileLoadResult {
    private final String content;
    private final LibraryItemManageable libraryBookRecordManager;

    public FileLoadResult(String content, LibraryItemManageable libraryBookRecordManager) {
        this.content = content;
        this.libraryBookRecordManager = libraryBookRecordManager;
    }

    public String getContent() {
        return content;
    }

    public LibraryItemManageable getLibraryBookRecordManager() {
        return libraryBookRecordManager;
    }
}
