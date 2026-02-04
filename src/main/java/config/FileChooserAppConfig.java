package config;

import enums.AppConfig;
import enums.ErrorLabel;
//import util.AppLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileChooserAppConfig {
    private static final Properties props = new Properties();
    // private static final AppLogger logger = AppLogger.getInstance();

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

    public static String getString(AppConfig config) {
        return props.getProperty(config.getKey());
    }

    public static String getProperty(AppConfig config) {
        return props.getProperty(config.getKey(), config.getDefaultValue());
    }

    public static int getInt(AppConfig config) {
        String value = props.getProperty(config.getKey());

        try {
            return value != null ? Integer.parseInt(value) : config.getDefaultInt();
        } catch (NumberFormatException e) {
            // TODO: bug, circular dependency; hardcode log_file path;
            //logger.warn(String.format(ErrorLabel.CONFIG_PARSE_WARNING.getLabel(), value, defaultValue));

            return config.getDefaultInt();
        }
    }

    public static boolean getBooleanProperty(AppConfig config) {
        String value = props.getProperty(config.getKey());

        return value != null ? Boolean.parseBoolean(value) : config.getDefaultBoolean();
    }
}
