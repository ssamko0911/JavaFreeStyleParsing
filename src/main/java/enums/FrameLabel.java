package enums;

public enum FrameLabel {
    CHOOSE_FILE("Load file"),
    CLEAN_CONTENT("Remove"),
    APP_TITLE("FileChooser App"),
    READY("Ready"),
    LOADING("Loading "),
    LOADED("Loaded: %s (%s  bytes)");

    private final String label;

    FrameLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
