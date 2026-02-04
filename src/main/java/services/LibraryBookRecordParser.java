package services;

import entities.LibraryBookRecord;


public class LibraryBookRecordParser {
    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field) {
            case "OCLC Number:":
                libraryBookRecord.setOclcNumber(value);
            case "Title:":
                libraryBookRecord.setTitle(value);
        }
    }

    public boolean isFieldLabel(String line) {
        return switch (line) {
            case "OCLC Number:", "Title:" -> true;
            default -> false;
        };
    }
}
