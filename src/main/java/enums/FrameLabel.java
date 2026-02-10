package enums;

public enum FrameLabel {
    CHOOSE_FILE("Load File"),
    CLEAN_CONTENT("Remove Data"),
    REPORT("Validation Report"),
    SEARCH("Search"),
    APP_TITLE("Library Items Dashboard"),
    READY("Ready"),
    LOADING("Loading "),
    LOADED("Loaded: %s (%d bytes) | %d words | %d chars | %d book%s"),
    TABLE_COL_TITLE("Title"),
    TABLE_COL_AUTHOR("Authors"),
    TABLE_COL_YEAR("Publication Year"),
    TABLE_COL_GENRE("Genre"),
    TAB_DETAILS("Details"),
    TAB_RAW_TEXT("Raw Text"),
    NO_SELECTION("None"),
    SHOW_ALL("Show All"),
    DIALOG_NO_RECORDS("No records to validate."),
    DIALOG_NO_VALIDATION("No validation"),
    DIALOG_ALL_VALID("All records are valid!"),
    DIALOG_VALIDATION_REPORT("Validation Report"),
    DIALOG_VALIDATION_SUMMARY("Found %d errors, %d warnings in %d records.\n\n"),
    DIALOG_NO_BOOKS_FOUND("No books found."),
    DIALOG_SEARCH_RESULT("Search Result"),
    DIALOG_SEARCH("Search"),
    DIALOG_FIELD("Field:"),
    DIALOG_SEARCH_QUERY("Search:"),
    DIALOG_CASE_SENSITIVE("Case-Sensitive");

    private final String label;

    FrameLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
