package enums;

public enum ErrorLabel {
    APP_ERROR("An error occurred. Try again later."),
    FILE_NOT_FOUND("File Not Found."),
    CONFIG_FILE("application.properties"),
    CONFIG_FILE_ERROR("Unable to find application.properties"),
    CONFIG_ERROR("Failed to load configuration");

    private final String label;

    ErrorLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
