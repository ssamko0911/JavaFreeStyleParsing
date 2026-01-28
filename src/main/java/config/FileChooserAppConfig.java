package config;

import enums.ErrorLabels;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileChooserAppConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = FileChooserAppConfig.class.getClassLoader().getResourceAsStream(ErrorLabels.CONFIG_FILE.getLabel())) {
            if (input == null) {
                throw new RuntimeException(ErrorLabels.CONFIG_FILE_ERROR.getLabel());
            }

            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(ErrorLabels.CONFIG_ERROR.getLabel());
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
            return defaultValue;
        }
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = props.getProperty(key);

        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
}
