package enums;

public enum AppConfig {
    APP_WIDTH("app.width", "950"),
    APP_HEIGHT("app.height", "680"),
    APP_TEXT_AREA_EDITABLE("app.text_area_editable", "false"),
    APP_TEXT_AREA_ROWS("app.textAreaRows", "30"),
    APP_TEXT_AREA_COLS("app.textAreaCols", "30"),
    APP_LOG_FILE("app.logFile", "logs/appLog.log"),
    APP_TABLE_ROW("app.tableRowHeight", "24"),
    APP_SPLIT_DIVIDER("app.splitDividerLocation", "450"),
    CONFIG_FILE("application.properties", "application.properties");

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
