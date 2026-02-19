package services.parsers;

import entities.libraryItems.LibraryBookRecord;
import services.LibraryItemParser;

import java.util.Set;

public class LibraryBookRecordParser implements LibraryItemParser<LibraryBookRecord> {
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
    private final TitleParser titleParser;
    private final IsbnParser isbnParser;

    @Override
    public LibraryBookRecord createRecord() {
        return new LibraryBookRecord();
    }

    //TODO: Enhance PhysicalDescription parser;
    public LibraryBookRecordParser(
            AuthorParser authorParser,
            PhysicalDescriptionParser physicalDescriptionParser,
            PublisherParser publisherParser,
            PublicationYearParser publicationYearParser,
            TitleParser titleParser,
            IsbnParser isbnParser
    ) {
        this.authorParser = authorParser;
        this.physicalDescriptionParser = physicalDescriptionParser;
        this.publisherParser = publisherParser;
        this.publicationYearParser = publicationYearParser;
        this.titleParser = titleParser;
        this.isbnParser = isbnParser;
    }

    public void setField(LibraryBookRecord libraryBookRecord, String field, String value) {
        switch (field.trim()) {
            case LibraryBookRecordParser.OCLC -> libraryBookRecord.setOclcNumber(value);
            case LibraryBookRecordParser.TITLE -> libraryBookRecord.setTitle(this.titleParser.parseTitle(value));
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
            case LibraryBookRecordParser.ISBN -> libraryBookRecord.setIsbn(this.isbnParser.parseIsbn(value));
        }
    }

    public boolean isFieldLabel(String line) {
        return FIELD_LABELS.contains(line.trim());
    }
}
