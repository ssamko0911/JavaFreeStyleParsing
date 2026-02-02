import app.FileChooserApp;
import com.formdev.flatlaf.FlatDarkLaf;
import config.FileChooserAppConfig;
import enums.AppConfig;
import enums.ErrorLabel;
import enums.LogLabel;
import util.AppLogger;

import javax.swing.*;

void main() {
    FlatDarkLaf.setup();

    String logFile = FileChooserAppConfig.getString(AppConfig.APP_LOG_FILE);

    AppLogger logger = AppLogger.init(logFile);
    logger.info(LogLabel.APP_START.getLabel());

    try {
        SwingUtilities.invokeLater(() -> {
            new FileChooserApp().setVisible(true);
            logger.info(LogLabel.APP_DISPLAY.getLabel());
        });
    } catch (Exception e) {
        logger.severe(ErrorLabel.APP_ERROR.getLabel(), e);
    }
}
