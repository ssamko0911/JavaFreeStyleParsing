package enums;

public enum FrameLabel {
    CHOOSE_FILE("Load File"),
    CLEAN_CONTENT("Remove Data"),
    REPORT("Validation Report"),
    SEARCH("Search"),
    APP_TITLE("LibraryBookService App"),
    READY("Ready"),
    LOADING("Loading "),
    LOADED("Loaded: %s (%d bytes) | %d words | %d chars | %d book%s");

    private final String label;

    FrameLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
