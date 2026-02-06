package enums;

public enum BookRecordLabel {
    OBJECT_SEPARATOR("---------"),
    NEW_RECORD_LOG("New book record has been added: "),
    TOTAL_RECORDS_LOG("Total records has been added: %d"),
    TO_STRING_SHORT_TEST("LibraryBookRecord:\n1) OCLC Number: %s;\n2) ISBN: %s;\n"),
    TO_STRING("""
                 LibraryBookRecord {
                                  oclcNumber: '%s'
                                  title: '%s'
                                  author: '%s'
                                  summary: '%s'
                                  publicationYear: %d
                                  publisher: '%s'
                                  genre: '%s'
                                  physicalDescription: '%s'
                                  isbn: '%s'
                }""");

    private final String label;

    BookRecordLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
