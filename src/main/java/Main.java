import com.formdev.flatlaf.FlatDarkLaf;
import enums.ErrorLabel;
import enums.LogLabel;
import util.AppLogger;

import javax.swing.*;

void main() {
    FlatDarkLaf.setup();

    AppLogger logger = AppLogger.getInstance();
    logger.info(LogLabel.APP_START.getLabel());

    try {
        SwingUtilities.invokeLater(() -> {
            new FileChooserApp().setVisible(true);
            logger.info(LogLabel.APP_DISPLAY.getLabel());
        });
    } catch (Exception e) {
        System.err.println(ErrorLabel.APP_ERROR.getLabel());
        e.printStackTrace();
    }
}
