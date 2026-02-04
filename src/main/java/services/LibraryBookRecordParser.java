package services;

import entities.LibraryBookRecord;


public class LibraryBookRecordParser {
    public final static String OCLC = "OCLC Number:";
    public final static String TITLE = "Title:";
    public final static String GENRE = "Genre:";

    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field.trim()) {
            case LibraryBookRecordParser.OCLC:
                libraryBookRecord.setOclcNumber(value);
                break;
            case LibraryBookRecordParser.TITLE:
                libraryBookRecord.setTitle(value);
                break;
            case LibraryBookRecordParser.GENRE:
                libraryBookRecord.setGenre(value);
                break;
        }
    }

    public boolean isFieldLabel(String line) {
        String trimmedLine = line.trim();

        return trimmedLine.equals(LibraryBookRecordParser.OCLC) ||
                trimmedLine.equals(LibraryBookRecordParser.TITLE) ||
                trimmedLine.equals(LibraryBookRecordParser.GENRE);
    }
}
