import com.formdev.flatlaf.FlatDarkLaf;
import enums.ErrorLabel;

import javax.swing.*;

void main() {
    FlatDarkLaf.setup();

    try {
        SwingUtilities.invokeLater(() -> {
            new FileChooserApp().setVisible(true);
        });
    } catch (Exception e) {
        System.err.println(ErrorLabel.APP_ERROR.getLabel());
        // TODO:add global logging
        e.printStackTrace();
    }
}
