package services;

import entities.LibraryBookRecord;
import enums.ErrorLabel;

public class LibraryBookRecordParser {
    private final static String OCLC = "OCLC Number:";
    private final static String TITLE = "Title:";
    private final static String AUTHOR_PLURAL = "Authors:";
    private final static String AUTHOR_SINGULAR = "Author:";
    private final static String SUMMARY = "Summary:";
    private final static String PUBLICATION_YEAR = "Year of publication:";
    private final static String PUBLISHER = "Publisher:";
    private final static String PHYSICAL_DESCRIPTION = "Physical Description:";
    private final static String GENRE = "Genre:";
    private final static String ISBN = "ISBN:";
    private final AuthorParser authorParser;
    private final PhysicalDescriptionParser physicalDescriptionParser;

    //TODO: add PublisherParser;
    public LibraryBookRecordParser(AuthorParser authorParser, PhysicalDescriptionParser physicalDescriptionParser) {
        this.authorParser = authorParser;
        this.physicalDescriptionParser = physicalDescriptionParser;
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
                try {
                    libraryBookRecord.setPublicationYear(Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            String.format(ErrorLabel.PUBLICATION_YEAR_INVALID_VALUE.getLabel(), LibraryBookRecordParser.PUBLICATION_YEAR, value)
                    );
                }
                break;
            case LibraryBookRecordParser.PUBLISHER:
                libraryBookRecord.setPublisher(value);
                break;
            case LibraryBookRecordParser.PHYSICAL_DESCRIPTION:
                libraryBookRecord.setPhysicalDescription(this.physicalDescriptionParser.parsePhysicalDescription(value));
                break;
            case LibraryBookRecordParser.GENRE:
                libraryBookRecord.setGenre(value);
                break;
            case LibraryBookRecordParser.ISBN:
                libraryBookRecord.setIsbn(value);
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
                trimmedLine.equals(LibraryBookRecordParser.PUBLISHER) ||
                trimmedLine.equals(LibraryBookRecordParser.PHYSICAL_DESCRIPTION) ||
                trimmedLine.equals(LibraryBookRecordParser.GENRE) ||
                trimmedLine.equals(LibraryBookRecordParser.ISBN);
    }
}
