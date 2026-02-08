package services;

import entities.LibraryBookRecord;
import enums.ErrorLabel;

import java.util.Set;

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

    private static final Set<String> FIELD_LABELS = Set.of(
            LibraryBookRecordParser.OCLC,
            LibraryBookRecordParser.TITLE,
            LibraryBookRecordParser.AUTHOR_PLURAL,
            LibraryBookRecordParser.AUTHOR_SINGULAR,
            LibraryBookRecordParser.SUMMARY,
            LibraryBookRecordParser.PUBLICATION_YEAR,
            LibraryBookRecordParser.PUBLISHER,
            LibraryBookRecordParser.PHYSICAL_DESCRIPTION,
            LibraryBookRecordParser.GENRE,
            LibraryBookRecordParser.ISBN
    );

    private final AuthorParser authorParser;
    private final PhysicalDescriptionParser physicalDescriptionParser;
    private final PublisherParser publisherParser;

    public LibraryBookRecordParser(
            AuthorParser authorParser,
            PhysicalDescriptionParser physicalDescriptionParser,
            PublisherParser publisherParser
    ) {
        this.authorParser = authorParser;
        this.physicalDescriptionParser = physicalDescriptionParser;
        this.publisherParser = publisherParser;
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
                libraryBookRecord.setPublisher(this.publisherParser.parsePublisher(value));
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
        return FIELD_LABELS.contains(line.trim());
    }
}
