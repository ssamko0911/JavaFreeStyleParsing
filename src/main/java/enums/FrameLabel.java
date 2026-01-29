package enums;

public enum FrameLabel {
    CHOOSE_FILE("Load file"),
    CLEAN_CONTENT("Removed content from textarea"),
    APP_TITLE("FileChooser App");

    private final String label;

    FrameLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
