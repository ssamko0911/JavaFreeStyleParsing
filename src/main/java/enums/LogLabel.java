package enums;

public enum LogLabel {
    SELECT_FILE("Selected file: "),
    LOAD_FILE("Loading file: "),
    CLEAN_CONTENT("Removed content"),
    APP_START("Application Started"),
    APP_DISPLAY("Application window displayed"),
    APP_INIT("App initialized");

    private final String label;

    LogLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
