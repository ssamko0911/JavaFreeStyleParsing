import app.FileChooserApp;
import com.formdev.flatlaf.FlatDarkLaf;
import enums.ErrorLabel;
import enums.LogLabel;
import util.AppLogger;

import javax.swing.*;

void main() {
    FlatDarkLaf.setup();

    AppLogger logger = AppLogger.getInstance();
    logger.info(LogLabel.APP_START.getLabel());

    // TODO: refactor to catch Ex
    try {
        SwingUtilities.invokeLater(() -> {
            new FileChooserApp().setVisible(true);
            logger.info(LogLabel.APP_DISPLAY.getLabel());
        });
    } catch (Exception e) {
        logger.severe(ErrorLabel.APP_ERROR.getLabel(), e);
    }
}
