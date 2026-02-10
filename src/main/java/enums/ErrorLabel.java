package enums;

public enum ErrorLabel {
    ERROR("Error"),
    APP_ERROR("An error occurred. Try again later."),
    FILE_NOT_FOUND("File Not Found"),
    CONFIG_FILE_ERROR("Unable to find application.properties"),
    CONFIG_ERROR("Failed to load configuration"),
    FILE_HANDLER_ERROR("Cannot create file handler: "),
    CONFIG_PARSE_WARNING("Invalid config value for key %s. Using default: %s."),
    ERROR_CREATE_LOG_DIR("Failed to create log directory: "),
    ERROR_LOGGER_INIT("AppLogger not initialized. Call init(logFile) first."),
    PUBLICATION_YEAR_INVALID_VALUE("Invalid value for %s: %s"),
    IO_ERROR("Error reading file"),
    PARSE_ERROR("Error parsing file content"),
    PARSE_ERROR_YEAR("Cannot parse the publisher: ");

    private final String label;

    ErrorLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
