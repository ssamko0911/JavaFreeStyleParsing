package services;

import entities.LibraryBookRecord;


public class LibraryBookRecordParser {
    public final static String OCLC = "OCLC Number:";
    public final static String TITLE = "Title:";

    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field.trim()) {
            case LibraryBookRecordParser.OCLC:
                libraryBookRecord.setOclcNumber(value);
                break;
            case LibraryBookRecordParser.TITLE:
                libraryBookRecord.setTitle(value);
                break;
        }
    }

    public boolean isFieldLabel(String line) {
        String trimmedLine = line.trim();

        return trimmedLine.equals("OCLC Number:") || trimmedLine.equals("Title:");
    }
}
