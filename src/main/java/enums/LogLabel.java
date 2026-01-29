package enums;

public enum LogLabel {
    SELECT_FILE("Selected file: "),
    LOAD_FILE("Loading file: ");

    private final String label;

    LogLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
