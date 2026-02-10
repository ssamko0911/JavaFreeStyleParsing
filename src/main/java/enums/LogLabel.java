package enums;

public enum LogLabel {
    SELECT_FILE("Selected file: %s"),
    LOAD_FILE("Loading file: %s"),
    LOADED_FILE("Loaded file: %s"),
    CLEAN_CONTENT("Removed content"),
    APP_START("Application Started"),
    APP_DISPLAY("Application window displayed"),
    APP_INIT("App initialized"),
    YEAR_LOG("year"),
    PARSED_PUBLISHER("Parsed publisher: %s"),
    NEW_RECORD_LOG("New book record has been added: "),
    NEW_AUTHOR_LOG("New Author object has been added:\n%s"),
    PHYSICAL_DESCRIPTION_PARSED("Physical Description record parsed:\n%s"),
    TOTAL_RECORDS_LOG("Total records has been added: %d");

    private final String label;

    LogLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
