package services;

import entities.LibraryBookRecord;


public class LibraryBookRecordParser {
    public final static String OCLC = "OCLC Number:";
    public final static String TITLE = "Title:";
    public final static String AUTHOR_PLURAL = "Authors:";
    public final static String AUTHOR_SINGULAR = "Author:";
    public final static String SUMMARY = "Summary:";
    public final static String PUBLICATION_YEAR = "Year of publication:";
    public final static String GENRE = "Genre:";

    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field.trim()) {
            case LibraryBookRecordParser.OCLC:
                libraryBookRecord.setOclcNumber(value);
                break;
            case LibraryBookRecordParser.TITLE:
                libraryBookRecord.setTitle(value);
                break;
            case LibraryBookRecordParser.AUTHOR_PLURAL:
            case LibraryBookRecordParser.AUTHOR_SINGULAR:
                libraryBookRecord.setAuthor(value);
                break;
            case LibraryBookRecordParser.SUMMARY:
                libraryBookRecord.setSummary(value);
            case LibraryBookRecordParser.PUBLICATION_YEAR:
                libraryBookRecord.setPublicationYear(value);
            case LibraryBookRecordParser.GENRE:
                libraryBookRecord.setGenre(value);
                break;
        }
    }

    public boolean isFieldLabel(String line) {
        String trimmedLine = line.trim();

        return trimmedLine.equals(LibraryBookRecordParser.OCLC) ||
                trimmedLine.equals(LibraryBookRecordParser.TITLE) ||
                trimmedLine.equals(LibraryBookRecordParser.AUTHOR_PLURAL) ||
                trimmedLine.equals(LibraryBookRecordParser.AUTHOR_SINGULAR) ||
                trimmedLine.equals(LibraryBookRecordParser.SUMMARY) ||
                trimmedLine.equals(LibraryBookRecordParser.PUBLICATION_YEAR) ||
                trimmedLine.equals(LibraryBookRecordParser.GENRE);
    }
}
