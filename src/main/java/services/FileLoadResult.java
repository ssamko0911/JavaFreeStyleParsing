package services;

import managers.LibraryBookRecordManager;

public class FileLoadResult {
    private final String content;
    private final LibraryBookRecordManager libraryBookRecordManager;

    public FileLoadResult(String content, LibraryBookRecordManager libraryBookRecordManager) {
        this.content = content;
        this.libraryBookRecordManager = libraryBookRecordManager;
    }

    public String getContent() {
        return content;
    }

    public LibraryBookRecordManager getLibraryBookRecordManager() {
        return libraryBookRecordManager;
    }
}
