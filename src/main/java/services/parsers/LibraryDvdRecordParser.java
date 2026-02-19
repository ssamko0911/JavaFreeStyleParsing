package services.parsers;

import entities.libraryItems.LibraryDvdRecord;
import enums.RecordType;
import services.LibraryItemParser;

import java.util.Set;

public class LibraryDvdRecordParser implements LibraryItemParser<LibraryDvdRecord> {
    private final static String OCLC = "OCLC Number:";
    private final static String TITLE = "Title:";
    private final static String CAST = "Cast:";
    private final static String CREDITS = "Credits:";
    private final static String PLOT = "Plot:";
    private final static String RELEASE_YEAR = "Year of release:";
    private final static String LANGUAGE = "Language:";
    private final static String PUBLISHER = "Publisher:";
    private final static String GENRE = "Genre:";
    private final static String PHYSICAL_DESCRIPTION = "Physical Description:";
    private final static String ISBN = "ISBN:";

    private static final Set<String> FIELD_LABELS = Set.of(
            LibraryDvdRecordParser.OCLC,
            LibraryDvdRecordParser.TITLE,
            LibraryDvdRecordParser.CAST,
            LibraryDvdRecordParser.CREDITS,
            LibraryDvdRecordParser.PLOT,
            LibraryDvdRecordParser.RELEASE_YEAR,
            LibraryDvdRecordParser.LANGUAGE,
            LibraryDvdRecordParser.PUBLISHER,
            LibraryDvdRecordParser.GENRE,
            LibraryDvdRecordParser.PHYSICAL_DESCRIPTION,
            LibraryDvdRecordParser.ISBN
    );

    private final TitleParser titleParser;
    private final IsbnParser isbnParser;

    public LibraryDvdRecordParser(TitleParser titleParser, IsbnParser isbnParser) {
        this.titleParser = titleParser;
        this.isbnParser = isbnParser;
    }

    @Override
    public LibraryDvdRecord createRecord() {
        return new LibraryDvdRecord(RecordType.DVD);
    }

    @Override
    public boolean isFieldLabel(String line) {
        return FIELD_LABELS.contains(line.trim());
    }

    @Override
    public void setField(LibraryDvdRecord record, String field, String value) {
        switch (field.trim()) {
            case LibraryDvdRecordParser.OCLC -> record.setOclcNumber(value);
            case LibraryDvdRecordParser.TITLE -> record.setTitle(this.titleParser.parseTitle(value));
            case LibraryDvdRecordParser.CAST -> record.setCast(value);
            case LibraryDvdRecordParser.CREDITS -> record.setCredits(value);
            case LibraryDvdRecordParser.PLOT -> record.setPlot(value);
            case LibraryDvdRecordParser.RELEASE_YEAR -> record.setReleaseYear(Integer.parseInt(value));
            case LibraryDvdRecordParser.LANGUAGE -> record.setLanguage(value);
            case LibraryDvdRecordParser.PUBLISHER -> record.setPublisher(value);
            case LibraryDvdRecordParser.GENRE -> record.setGenre(value);
            case LibraryDvdRecordParser.PHYSICAL_DESCRIPTION -> record.setPhysicalDescription(value);
            case LibraryDvdRecordParser.ISBN -> record.setIsbn(this.isbnParser.parseIsbn(value));
        }
    }
}
