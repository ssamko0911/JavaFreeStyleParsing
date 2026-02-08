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
    private final PublicationYearParser publicationYearParser;

    public LibraryBookRecordParser(
            AuthorParser authorParser,
            PhysicalDescriptionParser physicalDescriptionParser,
            PublisherParser publisherParser,
            PublicationYearParser publicationYearParser
    ) {
        this.authorParser = authorParser;
        this.physicalDescriptionParser = physicalDescriptionParser;
        this.publisherParser = publisherParser;
        this.publicationYearParser = publicationYearParser;
    }

    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field.trim()) {
            case LibraryBookRecordParser.OCLC -> libraryBookRecord.setOclcNumber(value);
            case LibraryBookRecordParser.TITLE -> libraryBookRecord.setTitle(value);
            case LibraryBookRecordParser.AUTHOR_PLURAL, LibraryBookRecordParser.AUTHOR_SINGULAR ->
                    libraryBookRecord.setAuthors(this.authorParser.parseAuthor(value));
            case LibraryBookRecordParser.SUMMARY -> libraryBookRecord.setSummary(value);
            case LibraryBookRecordParser.PUBLICATION_YEAR ->
                    libraryBookRecord.setPublicationYear(this.publicationYearParser.parsePublicationYear(value));
            case LibraryBookRecordParser.PUBLISHER ->
                    libraryBookRecord.setPublisher(this.publisherParser.parsePublisher(value));
            case LibraryBookRecordParser.PHYSICAL_DESCRIPTION ->
                    libraryBookRecord.setPhysicalDescription(this.physicalDescriptionParser.parsePhysicalDescription(value));
            case LibraryBookRecordParser.GENRE -> libraryBookRecord.setGenre(value);
            case LibraryBookRecordParser.ISBN -> libraryBookRecord.setIsbn(value);
        }
    }

    public boolean isFieldLabel(String line) {
        return FIELD_LABELS.contains(line.trim());
    }
}
