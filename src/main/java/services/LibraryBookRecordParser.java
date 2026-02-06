package services;

import entities.LibraryBookRecord;

public class LibraryBookRecordParser {
    public final static String OCLC = "OCLC Number:";
    public final static String TITLE = "Title:";
    public final static String AUTHOR_PLURAL = "Authors:";
    public final static String AUTHOR_SINGULAR = "Author:";
    public final static String SUMMARY = "Summary:";
    public final static String PUBLICATION_YEAR = "Year of publication:";
    public final static String PUBLISHER = "Publisher:";
    public final static String PHYSICAL_DESCRIPTION = "Physical Description:";
    public final static String GENRE = "Genre:";
    public final static String ISBN = "ISBN:";
    private final AuthorParser authorParser;

    public LibraryBookRecordParser(AuthorParser authorParser) {
        this.authorParser = authorParser;
    }

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
                libraryBookRecord.setAuthors(this.authorParser.parseAuthor(value));
                break;
            case LibraryBookRecordParser.SUMMARY:
                libraryBookRecord.setSummary(value);
                break;
            case LibraryBookRecordParser.PUBLICATION_YEAR:
                libraryBookRecord.setPublicationYear(Integer.parseInt(value));
                break;
            case LibraryBookRecordParser.PUBLISHER:
                libraryBookRecord.setPublisher(value);
                break;
            case LibraryBookRecordParser.PHYSICAL_DESCRIPTION:
                libraryBookRecord.setPhysicalDescription(value);
                break;
            case LibraryBookRecordParser.GENRE:
                libraryBookRecord.setGenre(value);
                break;
            case LibraryBookRecordParser.ISBN:
                libraryBookRecord.setIsbn(value);
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
                trimmedLine.equals(LibraryBookRecordParser.PUBLISHER) ||
                trimmedLine.equals(LibraryBookRecordParser.PHYSICAL_DESCRIPTION) ||
                trimmedLine.equals(LibraryBookRecordParser.GENRE) ||
                trimmedLine.equals(LibraryBookRecordParser.ISBN);
    }
}
