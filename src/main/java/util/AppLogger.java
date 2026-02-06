package util;

import enums.ErrorLabel;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class AppLogger {
    private static String logfile;
    private static volatile Logger logger;
    private static volatile AppLogger instance;

    private AppLogger(String logFile) {
        AppLogger.logfile = logFile;
        this.initLogger();
    }

    public static AppLogger init(String logFile) {
        if (AppLogger.instance == null) {
            synchronized (AppLogger.class) {
                if (AppLogger.instance == null) {
                    AppLogger.instance = new AppLogger(logFile);
                }
            }
        }

        return AppLogger.instance;
    }

    private void initLogger() {
        AppLogger.logger = Logger.getLogger(AppLogger.class.getName());
        AppLogger.logger.setLevel(Level.INFO);
        AppLogger.logger.setUseParentHandlers(false);

        try {
            File logFile = new File(AppLogger.logfile);
            File parentDir = logFile.getParentFile();

            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException(ErrorLabel.ERROR_CREATE_LOG_DIR.getLabel() + parentDir);
            }

            FileHandler fileHandler = new FileHandler(logFile.getPath(), true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            AppLogger.logger.addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            AppLogger.logger.addHandler(consoleHandler);
            AppLogger.logger.severe(ErrorLabel.FILE_HANDLER_ERROR.getLabel() + e.getMessage());
        }
    }

    public static AppLogger getInstance() {
        if (AppLogger.instance == null) {
            throw new IllegalArgumentException(ErrorLabel.ERROR_LOGGER_INIT.getLabel());
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
