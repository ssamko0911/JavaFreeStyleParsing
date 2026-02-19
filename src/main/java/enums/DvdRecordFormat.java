package enums;

public enum DvdRecordFormat {
    TO_STRING("""
             LibraryDVDRecord {
                              oclcNumber: '%s'
                              title: '%s'
                              cast: '%s'
                              credits: '%s'
                              plot: '%s'
                              releaseYear: %d
                              publisher: '%s'
                              language: '%s'
                              genre: '%s'
                              physicalDescription: '%s'
                              isbn: '%s'
            }""");

    private final String label;

    DvdRecordFormat(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
