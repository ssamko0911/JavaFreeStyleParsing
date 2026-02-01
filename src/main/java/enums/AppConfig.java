package enums;

public enum AppConfig {
    APP_WIDTH("app.width", "500"),
    APP_HEIGHT("app.height", "600"),
    APP_TEXT_AREA_EDITABLE("app.text_area_editable", "false"),
    APP_TEXT_AREA_ROWS("app.textAreaRows", "30"),
    APP_TEXT_AREA_COLS("app.textAreaCols", "30"),
    APP_LOG_FILE("app.logFile", "src/log/appLog.log");

    private final String key;
    private final String defaultValue;

    AppConfig(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return this.key;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public int getDefaultInt() {
        return Integer.parseInt(getDefaultValue());
    }

    public boolean getDefaultBoolean() {
        return Boolean.parseBoolean(getDefaultValue());
    }
}
