package enums;

public enum BookRecordLabel {
    OBJECT_SEPARATOR ("---------");

    private final String label;

    BookRecordLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
