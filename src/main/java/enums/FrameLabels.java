package enums;

public enum FrameLabels {
    CHOOSE_FILE("Load file"),
    APP_TITLE("FileChooser App");

    private final String label;

    FrameLabels(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
