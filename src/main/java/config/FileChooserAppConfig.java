package config;

import enums.ErrorLabel;
import util.AppLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileChooserAppConfig {
    private static final Properties props = new Properties();
    private static final AppLogger logger = AppLogger.getInstance();

    static {
        try (InputStream input = FileChooserAppConfig.class.getClassLoader().getResourceAsStream(ErrorLabel.CONFIG_FILE.getLabel())) {
            if (input == null) {
                throw new RuntimeException(ErrorLabel.CONFIG_FILE_ERROR.getLabel());
            }

            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(ErrorLabel.CONFIG_ERROR.getLabel(), e);
        }
    }

    public static String getString(String key) {
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        String value = props.getProperty(key);

        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            logger.warn(String.format(ErrorLabel.CONFIG_PARSE_WARNING.getLabel(), value, defaultValue));

            return defaultValue;
        }
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = props.getProperty(key);

        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
}
