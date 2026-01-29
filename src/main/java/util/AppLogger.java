package util;

import config.FileChooserAppConfig;
import enums.ErrorLabel;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static Logger logger;
    private static AppLogger instance;

    private AppLogger() {
        this.initLogger();
    }

    private void initLogger() {
        AppLogger.logger = Logger.getLogger(AppLogger.class.getName());
        AppLogger.logger.setLevel(Level.INFO);
        AppLogger.logger.setUseParentHandlers(false);

        try {
            FileHandler fileHandler = new FileHandler(FileChooserAppConfig.getString("app.logFile"), true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            AppLogger.logger.addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            System.err.println(ErrorLabel.APP_ERROR.getLabel() + e.getMessage());
            e.printStackTrace();
        }
    }

    public static AppLogger getInstance() {
        if (AppLogger.instance == null) {
            synchronized (AppLogger.class) {
                if (AppLogger.instance == null) {
                    AppLogger.instance = new AppLogger();
                }
            }
        }

        return AppLogger.instance;
    }

    public void info(String message) {
        AppLogger.logger.log(Level.INFO, message);
    }

    public void warn(String message) {
        AppLogger.logger.log(Level.WARNING, message);
    }

    public void severe(String message) {
        AppLogger.logger.log(Level.SEVERE, message);
    }

    public void severe(String message, Throwable throwable) {
        AppLogger.logger.log(Level.SEVERE, message, throwable);
    }

    public void log(Level level, String message) {
        AppLogger.logger.log(level, message);
    }

    public void log(Level level, String message, Throwable throwable) {
        AppLogger.logger.log(level, message, throwable);
    }

    public Logger getAppLogger() {
        return AppLogger.logger;
    }
}
