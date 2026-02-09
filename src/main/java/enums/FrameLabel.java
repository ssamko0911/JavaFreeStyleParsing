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
    SHOW_ALL("Show All");

    private final String label;

    FrameLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
