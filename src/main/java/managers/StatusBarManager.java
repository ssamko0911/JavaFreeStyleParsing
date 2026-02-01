package managers;

import enums.ErrorLabel;
import enums.FrameLabel;

import javax.swing.*;
import java.io.File;

public class StatusBarManager {
    private final JLabel statusBar;

    public StatusBarManager(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    public void setReady() {
        this.statusBar.setText(FrameLabel.READY.getLabel());
    }

    public void setLoading(String fileName) {
        this.statusBar.setText(FrameLabel.LOADING.getLabel() + fileName);
    }

    public void setLoaded(File file, String content) {
        long words = content.isBlank() ? 0 : content.split("\\s+").length;
        long chars = content.length();

        this.statusBar.setText(String.format(
                String.format(FrameLabel.LOADED.getLabel(), file.getName(), file.length(), words, chars)));
    }

    public void setError(ErrorLabel error) {
        this.statusBar.setText(error.getLabel());
    }
}
