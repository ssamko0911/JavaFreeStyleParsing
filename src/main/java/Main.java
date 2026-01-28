import com.formdev.flatlaf.FlatDarkLaf;
import enums.ErrorLabels;

import javax.swing.*;

void main() {
    FlatDarkLaf.setup();

    try {
        SwingUtilities.invokeLater(() -> {
            new FileChooserApp().setVisible(true);
        });
    } catch (Exception e) {
        System.err.println(ErrorLabels.APP_ERROR.getLabel());
        // add logging
        e.printStackTrace();
    }
}
