package services;

import entities.LibraryBookRecord;


public class LibraryBookRecordParser {
    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field.trim()) {
            case "OCLC Number:":
                libraryBookRecord.setOclcNumber(value);
                break;
            case "Title:":
                libraryBookRecord.setTitle(value);
                break;
        }
    }

    public boolean isFieldLabel(String line) {
        String trimmedLine = line.trim();

        return trimmedLine.equals("OCLC Number:") || trimmedLine.equals("Title:");
    }
}
