package enums;

public enum BookRecordFormat {
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

    BookRecordFormat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
