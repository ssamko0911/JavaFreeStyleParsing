package enums;

public enum RecordType {
    DVD("DVD"),
    BOOK("BOOK");

    private final String label;

    RecordType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
