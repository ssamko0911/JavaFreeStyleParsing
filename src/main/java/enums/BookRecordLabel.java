package enums;

public enum BookRecordLabel {
    OBJECT_SEPARATOR ("---------"),
    NEW_RECORD_LOG("New book record has been added: " ),
    TOTAL_RECORDS_LOG("Total records has been added: %d");

    private final String label;

    BookRecordLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
